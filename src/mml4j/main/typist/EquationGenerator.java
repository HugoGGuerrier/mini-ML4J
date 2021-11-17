package mml4j.main.typist;

import mml4j.main.Utils;
import mml4j.main.ast.*;
import mml4j.main.exceptions.TypingException;
import mml4j.main.typist.equation_system.*;
import mml4j.main.typist.equation_system.nodes.*;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;
import mml4j.main.typist.interfaces.INodeGenerator;
import mml4j.main.typist.utils.BaseContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is an equation generator for the inferred typist
 *
 * @author Hugo GUERRIER
 */
public class EquationGenerator implements INodeGenerator {

    // ----- Attributes -----


    /** The node counter to name the nodes */
    private int nodeCounter;

    /** The equation system to add the equations in */
    private final EquationSystem system;

    /** The corresponding map to generate nodes */
    private final Map<Node, Node> correspondence;


    // ----- Constructors -----


    /**
     * Create a new equation generator bound with an equation system
     *
     * @param system The equation system
     */
    public EquationGenerator(EquationSystem system) {
        this.nodeCounter = 0;
        this.system = system;
        this.system.setInitNode(getNewNode());
        this.correspondence = new HashMap<>();
    }


    // ----- Getters -----


    public EquationSystem getSystem() {
        return system;
    }


    // ----- Generator methods -----


    /** @see INodeGenerator#getNewNode() */
    @Override
    public SimpleNode getNewNode() {
        return new SimpleNode(nodeCounter++);
    }

    @Override
    public SimpleNode getNewNode(SimpleNode key) {
        SimpleNode res = (SimpleNode) correspondence.getOrDefault(key, null);
        if(res == null) {
            res = getNewNode();
            correspondence.put(key, res);
        }
        return res;
    }

    /** @see INodeGenerator#hasCorrespondence(Node) */
    @Override
    public boolean hasCorrespondence(Node key) {
        return correspondence.containsKey(key);
    }

    /** @see INodeGenerator#addCorrespondence(Node, Node) */
    @Override
    public void addCorrespondence(Node key, Node value) {
        correspondence.put(key, value);
    }

    /** @see INodeGenerator#reset() */
    @Override
    public void reset() {
        correspondence.clear();
        nodeCounter = 0;
    }


    // ----- Generation methods -----


    // --- Function definition and application

    // Generate equations for an abstraction
    public void generate(ASTAbs abs, Node target, Map<String, Node> context) throws TypingException {
        // Create the new nodes
        SimpleNode paramNode = getNewNode();
        SimpleNode resultNode = getNewNode();
        ArrowNode absNode = new ArrowNode(paramNode, resultNode);

        // Add the equation between the target and the abs node
        system.addEquation(target, absNode);

        // Add the variable to the context
        Map<String, Node> contextCopy = Utils.cloneMap(context);
        contextCopy.put(abs.getParam(), paramNode);

        // Type the body with the result target
        abs.getBody().acceptEqGenerator(this, resultNode, contextCopy);
    }

    // Generate equations for a recursive abstraction
    public void generate(ASTAbsRec absRec, Node target, Map<String, Node> context) throws TypingException {
        // Create the new nodes
        SimpleNode paramNode = getNewNode();
        SimpleNode resultNode = getNewNode();
        ArrowNode recNode = new ArrowNode(paramNode, resultNode);

        // Add the equation between the target and the abs node
        system.addEquation(target, recNode);

        // Add the variable to the context
        Map<String, Node> contextCopy = Utils.cloneMap(context);
        contextCopy.put(absRec.getParam(), paramNode);
        contextCopy.put(absRec.getName(), recNode);

        // Type the body with the result target
        absRec.getBody().acceptEqGenerator(this, resultNode, contextCopy);
    }

    // Generate equations for an application
    public void generate(ASTApp app, Node target, Map<String, Node> context) throws TypingException {
        // Create the new nodes
        SimpleNode argNode = getNewNode();
        ArrowNode funcNode = new ArrowNode(argNode, target);

        // Type the func and arg with
        app.getFunction().acceptEqGenerator(this, funcNode, context);
        app.getArg().acceptEqGenerator(this, argNode, context);
    }


    // --- Control structure

    // Generate equations for an if empty
    public void generate(ASTIfem ifem, Node target, Map<String, Node> context) throws TypingException {
        // Create the new nodes
        ForAllNode condNode = new ForAllNode();
        ListNode generalListNode = new ListNode(condNode.getNewNode());
        condNode.setType(generalListNode);

        // Generate the equations of the condition
        ifem.getCondition().acceptEqGenerator(this, condNode, context);

        // Generate the equations for the consequence and alternative
        ifem.getConsequence().acceptEqGenerator(this, target, context);
        ifem.getAlternative().acceptEqGenerator(this, target, context);
    }

    // Generate equations for a list if zero
    public void generate(ASTIfz ifz, Node target, Map<String, Node> context) throws TypingException {
        // Generate the equations for the condition
        ifz.getCondition().acceptEqGenerator(this, IntNode.getInstance(), context);

        // Generate the equations for the consequence and alternative
        ifz.getConsequence().acceptEqGenerator(this, target, context);
        ifz.getAlternative().acceptEqGenerator(this, target, context);
    }

    // Generate equations for a let
    public void generate(ASTLet let, Node target, Map<String, Node> context) throws TypingException {
        // Generate equations for the let value
        EquationSystem system = new EquationSystem();
        EquationGenerator generator = new EquationGenerator(system);

        // For each node in the context, create an equivalent node and clone the context
        Map<String, Node> internalContext = new HashMap<>();
        for(String var : context.keySet()) {
            // Get the external and the internal value
            Node externalNode = context.get(var);
            Node internalNode = generator.getNewNode();

            // Add the external equation to the system
            system.addExternalEquation(externalNode, internalNode);

            // Add the internal node to the internal context
            internalContext.put(var, internalNode);
        }

        // Start the equation generation
        let.getValue().acceptEqGenerator(generator, system.getInitNode(), internalContext);

        // Do debug print
        if(Utils.DEBUG) {
            System.out.println("=== Internal typing for the expression " + let.getValue() + "\n");
            System.out.println("Equation system :");
            System.out.println(system);
        }

        // Get the type node and generalize it
        Node letValueNode = system.unify();
        ForAllNode generalizedLetNode = ForAllNode.from(letValueNode, context, system.getExternalEquations());

        // Do debug print
        if(Utils.DEBUG) {
            System.out.println("Unified equation system : " + letValueNode);
            List<Equation> externalEquations = system.getExternalEquations();
            if(externalEquations.size() > 0) {
                System.out.println("External equations : " + externalEquations);
            }
            System.out.println("Generalized node : " + generalizedLetNode + "\n===\n");
        }

        // Create a context copy
        Map<String, Node> contextCopy = Utils.cloneMap(context);

        // Create the reverse context$
        Map<Node, String> reverseContext = new HashMap<>();
        for(String key : contextCopy.keySet()) {
            reverseContext.put(contextCopy.get(key), key);
        }

        // Extends the context with the new types and export the constraints
        for(Equation extEq : system.getExternalEquations()) {
            Node externalNode = extEq.getLeft();
            Node internalNode = extEq.getRight();

            ForAllNode genInt = ForAllNode.from(internalNode, contextCopy, null);
            contextCopy.put(reverseContext.get(externalNode), genInt);
            this.system.addEquation(externalNode, genInt);
        }

        // Put the generalized node in the context
        contextCopy.put(let.getName(), generalizedLetNode);

        // Generate equation for the let in
        let.getIn().acceptEqGenerator(this, target, contextCopy);
    }


    // --- Basic expressions

    // Generate equations for an integer
    public void generate(ASTInt intg, Node target, Map<String, Node> context) throws TypingException {
        // Add the equation in the system
        system.addEquation(target, IntNode.getInstance());
    }

    // Generate equations for a Nil
    public void generate(ASTNil __, Node target, Map<String, Node> context) throws TypingException {
        // Create the new nodes
        ListNode nilNode = new ListNode(getNewNode());

        // Add the equation to the system
        system.addEquation(target, nilNode);
    }

    // Generate equation for a unit value
    public void generate(ASTUnit __, Node target, Map<String, Node> context) throws TypingException {
        system.addEquation(target, UnitNode.getInstance());
    }

    // Generate equations for a variable
    public void generate(ASTVar var, Node target, Map<String, Node> context) throws TypingException {
        // Get the var node or null in the user context
        Node varNode = context.getOrDefault(var.getName(), null);

        // Get the var node or null in the default context
        varNode = BaseContext.getBaseContext().getOrDefault(var.getName(), varNode);

        // Test if the var node is null
        if(varNode == null) {
            throw new TypingException("Variable \"" + var.getName() + "\" doesn't exists in context");
        }

        // Add the equation with an instance of the variable (to avoid late instantiation)
        system.addEquation(target, varNode.instantiate(system));
    }

}

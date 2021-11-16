package MML4J.main.typist.utils;

import MML4J.main.Utils;
import MML4J.main.ast.*;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_system.*;
import MML4J.main.typist.equation_system.nodes.*;
import MML4J.main.typist.interfaces.INodeGenerator;

import java.util.ArrayList;
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

    @Override
    public boolean hasCorrespondence(Node key) {
        return correspondence.containsKey(key);
    }

    @Override
    public void addCorrespondence(Node key, Node value) {
        correspondence.put(key, value);
    }

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

        // Get the type node
        Node letValueNode = system.unify();

        // Do debug print
        if(Utils.DEBUG) {
            System.out.println("Unified equation system : " + letValueNode);
            List<Equation> externalEquations = system.getExternalEquations();
            if(externalEquations.size() > 0) {
                System.out.println("External equations : " + externalEquations);
            }
        }

        // Export the general from constraint to the equation system
        for(Equation extEq : system.getExternalEquations()) {
            this.system.addEquation(extEq.getLeft(), ForAllNode.from(extEq.getRight(), context, null));
        }

        // Generalize the let node
        ForAllNode generalizedLetNode = ForAllNode.from(letValueNode, context, system.getExternalEquations());

        // Do debug print
        if(Utils.DEBUG) System.out.println("Generalized node : " + generalizedLetNode + "\n===\n");

        // Put the generalized node in the context
        Map<String, Node> contextCopy = Utils.cloneMap(context);
        contextCopy.put(let.getName(), generalizedLetNode);

        // Generate equation for the let in
        let.getIn().acceptEqGenerator(this, target, contextCopy);
    }


    // --- Operators and build-in

    // Generate equation for a reference creation
    public void generate(ASTRef ref, Node target, Map<String, Node> context) throws TypingException {
        // Create the new nodes
        SimpleNode valNode = getNewNode();
        RefNode refNode = new RefNode(valNode);

        // Add the equation to the system
        system.addEquation(target, refNode);

        // Generate equations for the reference with the val node
        ref.getValue().acceptEqGenerator(this, valNode, context);
    }

    // Generate equation for a de-reference operator
    public void generate(ASTDeref deref, Node target, Map<String, Node> context) throws TypingException {
        // Create the new nodes
        SimpleNode valNode = getNewNode();
        RefNode refNode = new RefNode(valNode);

        // Add the equation to the system
        system.addEquation(target, valNode);

        // Generate the equations for the dereference
        deref.getReference().acceptEqGenerator(this, refNode, context);
    }

    // Generate equations for an addition
    public void generate(ASTAdd add, Node target, Map<String, Node> context) throws TypingException {
        // Add the equation to the system
        system.addEquation(target, IntNode.getInstance());

        // Type the left and right with the int node instance
        add.getLeft().acceptEqGenerator(this, IntNode.getInstance(), context);
        add.getRight().acceptEqGenerator(this, IntNode.getInstance(), context);
    }

    // Generate equations for a sub
    public void generate(ASTSub sub, Node target, Map<String, Node> context) throws TypingException {
        // Add the equation to the system
        system.addEquation(target, IntNode.getInstance());

        // Type the left and right with the int node instance
        sub.getLeft().acceptEqGenerator(this, IntNode.getInstance(), context);
        sub.getRight().acceptEqGenerator(this, IntNode.getInstance(), context);
    }

    // Generate equation for an assignment
    public void generate(ASTAssign assign, Node target, Map<String, Node> context) throws TypingException {
        // Add the equation to the system
        system.addEquation(target, UnitNode.getInstance());

        // Create the new type
        SimpleNode valNode = getNewNode();
        RefNode refNode = new RefNode(valNode);

        // Type the left and the right
        assign.getLeft().acceptEqGenerator(this, refNode, context);
        assign.getRight().acceptEqGenerator(this, valNode, context);
    }

    // Generate equations for a list constructor
    public void generate(ASTCons cons, Node target, Map<String, Node> context) throws TypingException {
        // Create the new nodes
        SimpleNode elemNode = getNewNode();
        ListNode listNode = new ListNode(elemNode);

        // Add the equation to the system
        system.addEquation(target, listNode);

        // Generate equation for the cons args
        cons.getHead().acceptEqGenerator(this, elemNode, context);
        cons.getTail().acceptEqGenerator(this, listNode, context);
    }

    // Generate equations for a list head operator
    public void generate(ASTHead head, Node target, Map<String, Node> context) throws TypingException {
        // Create the new nodes
        SimpleNode elemNode = getNewNode();
        ListNode listNode = new ListNode(elemNode);

        // Add the equation to the system
        system.addEquation(target, elemNode);

        // Generate equation for the head arg
        head.getList().acceptEqGenerator(this, listNode, context);
    }

    // Generate equations for a tail operator
    public void generate(ASTTail tail, Node target, Map<String, Node> context) throws TypingException {
        // Create the new nodes
        SimpleNode elemNode = getNewNode();
        ListNode listNode = new ListNode(elemNode);

        // Add the equation to the system
        system.addEquation(target, listNode);

        // Generate equations for the tail arg
        tail.getList().acceptEqGenerator(this, listNode, context);
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
        // Get the var node or null
        Node varNode = context.getOrDefault(var.getName(), null);

        // Test if the var node is null
        if(varNode == null) {
            throw new TypingException("Variable \"" + var.getName() + "\" doesn't exists in context");
        }

        // Add the equation with an instance of the variable (to avoid late instantiation)
        system.addEquation(target, varNode.instantiate(system));
    }

}
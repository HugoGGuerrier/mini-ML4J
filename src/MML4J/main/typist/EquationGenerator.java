package MML4J.main.typist;

import MML4J.main.Utils;
import MML4J.main.ast.*;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_system.*;
import MML4J.main.typist.utils.Generalizer;
import MML4J.main.typist.utils.NodePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquationGenerator {

    // ----- Attributes -----


    private int nodeCounter;
    private final EquationSystem system;


    // ----- Constructors -----


    public EquationGenerator(EquationSystem system) {
        nodeCounter = 0;
        this.system = system;
        this.system.setInitNode(getNewNode());
    }


    // ----- Getters -----


    public EquationSystem getSystem() {
        return system;
    }


    // ----- Internal methods -----


    SimpleNode getNewNode() {
        return new SimpleNode(nodeCounter++);
    }


    // ----- Class methods -----


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
        // Do debug print
        if(Utils.DEBUG) System.out.println("=== Internal typing for the expression " + let.getValue() + "\n");

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
            System.out.println("Equation system :");
            System.out.println(system);
        }

        // Get the type node
        Node letValueNode = system.unify();

        // Do debug print
        if(Utils.DEBUG) {
            System.out.println("Unified equation system : " + letValueNode);
            List<NodePair> externalEquations = system.getExternalEquations();
            if(externalEquations.size() > 0) {
                System.out.println("External equations : " + externalEquations);
            }
        }

        // Generalize the let node
        ForAllNode generalizedLetNode = Generalizer.generalize(letValueNode, context, system.getExternalEquations());

        // Do debug print
        if(Utils.DEBUG) System.out.println("Generalized node : " + generalizedLetNode + "\n===\n");

        // Put the generalized node in the context
        Map<String, Node> contextCopy = Utils.cloneMap(context);
        contextCopy.put(let.getName(), generalizedLetNode);

        // Generate equation for the let in
        let.getIn().acceptEqGenerator(this, target, contextCopy);
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


    // Generate equations for a Nil
    public void generate(ASTNil nil, Node target, Map<String, Node> context) throws TypingException {
        // Create the new nodes
        ForAllNode nilNode = new ForAllNode();
        ListNode generalListNode = new ListNode(nilNode.getNewNode());
        nilNode.setType(generalListNode);

        // Add the equation to the system
        system.addEquation(target, nilNode);
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


    // Generate equations for a variable
    public void generate(ASTVar var, Node target, Map<String, Node> context) throws TypingException {
        // Get the var node or null
        Node varNode = context.getOrDefault(var.getName(), null);

        // Test if the var node is null
        if(varNode == null) {
            throw new TypingException("Error during equation generation : " + var.getName() + " doesn't exists in context");
        }

        // Add the equation
        system.addEquation(target, varNode);
    }

    // Generate equations for an integer
    public void generate(ASTInt intg, Node target, Map<String, Node> context) throws TypingException {
        // Add the equation in the system
        system.addEquation(target, IntNode.getInstance());
    }

}

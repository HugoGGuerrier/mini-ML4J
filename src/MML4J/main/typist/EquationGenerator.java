package MML4J.main.typist;

import MML4J.main.Utils;
import MML4J.main.ast.*;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_graph.*;

import java.util.Map;

public class EquationGenerator {

    // ----- Attributes -----


    private int nodeCounter;


    // ----- Constructors -----


    public EquationGenerator() {
        nodeCounter = 0;
    }


    // ----- Internal methods -----


    SimpleNode getNextNode() {
        return new SimpleNode(nodeCounter++);
    }


    // ----- Class methods -----


    // Generate equations for an abstraction
    public void generate(ASTAbs abs, Node target, Map<String, Node> context) throws TypingException {
        // Create the new nodes
        SimpleNode varSimpleNode = getNextNode();
        SimpleNode newSimpleNode = getNextNode();
        ArrowNode newArrowNode = new ArrowNode(varSimpleNode, newSimpleNode);

        // Add the equivalence between target and new arrow node
        target.addChild(newArrowNode);
        newArrowNode.addParent(target);

        // Copy the context and enlarge it
        Map<String, Node> newContext = Utils.cloneMap(context);
        newContext.put(abs.getParam(), varSimpleNode);

        // Visit the abstraction body
        abs.getBody().acceptEqGenerator(this, newSimpleNode, newContext);
    }

    // Generate equations for a recursive abstraction
    public void generate(ASTAbsRec absRec, Node target, Map<String, Node> context) throws TypingException {

    }

    // Generate equations for an addition
    public void generate(ASTAdd add, Node target, Map<String, Node> context) throws TypingException {
        // Add the constraint that the target is int
        target.addChild(IntNode.getInstance());
        IntNode.getInstance().addParent(target);

        // Start the typing on the left and right
        add.getLeft().acceptEqGenerator(this, IntNode.getInstance(), context);
        add.getRight().acceptEqGenerator(this, IntNode.getInstance(), context);
    }

    // Generate equations for an application
    public void generate(ASTApp app, Node target, Map<String, Node> context) throws TypingException {
        // Create the new types
        SimpleNode newSimpleNode = getNextNode();
        ArrowNode newArrowNode = new ArrowNode(newSimpleNode, target);

        // Visit the argument and the function
        app.getArg().acceptEqGenerator(this, newSimpleNode, context);
        app.getFunction().acceptEqGenerator(this, newArrowNode, context);
    }

    // Generate equations for a list constructor
    public void generate(ASTCons cons, Node target, Map<String, Node> context) throws TypingException {

    }

    // Generate equations for a list head operator
    public void generate(ASTHead head, Node target, Map<String, Node> context) throws TypingException {
        // Create the new target
        ListNode listNode = new ListNode(target);

        // Start the typing on the tail arg
        head.getList().acceptEqGenerator(this, listNode, context);
    }

    // Generate equations for an if empty
    public void generate(ASTIfem ifem, Node target, Map<String, Node> context) throws TypingException {

    }

    // Generate equations for a list if zero
    public void generate(ASTIfz ifz, Node target, Map<String, Node> context) throws TypingException {

    }

    // Generate equations for an integer
    public void generate(ASTInt intg, Node target, Map<String, Node> context) throws TypingException {
        // Add the integer constraint
        target.addChild(IntNode.getInstance());
        IntNode.getInstance().addParent(target);
    }

    // Generate equations for a let
    public void generate(ASTLet let, Node target, Map<String, Node> context) throws TypingException {

    }

    // Generate equations for a Nil
    public void generate(ASTNil nil, Node target, Map<String, Node> context) throws TypingException {

    }

    // Generate equations for a sub
    public void generate(ASTSub sub, Node target, Map<String, Node> context) throws TypingException {
        // Add the constraint that the target is int
        target.addChild(IntNode.getInstance());
        IntNode.getInstance().addParent(target);

        // Start the typing on the left and right
        sub.getLeft().acceptEqGenerator(this, IntNode.getInstance(), context);
        sub.getRight().acceptEqGenerator(this, IntNode.getInstance(), context);
    }

    // Generate equations for a tail operator
    public void generate(ASTTail tail, Node target, Map<String, Node> context) throws TypingException {
        // Create the new target
        ListNode listNode = new ListNode(target);

        // Start the typing on the tail arg
        tail.getList().acceptEqGenerator(this, listNode, context);
    }

    // Generate equations for a variable
    public void generate(ASTVar var, Node target, Map<String, Node> context) throws TypingException {
        // Get the node in the context and verify it
        Node varNode = context.getOrDefault(var.getName(), null);
        if(varNode == null) throw new TypingException("Variable " + var.getName() + " type isn't in the current context...");

        // Add the relation
        varNode.addChild(target);
        target.addParent(varNode);
    }

}

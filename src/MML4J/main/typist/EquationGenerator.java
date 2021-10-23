package MML4J.main.typist;

import MML4J.main.Utils;
import MML4J.main.ast.*;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_graph.ArrowNode;
import MML4J.main.typist.equation_graph.Node;
import MML4J.main.typist.equation_graph.SimpleNode;

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

    // Generate equations for an if empty
    public void generate(ASTIfem ifem, Node target, Map<String, Node> context) throws TypingException {

    }

    // Generate equations for a list if zero
    public void generate(ASTIfz ifz, Node target, Map<String, Node> context) throws TypingException {

    }

    // Generate equations for an integer
    public void generate(ASTInt intg, Node target, Map<String, Node> context) throws TypingException {

    }

    // Generate equations for a let
    public void generate(ASTLet let, Node target, Map<String, Node> context) throws TypingException {

    }

    // Generate equations for a Nil
    public void generate(ASTNil nil, Node target, Map<String, Node> context) throws TypingException {

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

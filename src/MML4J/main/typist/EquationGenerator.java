package MML4J.main.typist;

import MML4J.main.Utils;
import MML4J.main.ast.*;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_system.ArrowNode;
import MML4J.main.typist.equation_system.EquationSystem;
import MML4J.main.typist.equation_system.Node;
import MML4J.main.typist.equation_system.SimpleNode;

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
        context.put(abs.getParam(), paramNode);

        // Type the body with the result target
        abs.getBody().acceptEqGenerator(this, resultNode, context);
    }

    // Generate equations for a recursive abstraction
    public void generate(ASTAbsRec absRec, Node target, Map<String, Node> context) throws TypingException {

    }

    // Generate equations for an addition
    public void generate(ASTAdd add, Node target, Map<String, Node> context) throws TypingException {

    }

    // Generate equations for an application
    public void generate(ASTApp app, Node target, Map<String, Node> context) throws TypingException {
        // Create the new nodes
        SimpleNode argNode = getNewNode();
        ArrowNode funcNode = new ArrowNode(argNode, target);

        // Type the func and arg with
        app.getFunction().acceptEqGenerator(this, funcNode, Utils.cloneMap(context));
        app.getArg().acceptEqGenerator(this, argNode, Utils.cloneMap(context));
    }

    // Generate equations for a list constructor
    public void generate(ASTCons cons, Node target, Map<String, Node> context) throws TypingException {

    }

    // Generate equations for a list head operator
    public void generate(ASTHead head, Node target, Map<String, Node> context) throws TypingException {

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

    // Generate equations for a sub
    public void generate(ASTSub sub, Node target, Map<String, Node> context) throws TypingException {

    }

    // Generate equations for a tail operator
    public void generate(ASTTail tail, Node target, Map<String, Node> context) throws TypingException {

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
        system.addEquation(varNode, target);
    }

}

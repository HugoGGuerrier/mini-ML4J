package MML4J.main.typist;

import MML4J.main.ast.ASTAbs;
import MML4J.main.ast.ASTApp;
import MML4J.main.ast.ASTExpr;
import MML4J.main.ast.ASTVar;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_graph.ArrowNode;
import MML4J.main.typist.equation_graph.Node;
import MML4J.main.typist.equation_graph.SimpleNode;
import MML4J.main.typist.type.Type;

import java.util.HashMap;

public class Typist {

    // ----- Attributes -----

    private int counter;

    // ----- Constructors -----

    public Typist() {
        this.counter = 0;
    }

    // ----- Internal methods -----

    /**
     * Get the next available simple node
     *
     * @return The next node
     */
    private SimpleNode getNextNode() {
        return new SimpleNode("T" + counter++);
    }

    /**
     * Process the equation generation on an expression
     *
     * @param expr The AST node
     * @param target The target type
     * @param context The typing context
     * @throws TypingException If the var is not found in the context
     */
    private void recursEqG(ASTExpr expr, Node target, HashMap<String, Node> context) throws TypingException {

        if(expr instanceof ASTVar) {
            ASTVar var = (ASTVar) expr;

            // Get the node in the context and verify it
            Node varNode = context.getOrDefault(var.getName(), null);
            if(varNode == null) throw new TypingException("Variable " + var.getName() + " type isn't in the current context...");

            // Add the relation
            varNode.addChild(target);
            target.addParent(varNode);
        }

        else if(expr instanceof ASTApp) {
            ASTApp app = (ASTApp) expr;

            // Create the new types
            SimpleNode newSimpleNode = getNextNode();
            ArrowNode newArrowNode = new ArrowNode(newSimpleNode, target);

            // Type the function
            recursEqG(app.getFunction(), newArrowNode, context);

            // Type the arguments
            recursEqG(app.getArg(), newSimpleNode, context);
        }

        else if(expr instanceof ASTAbs) {
            ASTAbs abs = (ASTAbs) expr;

            // Create the new nodes
            SimpleNode varSimpleNode = getNextNode();
            SimpleNode newSimpleNode = getNextNode();
            ArrowNode newArrowNode = new ArrowNode(varSimpleNode, newSimpleNode);

            // Add the equivalence between target and new arrow node
            target.addChild(newArrowNode);
            newArrowNode.addParent(target);
            
            // Copy the context and enlarge it
            HashMap<String, Node> newContext = ((HashMap<String, Node>) context.clone());
            newContext.put(abs.getParam(), varSimpleNode);

            // Type the body with the new context
            recursEqG(abs.getBody(), newSimpleNode, newContext);
        }

        else {
            throw new TypingException("Unknown AST type");
        }

    }

    // ----- Class methods -----

    /**
     * Get the equation graph from an AST expression
     *
     * @param expr The AST representing the expression
     * @return The equation graph extracted from the expression
     */
    public Node getEquationGraph(ASTExpr expr) throws TypingException {
        // Reset the counter
        counter = 0;

        // Verify the non nullity of the expr
        if(expr == null) throw new TypingException("Cannot generate equations from a null AST");

        // Generate the equations and return the result
        Node res = getNextNode();
        recursEqG(expr, res, new HashMap<>());
        return res;
    }

    /**
     * Unify the equation graph and get the final type
     *
     * @param graph The equation graph to unify
     * @return The final type
     */
    public Type unify(Node graph) {
        return null;
    }

}

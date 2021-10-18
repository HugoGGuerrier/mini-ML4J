package MML4J.main.typist;

import MML4J.main.Utils;
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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Typist {

    // ----- Attributes -----

    private int counter;
    private int iteration;

    // ----- Constructors -----

    /**
     * Create and initialize the typist
     */
    public Typist() {
        this.counter = 0;
        this.iteration = 0;
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
    private void recursEquationGeneration(ASTExpr expr, Node target, Map<String, Node> context) throws TypingException {

        // If the expression is a variable
        if(expr instanceof ASTVar) {
            ASTVar var = (ASTVar) expr;

            // Get the node in the context and verify it
            Node varNode = context.getOrDefault(var.getName(), null);
            if(varNode == null) throw new TypingException("Variable " + var.getName() + " type isn't in the current context...");

            // Add the relation
            varNode.addChild(target);
            target.addParent(varNode);
        }

        // If the expression is an application
        else if(expr instanceof ASTApp) {
            ASTApp app = (ASTApp) expr;

            // Create the new types
            SimpleNode newSimpleNode = getNextNode();
            ArrowNode newArrowNode = new ArrowNode(newSimpleNode, target);

            // Type the function
            recursEquationGeneration(app.getFunction(), newArrowNode, context);

            // Type the arguments
            recursEquationGeneration(app.getArg(), newSimpleNode, context);
        }

        // If the expression is an abstraction
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
            Map<String, Node> newContext = Utils.cloneMap(context);
            newContext.put(abs.getParam(), varSimpleNode);

            // Type the body with the new context
            recursEquationGeneration(abs.getBody(), newSimpleNode, newContext);
        }

        // Else, there is an error
        else {
            throw new TypingException("Unknown AST type");
        }

    }

    /**
     * Unify a given node recursively
     *
     * @param node The node to unify
     * @return The result node of the unification
     */
    private Node recursUnification(Node node) throws TypingException {
        // Do the debug things
        if(Utils.DEBUG) {
            iteration++;
            System.out.println("Start recursive unification on " + node + " | Children : " + node.getChildren());
        }

        // Start the unification on all children (infix recursion)
        Set<Node> nodeChildren = new HashSet<>(node.getChildren());
        if(node instanceof ArrowNode) {
            ArrowNode arrowNode = (ArrowNode) node;
            nodeChildren.add(arrowNode.getLeft());
            nodeChildren.add(arrowNode.getRight());
        }
        for(Node child : nodeChildren) {
            // Recurs on the children
            recursUnification(child);
        }

        // Create the unifier
        Unifier unifier = new Unifier();

        // Unify the node with all its children
        Node currentNode = node;
        Set<Node> toVisit = new HashSet<>(node.getChildren());
        for(Node child : toVisit) {
            currentNode = unifier.unify(currentNode, child);
        }

        return currentNode;
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
        recursEquationGeneration(expr, res, new HashMap<>());
        return res;
    }

    /**
     * Unify the equation graph and get the final type
     *
     * @param graph The equation graph to unify
     * @return The final type
     * @throws TypingException If the graph cannot be unified
     */
    public Type unify(Node graph) throws TypingException {
        // If in debug mode, count the iteration
        if(Utils.DEBUG) {
            iteration = 0;
        }

        // Unify the graph
        Node theNode = recursUnification(graph);

        // Display the algorithm efficiency
        if(Utils.DEBUG) {
            System.out.println("Iterations = " + iteration);
        }

        // Verify the unification
        // TODO

        // Turn the graph into a type
        // TODO
        return null;
    }

    /**
     * Get the inferred type for the given expression
     *
     * @param expr The expresion
     * @return The inferred type
     */
    public Type typeExpression(ASTExpr expr) {
        // TODO : Generate equations and the type of the expression
        return null;
    }

}

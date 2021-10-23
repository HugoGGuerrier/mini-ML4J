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
import MML4J.main.typist.type.ArrowType;
import MML4J.main.typist.type.SimpleType;
import MML4J.main.typist.type.Type;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Typist {

    // ----- Attributes -----

    private int typeCounter;
    private final Map<Node, Type> typeMap;
    private int iteration;

    // ----- Constructors -----

    /**
     * Create and initialize the typist
     */
    public Typist() {
        this.typeCounter = 0;
        this.typeMap = new HashMap<>();
        this.iteration = 0;
    }

    // ----- Internal methods -----

    /**
     * Get the next available type
     *
     * @return The type
     */
    private SimpleType getNextType() {
        return new SimpleType("T" + typeCounter++);
    }

    /**
     * Get the type associated with the node
     *
     * @param node The node
     * @return The type
     */
    private Type getTypeForNode(Node node) {
        Type res = typeMap.getOrDefault(node, null);
        if(res == null) {
            res = getNextType();
            typeMap.put(node, res);
        }
        return res;
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

        // Copy the node children to visit them
        Set<Node> nodeChildren = new HashSet<>(node.getChildren());

        // If the node is an arrow, add the left and right to the children
        if(node instanceof ArrowNode) {
            ArrowNode arrowNode = (ArrowNode) node;
            nodeChildren.add(arrowNode.getLeft());
            nodeChildren.add(arrowNode.getRight());
        }

        // Unify the children
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

    /**
     * Create a type with the given node
     *
     * @param node The node to create the type from
     * @return The created type
     * @throws TypingException If the type is not unified
     */
    private Type getTypeFromNode(Node node) throws TypingException {
        // Verify that the node has no child
        if(node.getChildren().size() > 0) {
            throw new TypingException("Cannot create a type from a not childless node : " + node);
        }

        // If the node is a simple node just return a new type
        if(node instanceof SimpleNode) {
            return getTypeForNode(node);
        }

        // If the node is an arrow node return the arrow type
        else if(node instanceof ArrowNode) {
            ArrowNode arrowNode = (ArrowNode) node;
            return new ArrowType(getTypeFromNode(arrowNode.getLeft()), getTypeFromNode(arrowNode.getRight()));
        }

        // Else, there is an error
        else {
            throw new TypingException("Unknown node type");
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
        // Verify the non nullity of the expr
        if(expr == null) throw new TypingException("Cannot generate equations from a null AST");

        // Create the equation generator and visit the ast
        EquationGenerator generator = new EquationGenerator();
        SimpleNode baseNode = generator.getNextNode();
        expr.acceptEqGenerator(generator, baseNode, new HashMap<>());

        // Return the base node
        return baseNode;
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

        // Display the algorithm iterations
        if(Utils.DEBUG) {
            System.out.println("Iterations = " + iteration);
        }

        // Reset the type counter
        typeCounter = 0;

        // Turn the graph into a type
        return getTypeFromNode(theNode);
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

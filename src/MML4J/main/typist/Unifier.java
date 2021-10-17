package MML4J.main.typist;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_graph.ArrowNode;
import MML4J.main.typist.equation_graph.Node;
import MML4J.main.typist.equation_graph.SimpleNode;

public class Unifier {

    // ----- Internal methods -----

    private Node unifySimpleNode(SimpleNode node, Node child) {
        // TODO
        return null;
    }

    private Node unifyArrowNode(ArrowNode node, Node child) throws TypingException {
        // TODO
        return null;
    }

    // ----- Class methods -----

    /**
     * Unify a node with one of its children and return the result
     *
     * @param node The node
     * @param child The children
     * @return The result node
     * @throws TypingException If there is an error during unifying
     */
    public Node unify(Node node, Node child) throws TypingException {
        // Pattern match the node
        if(node instanceof SimpleNode) return unifySimpleNode((SimpleNode) node, child);
        if(node instanceof ArrowNode) return unifyArrowNode((ArrowNode) node, child);

        // Default exception
        throw new TypingException("Unknown node type");
    }

}

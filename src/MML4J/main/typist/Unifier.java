package MML4J.main.typist;

import MML4J.main.Utils;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_graph.ArrowNode;
import MML4J.main.typist.equation_graph.Node;
import MML4J.main.typist.equation_graph.SimpleNode;
import org.junit.jupiter.api.DisplayNameGenerator;

public class Unifier {

    // ----- Internal methods -----

    private boolean isRecursive(SimpleNode node, ArrowNode arrow) {
        // Test the left
        boolean leftEquals = false;
        if(arrow.getLeft().equals(node)) leftEquals = true;
        else if(arrow.getLeft() instanceof ArrowNode) {
            ArrowNode left = (ArrowNode) arrow.getLeft();
            leftEquals = isRecursive(node, left);
        }

        // Test the right
        boolean rightEquals = false;
        if(arrow.getRight().equals(node)) rightEquals = true;
        else if(arrow.getRight() instanceof ArrowNode) {
            ArrowNode right = (ArrowNode) arrow.getRight();
            rightEquals = isRecursive(node, right);
        }

        // Return te result
        return leftEquals || rightEquals;
    }

    private Node unifySimpleNode(SimpleNode me, Node other) throws TypingException {
        // Verify that the other is not recursive
        if(other instanceof ArrowNode) {
            ArrowNode arrowNode = (ArrowNode) other;
            if(isRecursive(me, arrowNode)) throw new TypingException("Type is recursive");
        }

        // Give all the node parent to the child
        for(Node myParent : me.getParents()) {
            myParent.replaceChild(me, other);
            other.addParent(myParent);
        }

        // Give all the node child to the child
        for(Node myChild : me.getChildren()) {
            if(myChild != other) {
                myChild.replaceParent(me, other);
                other.addChild(myChild);
            }
        }

        // Remove the parent from the other
        other.removeParent(me);

        // Destroy the node
        me.destroy();

        // Return the child
        return other;
    }

    private Node unifyArrowNode(ArrowNode me, Node other) throws TypingException {
        // Verify that the other is not recursive
        if(other instanceof SimpleNode) {
            SimpleNode simpleNode = (SimpleNode) other;
            if(isRecursive(simpleNode, me)) throw new TypingException("Type is recursive");
        }

        // Get the parents from the other node
        for(Node otherParent : other.getParents()) {
            if(otherParent != me) {
                otherParent.replaceChild(other, me);
                me.addParent(otherParent);
            }
        }

        // Remove the child from me
        me.removeChild(other);

        // Get the children form the other node
        for(Node otherChild : other.getChildren()) {
            otherChild.replaceParent(other, me);
            me.addChild(otherChild);
        }

        // If the child is an arrow node
        if(other instanceof ArrowNode) {
            ArrowNode arrowNode = (ArrowNode) other;

            // Unify the lefts
            me.setLeft(this.unify(me.getLeft(), arrowNode.getLeft()));

            // Unify the rights
            me.setRight(this.unify(me.getRight(), arrowNode.getRight()));
        }

        // Destroy the other node
        other.destroy();

        // Return the node
        return me;
    }

    // ----- Class methods -----

    /**
     * Unify a node with one of its children and return the result
     *
     * @param me The node
     * @param other The children
     * @return The result node
     * @throws TypingException If there is an error during unifying
     */
    public Node unify(Node me, Node other) throws TypingException {
        // Do the unification things
        if(Utils.DEBUG) {
            System.out.println("Unify the nodes " + me + " and " + other);
        }

        // Pattern match the node
        if(me instanceof SimpleNode) return unifySimpleNode((SimpleNode) me, other);
        if(me instanceof ArrowNode) return unifyArrowNode((ArrowNode) me, other);

        // Default exception
        throw new TypingException("Unknown node type");
    }

}

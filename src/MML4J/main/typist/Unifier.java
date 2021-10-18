package MML4J.main.typist;

import MML4J.main.Utils;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_graph.ArrowNode;
import MML4J.main.typist.equation_graph.Node;
import MML4J.main.typist.equation_graph.SimpleNode;

public class Unifier {

    // ----- Internal methods -----

    private Node unifySimpleNode(SimpleNode me, Node other) throws TypingException {
        // If the other is an arrow node, verify that i'm not the left or right
        if(other instanceof ArrowNode) {
            ArrowNode arrowNode = (ArrowNode) other;
            if(arrowNode.getLeft() == me || arrowNode.getRight() == me) throw new TypingException("Cyclic type definition : " + me + " = " + arrowNode);
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
        // If the left or right is the other node, throw an error
        if(me.getLeft() == other || me.getRight() == other) throw new TypingException("Cyclic type definition : " + me + " = " + other);

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

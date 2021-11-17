package mml4j.main.typist.equation_system;

import mml4j.main.Pair;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;
import mml4j.main.typist.interfaces.INodeContained;
import mml4j.main.typist.interfaces.INodeContainer;

/**
 * This class represent an equation as a pair of nodes
 * @see Pair
 *
 * @author Hugo GUERRIER
 */
public class Equation extends Pair<Node, Node> implements INodeContainer {

    // ----- Constructors -----


    /**
     * Create a new equation with the left and the right member
     *
     * @see Pair#Pair(Object, Object)
     *
     * @param l The left member
     * @param r The right member
     */
    public Equation(Node l, Node r) {
        super(l, r);
        l.addContainer(this);
        r.addContainer(this);
    }


    // ----- Setters -----


    /** @see Pair#setLeft(Object) */
    @Override
    public void setLeft(Node left) {
        left.addContainer(this);
        if(this.left != null) this.left.removeContainer(this);
        this.left = left;
    }

    /** @see Pair#setRight(Object) */
    @Override
    public void setRight(Node right) {
        right.addContainer(this);
        if(this.right != null) this.right.removeContainer(this);
        this.right = right;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return left + " = " + right;
    }


    // ----- Class methods -----


    /** @see INodeContainer#replaceContained(INodeContained, INodeContained) */
    @Override
    public void replaceContained(INodeContained oldCont, INodeContained newCont) {
        if(left == oldCont) setLeft((Node) newCont);
        if(right == oldCont) setRight((Node) newCont);
    }

    /**
     * Destroy a pair from the existence
     */
    public void destroy() {
        if(left != null) left.removeContainer(this);
        if(right != null) right.removeContainer(this);
    }

    /**
     * Simply reverse the pair
     */
    public void reverse() {
        Node tmp = this.right;
        right = left;
        left = tmp;
    }

}

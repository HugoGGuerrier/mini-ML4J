package MML4J.main.typist.utils;

import MML4J.main.Pair;
import MML4J.main.typist.equation_system.Node;

public class NodePair extends Pair<Node, Node> implements INodeContainer {

    // ----- Constructors -----


    public NodePair(Node l, Node r) {
        super(l, r);
        l.addContainer(this);
        r.addContainer(this);
    }


    // ----- Setters -----


    @Override
    public void setLeft(Node left) {
        left.addContainer(this);
        if(this.left != null) this.left.removeContainer(this);
        this.left = left;
    }

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

    @Override
    public void replaceNode(Node oldNode, Node newNode) {
        if(left == oldNode) setLeft(newNode);
        if(right == oldNode) setRight(newNode);
    }


    // ----- Class methods -----


    public void destroy() {
        if(left != null) left.removeContainer(this);
        if(right != null) right.removeContainer(this);
    }

    public void reverse() {
        Node tmp = this.right;
        right = left;
        left = tmp;
    }

}

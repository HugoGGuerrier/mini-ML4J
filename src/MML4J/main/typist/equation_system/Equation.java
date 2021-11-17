package MML4J.main.typist.equation_system;

import MML4J.main.Pair;
import MML4J.main.typist.equation_system.nodes.abstracts.Node;
import MML4J.main.typist.interfaces.INodeContained;
import MML4J.main.typist.interfaces.INodeContainer;

public class Equation extends Pair<Node, Node> implements INodeContainer {

    // ----- Constructors -----


    public Equation(Node l, Node r) {
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
    public void replaceContained(INodeContained oldCont, INodeContained newCont) {
        if(left == oldCont) setLeft((Node) newCont);
        if(right == oldCont) setRight((Node) newCont);
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

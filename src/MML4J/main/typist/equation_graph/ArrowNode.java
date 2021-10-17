package MML4J.main.typist.equation_graph;

public class ArrowNode extends Node {

    // ----- Attributes -----

    private Node left;
    private Node right;

    // ----- Constructors -----

    public ArrowNode(Node left, Node right) {
        this.left = left;
        this.right = right;
        left.addParent(this);
        right.addParent(this);
    }

    // ----- Getters -----

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    // ----- Setters -----

    @Override
    public void replaceChild(Node oldChild, Node newChild) {
        if(oldChild == left) left = newChild;
        else if(oldChild == right) right = newChild;
        else super.replaceChild(oldChild, newChild);
    }

    // ----- Override methods -----

    @Override
    public String toString() {
        return "(" + left.toString() + " -> " + right.toString() + ")";
    }

    // ----- Class methods -----

    @Override
    public String getEquationsString() {
        return super.getEquationsString() +
                left.getEquationsString() +
                right.getEquationsString();
    }

}

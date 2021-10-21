package MML4J.main.typist.equation_graph;

public class ArrowNode extends Node {

    // ----- Attributes -----

    protected Node left;
    protected Node right;

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

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public void replaceChild(Node oldChild, Node newChild) {
        if(oldChild == left) left = newChild;
        else if(oldChild == right) right = newChild;
        else super.replaceChild(oldChild, newChild);
    }

    @Override
    public void destroy() {
        super.destroy();
        left = null;
        right = null;
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

    @Override
    public boolean structEquals(Node other) {
        if(this == other) return true;
        if(other == null || getClass() != other.getClass()) return false;
        ArrowNode arrowNode = (ArrowNode) other;

        boolean childrenEquality = children.size() == arrowNode.children.size();
        if(childrenEquality) {
            for(Node child : children) {
                boolean childEq = false;
                for(Node otherChild : arrowNode.children) {
                    if(child.structEquals(otherChild)) childEq = true;
                }
                childrenEquality = childrenEquality && childEq;
            }
        }

        return left.structEquals(arrowNode.left) && right.structEquals(arrowNode.right) && childrenEquality;
    }

}

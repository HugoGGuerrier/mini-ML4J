package MML4J.main.typist.equation_graph;

public class SimpleNode extends Node {

    // ----- Attributes -----

    protected final int id;

    // ----- Constructors -----

    public SimpleNode(int id) {
        this.id = id;
    }

    // ----- Getters -----

    public int getId() {
        return id;
    }

    // ----- Override methods -----

    @Override
    public String toString() {
        return "T" + id;
    }

    @Override
    public boolean structEquals(Node other) {
        if(this == other) return true;
        if(other == null || getClass() != other.getClass()) return false;
        SimpleNode simpleNode = (SimpleNode) other;

        boolean childrenEquality = children.size() == simpleNode.children.size();
        if(childrenEquality) {
            for(Node child : children) {
                boolean childEq = false;
                for(Node otherChild : simpleNode.children) {
                    if(child.structEquals(otherChild)) childEq = true;
                }
                childrenEquality = childrenEquality && childEq;
            }
        }

        return id == simpleNode.id && childrenEquality;
    }

}

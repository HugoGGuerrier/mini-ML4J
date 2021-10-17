package MML4J.main.typist.equation_graph;

public class SimpleNode extends Node {

    // ----- Attributes -----

    protected final String name;

    // ----- Constructors -----

    public SimpleNode(String name) {
        this.name = name;
    }

    // ----- Getters -----

    public String getName() {
        return name;
    }

    // ----- Override methods -----

    @Override
    public String toString() {
        return name;
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

        return name.equals(simpleNode.name) && childrenEquality;
    }

}

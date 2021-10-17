package MML4J.main.typist.equation_graph;

public class SimpleNode extends Node {

    // ----- Attributes -----

    private final String name;

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

}

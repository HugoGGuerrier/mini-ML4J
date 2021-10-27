package MML4J.main.typist.type;

import MML4J.main.typist.Generalizer;
import MML4J.main.typist.equation_graph.Node;

import java.util.Objects;

public class ArrowType extends Type {

    // ----- Attributes -----


    private final Type left;
    private final Type right;


    // ----- Constructors -----


    public ArrowType(Type left, Type right) {
        this.left = left;
        this.right = right;
    }


    // ----- Getters -----


    public Type getLeft() {
        return left;
    }

    public Type getRight() {
        return right;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "(" + left + " -> " + right + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrowType arrowType = (ArrowType) o;
        return Objects.equals(left, arrowType.left) && Objects.equals(right, arrowType.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

}

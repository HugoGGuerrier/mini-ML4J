package MML4J.main.typist.type;

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

}

package MML4J.main;

/**
 * This class represents a polymorphic pair of object
 *
 * @param <T> The left type
 * @param <U> The right type
 * @author Hugo Guerrier
 */
public class Pair<T, U> {

    // ----- Attributes -----

    protected T left;
    protected U right;

    // ----- Constructor -----

    /**
     * Create a new pair with key and value
     *
     * @param l The left
     * @param r The right
     */
    public Pair(T l, U r) {
        left = l;
        right = r;
    }

    // ----- Getters -----

    public T getLeft() {
        return left;
    }

    public U getRight() {
        return right;
    }

    // ----- Setters -----

    public void setLeft(T left) {
        this.left = left;
    }

    public void setRight(U right) {
        this.right = right;
    }

}
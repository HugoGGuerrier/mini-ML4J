package MML4J.main.ast;

import java.util.Objects;

public class ASTCons extends ASTExpr {

    // ----- Attributes -----

    private final ASTExpr head;
    private final ASTExpr tail;

    // ----- Constructors -----

    public ASTCons(ASTExpr head, ASTExpr tail) {
        this.head = head;
        this.tail = tail;
    }

    // ----- Getters -----

    public ASTExpr getHead() {
        return head;
    }

    public ASTExpr getTail() {
        return tail;
    }

    // ----- Override methods -----

    @Override
    public String toString() {
        return "cons(" + head + ", " + tail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTCons astCons = (ASTCons) o;
        return Objects.equals(head, astCons.head) && Objects.equals(tail, astCons.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail);
    }

}

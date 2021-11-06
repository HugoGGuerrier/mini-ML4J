package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.EquationGenerator;
import MML4J.main.typist.equation_system.Node;

import java.util.Map;
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
    public void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException {
        gen.generate(this, target, context);
    }

    @Override
    public String toString() {
        return "cons(" + head + ", " + tail + ")";
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

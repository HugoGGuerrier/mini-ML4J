package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.EquationGenerator;
import MML4J.main.typist.equation_system.Node;

import java.util.Map;
import java.util.Objects;

public class ASTDeref extends ASTExpr {

    // ----- Attributes -----


    protected final ASTExpr reference;


    // ----- Constructors -----


    public ASTDeref(ASTExpr reference) {
        this.reference = reference;
    }


    // ----- Getters -----


    public ASTExpr getReference() {
        return reference;
    }


    // ----- Override methods -----


    @Override
    public void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException {
        gen.generate(this, target, context);
    }

    @Override
    public String toString() {
        return "!" + reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTDeref astDeref = (ASTDeref) o;
        return Objects.equals(reference, astDeref.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }

}

package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.utils.EquationGenerator;
import MML4J.main.typist.equation_system.nodes.Node;

import java.util.Map;
import java.util.Objects;

public class ASTRef extends ASTExpr {

    // ----- Attributes -----


    protected final ASTExpr value;


    // ----- Constructors -----


    public ASTRef(ASTExpr value) {
        this.value = value;
    }


    // ----- Getters -----


    public ASTExpr getValue() {
        return value;
    }


    // ----- Override methods -----


    @Override
    public void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException {
        gen.generate(this, target, context);
    }

    @Override
    public String toString() {
        return "*" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTRef astRef = (ASTRef) o;
        return Objects.equals(value, astRef.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}

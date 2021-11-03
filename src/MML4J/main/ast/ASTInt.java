package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.EquationGenerator;
import MML4J.main.typist.equation_system.Node;

import java.util.Map;
import java.util.Objects;

public class ASTInt extends ASTExpr {

    // ----- Attributes -----


    private final int value;


    // ----- Constructors -----


    public ASTInt(int value) {
        this.value = value;
    }


    // ----- Getters -----


    public int getValue() {
        return value;
    }


    // ----- Override methods -----


    @Override
    public void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException {
        gen.generate(this, target, context);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTInt astInt = (ASTInt) o;
        return value == astInt.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}

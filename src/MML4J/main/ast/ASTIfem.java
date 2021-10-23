package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.EquationGenerator;
import MML4J.main.typist.equation_graph.Node;

import java.util.Map;
import java.util.Objects;

public class ASTIfem extends ASTExpr {

    // ----- Attributes -----

    private final ASTExpr condition;
    private final ASTExpr consequence;
    private final ASTExpr alternative;

    // ----- Constructors -----

    public ASTIfem(ASTExpr condition, ASTExpr consequence, ASTExpr alternative) {
        this.condition = condition;
        this.consequence = consequence;
        this.alternative = alternative;
    }

    // ----- Getters -----

    public ASTExpr getCondition() {
        return condition;
    }

    public ASTExpr getConsequence() {
        return consequence;
    }

    public ASTExpr getAlternative() {
        return alternative;
    }

    // ----- Override methods -----

    @Override
    public void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException {
        gen.generate(this, target, context);
    }

    @Override
    public String toString() {
        return "ifem(" + condition + ") then { " + condition + " } else { " + alternative + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTIfem astIfem = (ASTIfem) o;
        return Objects.equals(condition, astIfem.condition) && Objects.equals(consequence, astIfem.consequence) && Objects.equals(alternative, astIfem.alternative);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, consequence, alternative);
    }

}

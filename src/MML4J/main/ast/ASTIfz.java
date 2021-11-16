package MML4J.main.ast;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.utils.EquationGenerator;
import MML4J.main.typist.equation_system.nodes.Node;

import java.util.Map;
import java.util.Objects;

public class ASTIfz extends ASTExpr {

    // ----- Attributes -----


    protected final ASTExpr condition;
    protected final ASTExpr consequence;
    protected final ASTExpr alternative;


    // ----- Constructors -----


    public ASTIfz(ASTExpr condition, ASTExpr consequence, ASTExpr alternative) {
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
        return "ifz(" + condition + ") then { " + consequence + " } else { " + alternative + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTIfz astIfz = (ASTIfz) o;
        return Objects.equals(condition, astIfz.condition) && Objects.equals(consequence, astIfz.consequence) && Objects.equals(alternative, astIfz.alternative);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, consequence, alternative);
    }

}

package mml4j.main.ast;

import mml4j.main.ast.abstracts.ASTExpr;
import mml4j.main.exceptions.TypingException;
import mml4j.main.typist.EquationGenerator;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;

import java.util.Map;
import java.util.Objects;

public class ASTIfem extends ASTExpr {

    // ----- Attributes -----


    protected final ASTExpr condition;
    protected final ASTExpr consequence;
    protected final ASTExpr alternative;


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
        return "ifem(" + condition + ") then { " + consequence + " } else { " + alternative + " }";
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

package mml4j.main.ast;

import mml4j.main.ast.abstracts.ASTExpr;
import mml4j.main.evaluator.Evaluator;
import mml4j.main.evaluator.values.abstracts.Value;
import mml4j.main.exceptions.EvaluationException;
import mml4j.main.exceptions.TypingException;
import mml4j.main.typist.EquationGenerator;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;

import java.util.Map;
import java.util.Objects;

public class ASTLet extends ASTExpr {

    // ----- Attributes -----


    protected final String name;
    protected final ASTExpr value;
    protected final ASTExpr in;


    // ----- Constructors -----


    public ASTLet(String name, ASTExpr value, ASTExpr in) {
        this.name = name;
        this.value = value;
        this.in = in;
    }


    // ----- Getters -----


    public String getName() {
        return name;
    }

    public ASTExpr getValue() {
        return value;
    }

    public ASTExpr getIn() {
        return in;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "let " + name + " = " + value + " in " + in;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTLet astLet = (ASTLet) o;
        return Objects.equals(name, astLet.name) && Objects.equals(value, astLet.value) && Objects.equals(in, astLet.in);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, in);
    }


    // ----- Class methods -----


    @Override
    public void acceptEqGenerator(EquationGenerator gen, Node target, Map<String, Node> context) throws TypingException {
        gen.generate(this, target, context);
    }

    @Override
    public Value acceptEvaluator(Evaluator evaluator, Map<String, Value> context) throws EvaluationException {
        return evaluator.evaluate(this, context);
    }

}

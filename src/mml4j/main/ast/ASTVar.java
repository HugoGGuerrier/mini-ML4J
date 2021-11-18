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

public class ASTVar extends ASTExpr {

    // ----- Attributes -----


    protected final String name;


    // ----- Constructors -----


    public ASTVar(String name) {
        this.name = name;
    }


    // ----- Getters -----


    public String getName() {
        return name;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTVar var = (ASTVar) o;
        return Objects.equals(name, var.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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

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

public class ASTAbs extends ASTExpr {

    // ----- Attributes -----


    protected final String param;
    protected final ASTExpr body;


    // ----- Constructors -----


    public ASTAbs(String param, ASTExpr body) {
        this.param = param;
        this.body = body;
    }


    // ----- Getters -----


    public String getParam() {
        return param;
    }

    public ASTExpr getBody() {
        return body;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "fn(" + param + "){ " + body.toString() + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASTAbs abs = (ASTAbs) o;
        return Objects.equals(param, abs.param) && Objects.equals(body, abs.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(param, body);
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

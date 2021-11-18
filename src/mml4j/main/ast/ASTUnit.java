package mml4j.main.ast;

import mml4j.main.ast.abstracts.ASTExpr;
import mml4j.main.evaluator.Evaluator;
import mml4j.main.evaluator.values.abstracts.Value;
import mml4j.main.exceptions.EvaluationException;
import mml4j.main.exceptions.TypingException;
import mml4j.main.typist.EquationGenerator;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;

import java.util.Map;

public class ASTUnit extends ASTExpr {

    // ----- Override methods -----


    @Override
    public String toString() {
        return "()";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ASTUnit;
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

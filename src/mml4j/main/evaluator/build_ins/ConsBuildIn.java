package mml4j.main.evaluator.build_ins;

import mml4j.main.evaluator.Evaluator;
import mml4j.main.evaluator.values.FunctionalValue;
import mml4j.main.evaluator.values.ListValue;
import mml4j.main.evaluator.values.abstracts.Value;
import mml4j.main.exceptions.EvaluationException;

import java.util.Map;

/**
 * This class represents the cons build in value
 *
 * @author Hugo GUERRIER
 */
public class ConsBuildIn extends FunctionalValue {
    @Override
    public Value apply(Evaluator evaluator, Value arg, Map<String, Value> context) throws EvaluationException {
        return new ConsP1(arg);
    }

    /**
     * Cons first partial function
     */
    private static class ConsP1 extends FunctionalValue {
        private final Value op1;

        public ConsP1(Value op1) {super(); this.op1 = op1;}

        @Override
        public Value apply(Evaluator evaluator, Value arg, Map<String, Value> context) throws EvaluationException {
            ListValue argList = (ListValue) arg;
            return new ListValue(op1, argList);
        }
    }
}

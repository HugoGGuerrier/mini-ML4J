package mml4j.main.evaluator.build_ins;

import mml4j.main.evaluator.Evaluator;
import mml4j.main.evaluator.values.FunctionalValue;
import mml4j.main.evaluator.values.IntValue;
import mml4j.main.evaluator.values.abstracts.Value;
import mml4j.main.exceptions.EvaluationException;

import java.util.Map;

/**
 * This class represents the sub build in
 *
 * @author Hugo GUERRIER
 */
public class SubBuildIn extends FunctionalValue {
    @Override
    public Value apply(Evaluator evaluator, Value arg, Map<String, Value> context) throws EvaluationException {
        return new SubP1((IntValue) arg);
    }

    /**
     * Sub first partial function
     */
    private static class SubP1 extends FunctionalValue {
        private final IntValue op1;

        public SubP1(IntValue op1) {super(); this.op1 = op1;}

        @Override
        public Value apply(Evaluator evaluator, Value arg, Map<String, Value> context) throws EvaluationException {
            IntValue argInt = (IntValue) arg;
            return new IntValue(op1.getValue() - argInt.getValue());
        }
    }
}

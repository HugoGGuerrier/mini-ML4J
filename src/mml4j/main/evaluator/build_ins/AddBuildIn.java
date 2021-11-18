package mml4j.main.evaluator.build_ins;

import mml4j.main.evaluator.Evaluator;
import mml4j.main.evaluator.values.FunctionalValue;
import mml4j.main.evaluator.values.IntValue;
import mml4j.main.evaluator.values.abstracts.Value;
import mml4j.main.exceptions.EvaluationException;

import java.util.Map;

/**
 * This class is the add operator build in
 *
 * @author Hugo GUERRIER
 */
public class AddBuildIn extends FunctionalValue {
    @Override
    public Value apply(Evaluator evaluator, Value arg, Map<String, Value> context) throws EvaluationException {
        return new AddP1((IntValue) arg);
    }

    /**
     * This class represent the first partial function of add
     */
    private static class AddP1 extends FunctionalValue {
        private final IntValue op1;

        public AddP1(IntValue op1) {super(); this.op1 = op1;}

        @Override
        public Value apply(Evaluator evaluator, Value arg, Map<String, Value> context) throws EvaluationException {
            IntValue argInt = (IntValue) arg;
            return new IntValue(op1.getValue() + argInt.getValue());
        }
    }
}

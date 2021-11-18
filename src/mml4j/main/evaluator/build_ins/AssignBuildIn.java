package mml4j.main.evaluator.build_ins;

import mml4j.main.evaluator.Evaluator;
import mml4j.main.evaluator.values.FunctionalValue;
import mml4j.main.evaluator.values.RefValue;
import mml4j.main.evaluator.values.UnitValue;
import mml4j.main.evaluator.values.abstracts.Value;
import mml4j.main.exceptions.EvaluationException;

import java.util.Map;

/**
 * This class represent the affectation operation functional value
 *
 * @author Hugo GUERRIER
 */
public class AssignBuildIn extends FunctionalValue {
    @Override
    public Value apply(Evaluator evaluator, Value arg, Map<String, Value> context) throws EvaluationException {
        return new AssignP1((RefValue) arg);
    }

    /**
     * Assign first partial function
     */
    private static class AssignP1 extends FunctionalValue {
        private final RefValue op1;

        public AssignP1(RefValue op1) {super(); this.op1 = op1;}

        @Override
        public Value apply(Evaluator evaluator, Value arg, Map<String, Value> context) throws EvaluationException {
            context.put(op1.getReferenceName(), arg);
            return UnitValue.getInstance();
        }
    }
}

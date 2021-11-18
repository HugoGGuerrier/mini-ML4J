package mml4j.main.evaluator.build_ins;

import mml4j.main.evaluator.Evaluator;
import mml4j.main.evaluator.values.FunctionalValue;
import mml4j.main.evaluator.values.ListValue;
import mml4j.main.evaluator.values.abstracts.Value;
import mml4j.main.exceptions.EvaluationException;

import java.util.Map;

/**
 * This class represents the tail build in functional value
 *
 * @author Hugo GUERRIER
 */
public class TailBuildIn extends FunctionalValue {
    @Override
    public Value apply(Evaluator evaluator, Value arg, Map<String, Value> context) throws EvaluationException {
        return ((ListValue) arg).getTail();
    }
}

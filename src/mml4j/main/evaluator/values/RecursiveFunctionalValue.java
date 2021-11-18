package mml4j.main.evaluator.values;

import mml4j.main.Utils;
import mml4j.main.ast.abstracts.ASTExpr;
import mml4j.main.evaluator.Evaluator;
import mml4j.main.evaluator.values.abstracts.Value;
import mml4j.main.exceptions.EvaluationException;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a recursive functional value that can be applied
 *
 * @author Hugo GUERRIER
 */
public class RecursiveFunctionalValue extends FunctionalValue {

    // ----- Attributes -----


    /** The function name in the recursive context */
    protected final String name;


    // ----- Constructors -----


    /**
     * Create a new recursive functional value with its name
     *
     * @param name The recursive name
     * @param param The parameter
     * @param body The function body
     */
    public RecursiveFunctionalValue(String name, String param, ASTExpr body, Map<String, Value> context) {
        super(param, body, context);
        this.name = name;
    }


    // ----- Getters -----


    public String getName() {
        return name;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "rec " + name + " (" + param + "){" + body + "}";
    }


    // ----- Class methods -----


    @Override
    public Value apply(Evaluator evaluator, Value arg, Map<String, Value> context) throws EvaluationException {
        Map<String, Value> contextCopy = Utils.cloneMap(context);
        contextCopy.putAll(capturedContext);
        contextCopy.put(param, arg);
        contextCopy.put(name, this);
        return body.acceptEvaluator(evaluator, contextCopy);
    }

}

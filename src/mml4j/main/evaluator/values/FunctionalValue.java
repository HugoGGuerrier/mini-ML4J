package mml4j.main.evaluator.values;

import mml4j.main.Utils;
import mml4j.main.ast.abstracts.ASTExpr;
import mml4j.main.evaluator.Evaluator;
import mml4j.main.evaluator.values.abstracts.Value;
import mml4j.main.exceptions.EvaluationException;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a functional value
 *
 * @author Hugo GUERRIER
 */
public class FunctionalValue extends Value {

    // ----- Attributes -----


    /** The function parameter */
    protected final String param;

    /** The function body */
    protected final ASTExpr body;

    /** The function captured context */
    protected final Map<String, Value> capturedContext;


    // ----- Constructors -----


    /**
     * This is the default constructor for the build in
     */
    public FunctionalValue() {
        this(null, null, null);
    }

    /**
     * Create a new function value with the param and the body
     *
     * @param param The function param
     * @param body The function body
     */
    public FunctionalValue(String param, ASTExpr body, Map<String, Value> context) {
        this.param = param;
        this.body = body;
        this.capturedContext = context == null ? new HashMap<>() : Utils.cloneMap(context);
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
        return "fn(" + param + "){" + body + "}";
    }


    // ----- Class methods -----


    /**
     * Apply the functional value to a wanted arg in the evaluator
     *
     * @param evaluator The evaluator
     * @param arg The argument
     * @return The result of the function application
     * @throws EvaluationException If the body cannot be evaluated
     */
    public Value apply(Evaluator evaluator, Value arg, Map<String, Value> context) throws EvaluationException {
        Map<String, Value> contextCopy = Utils.cloneMap(context);
        contextCopy.putAll(capturedContext);
        contextCopy.put(param, arg);
        return body.acceptEvaluator(evaluator, contextCopy);
    }

}

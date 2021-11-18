package mml4j.main.evaluator;

import mml4j.main.ast.*;
import mml4j.main.evaluator.utils.BaseContext;
import mml4j.main.evaluator.values.*;
import mml4j.main.evaluator.values.abstracts.Value;
import mml4j.main.exceptions.EvaluationException;

import java.util.Map;

/**
 * This class represents the miniML evaluator
 */
public class Evaluator {

    // ----- Attributes -----


    /** The address counter to get new references */
    private int addressCounter;


    // ----- Constructors -----


    public Evaluator() {
        this.addressCounter = 0;
    }


    // ----- Class methods -----


    public String getNextAddress() {
        return "#" + addressCounter++;
    }


    // ----- Evaluation methods -----


    // --- Function definition and application

    // Evaluate an abstraction
    public Value evaluate(ASTAbs abs, Map<String, Value> context) throws EvaluationException {
        return new FunctionalValue(abs.getParam(), abs.getBody(), context);
    }

    // Evaluate a recursive abstraction
    public Value evaluate(ASTAbsRec absRec, Map<String, Value> context) throws EvaluationException {
        return new RecursiveFunctionalValue(absRec.getName(), absRec.getParam(), absRec.getBody(), context);
    }

    // Evaluate an application
    public Value evaluate(ASTApp app, Map<String, Value> context) throws EvaluationException {
        // Get the value of the function and the arg
        FunctionalValue function = (FunctionalValue) app.getFunction().acceptEvaluator(this, context);
        Value arg = app.getArg().acceptEvaluator(this, context);

        // Return the result of the application
        return function.apply(this, arg, context);
    }


    // --- Control structures

    // Evaluate an if empty
    public Value evaluate(ASTIfem ifem, Map<String, Value> context) throws EvaluationException {
        // Evaluate the condition
        ListValue condition = (ListValue) ifem.getCondition().acceptEvaluator(this, context);

        // Test and return the correct value
        if(condition == NilValue.getInstance()) {
            return ifem.getConsequence().acceptEvaluator(this, context);
        } else {
            return ifem.getAlternative().acceptEvaluator(this, context);
        }
    }

    // Evaluate an if zero
    public Value evaluate(ASTIfz ifz, Map<String, Value> context) throws EvaluationException {
        // Evaluate the condition
        IntValue condition = (IntValue) ifz.getCondition().acceptEvaluator(this, context);

        // Test and return the correct value
        if(condition.getValue() == 0) {
            return ifz.getConsequence().acceptEvaluator(this, context);
        } else {
            return ifz.getAlternative().acceptEvaluator(this, context);
        }
    }

    // Evaluate a let
    public Value evaluate(ASTLet let, Map<String, Value> context) throws EvaluationException {
        // Evaluate the value
        Value value = let.getValue().acceptEvaluator(this, context);

        // Save the old value in the context and override it
        Value oldValue = context.getOrDefault(let.getName(), null);
        context.put(let.getName(), value);
        Value res = let.getIn().acceptEvaluator(this, context);

        // Restore the old context
        context.put(let.getName(), oldValue);

        // Return the result
        return res;
    }


    // --- Basic operations

    // Evaluate an integer
    public Value evaluate(ASTInt intg, Map<String, Value> context) throws EvaluationException {
        return new IntValue(intg.getValue());
    }

    // Evaluate a nil
    public Value evaluate(ASTNil nil, Map<String, Value> context) throws EvaluationException {
        return NilValue.getInstance();
    }

    // Evaluate a unit
    public Value evaluate(ASTUnit unit, Map<String, Value> context) throws EvaluationException {
        return UnitValue.getInstance();
    }

    // Evaluate a variable
    public Value evaluate(ASTVar var, Map<String, Value> context) throws EvaluationException {
        // Get the value in the user context
        Value res = context.getOrDefault(var.getName(), null);

        // Get the value in the base context (override the user context)
        res = BaseContext.getBaseContext().getOrDefault(var.getName(), res);

        // Test if res is null
        if(res == null) throw new EvaluationException("Var " + var.getName() + " doesn't exists in the context");

        // Return the result
        return res;
    }

}

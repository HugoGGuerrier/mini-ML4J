package mml4j.main.evaluator.values;

import mml4j.main.evaluator.values.abstracts.Value;

/**
 * This class represents the only instance of the weak value, this is the default value in every expression
 *
 * @author Hugo GUERRIER
 */
public class WeakValue extends Value {

    // ----- Attributes -----


    private static WeakValue instance = null;


    // ----- Constructors -----


    private WeakValue() {}

    public static WeakValue getInstance() {
        if(instance == null) instance = new WeakValue();
        return instance;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "Weak";
    }

}

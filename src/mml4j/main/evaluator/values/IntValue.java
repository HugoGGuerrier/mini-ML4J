package mml4j.main.evaluator.values;

import mml4j.main.evaluator.values.abstracts.Value;

/**
 * This class represents the integer value inside MML
 *
 * @author Hugo GUERRIER
 */
public class IntValue extends Value {

    // ----- Attributes -----


    /** This is the integer value */
    protected final int value;


    // ----- Constructors -----


    /**
     * Create a new int value with its internal value
     *
     * @param value The value
     */
    public IntValue(int value) {
        this.value = value;
    }


    // ----- Getters -----


    public int getValue() {
        return value;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return String.valueOf(value);
    }

}

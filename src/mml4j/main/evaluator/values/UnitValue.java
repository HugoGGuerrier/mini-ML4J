package mml4j.main.evaluator.values;

import mml4j.main.evaluator.values.abstracts.Value;

/**
 * This class represents the only instance of the unit value
 *
 * @author Hugo GUERRIER
 */
public class UnitValue extends Value {

    // ----- Attributes -----


    private static UnitValue instance = null;


    // ----- Constructors -----


    private UnitValue() {}

    public static UnitValue getInstance() {
        if(instance == null) instance = new UnitValue();
        return instance;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "Unit";
    }

}

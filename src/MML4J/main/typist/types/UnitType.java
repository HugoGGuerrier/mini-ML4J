package MML4J.main.typist.types;

import MML4J.main.typist.types.abstracts.Type;

/**
 * This class represent the only instance of the unit type
 *
 * @author Hugo GUERRIER
 */
public class UnitType extends Type {

    // ----- Attributes -----


    protected static UnitType instance = null;


    // ----- Constructors -----


    private UnitType() {}

    public static UnitType getInstance() {
        if(instance == null) instance = new UnitType();
        return instance;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "unit";
    }

}

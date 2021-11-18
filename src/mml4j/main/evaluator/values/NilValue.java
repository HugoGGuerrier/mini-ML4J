package mml4j.main.evaluator.values;

/**
 * This class represents the only instance of the nil value
 *
 * @author Hugo GUERRIER
 */
public class NilValue extends ListValue {

    // ----- Attributes -----


    private static NilValue instance = null;


    // ----- Constructors -----


    private NilValue() {
        super(null, null);
    }

    public static NilValue getInstance() {
        if(instance == null) instance = new NilValue();
        return instance;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "Nil";
    }

}

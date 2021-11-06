package MML4J.main.typist.type;

public class IntType extends Type {

    // ----- Attributes -----


    private static IntType instance;


    // ----- Constructors -----


    private IntType() {}

    public static IntType getInstance() {
        if(instance == null) instance = new IntType();
        return instance;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "int";
    }

}

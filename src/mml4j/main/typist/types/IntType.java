package mml4j.main.typist.types;

import mml4j.main.typist.types.abstracts.Type;

/**
 * This class represent the only instance of the int type
 *
 * @author Hugo GUERRIER
 */
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

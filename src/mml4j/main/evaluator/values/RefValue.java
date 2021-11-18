package mml4j.main.evaluator.values;

import mml4j.main.evaluator.values.abstracts.Value;

public class RefValue extends Value {

    // ----- Attributes -----


    /** The reference name in the context */
    protected final String referenceName;


    // ----- Constructors -----


    /**
     * Create a new reference value with the reference name
     *
     * @param reference The reference name
     */
    public RefValue(String reference) {
        this.referenceName = reference;
    }


    // ----- Getters -----


    public String getReferenceName() {
        return referenceName;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "Ref(" + referenceName + ")";
    }

}

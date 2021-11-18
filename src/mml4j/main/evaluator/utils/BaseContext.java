package mml4j.main.evaluator.utils;

import mml4j.main.evaluator.build_ins.*;
import mml4j.main.evaluator.values.abstracts.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the MML evaluator base context
 *
 * @author Hugo GUERRIER
 */
public class BaseContext {

    // ----- Attributes -----


    /** The instance of base context */
    protected static Map<String, Value> baseContext = null;


    // ----- Getters -----


    /**
     * Get the base context and crete it if it's not
     *
     * @return The base context
     */
    public static Map<String, Value> getBaseContext() {
        if(baseContext == null) createBaseContext();
        return baseContext;
    }


    // ----- Class methods -----


    /**
     * Create the base context
     */
    protected static void createBaseContext() {
        // --- Create the base context
        baseContext = new HashMap<>();

        // --- Add the add operation
        baseContext.put("+", new AddBuildIn());

        // --- Add the sub operation
        baseContext.put("-", new SubBuildIn());

        // --- Add the cons build in
        baseContext.put("cons", new ConsBuildIn());

        // --- Add the head build in
        baseContext.put("head", new HeadBuildIn());

        // --- Add the tail build in
        baseContext.put("tail", new TailBuildIn());

        // --- Add the assign operation
        baseContext.put(":=", new AssignBuildIn());

        // --- Add the ref operation
        baseContext.put("@", new RefBuildIn());

        // --- Add the deref operation
        baseContext.put("!", new DerefBuildIn());
    }

}

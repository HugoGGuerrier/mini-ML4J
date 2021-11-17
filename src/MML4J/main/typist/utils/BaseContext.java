package MML4J.main.typist.utils;

import MML4J.main.typist.equation_system.nodes.*;
import MML4J.main.typist.equation_system.nodes.abstracts.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains the typing base context
 *
 * @author Hugo GUERRIER
 */
public class BaseContext {

    // ----- Attributes -----


    /** The typist base context */
    protected static Map<String, Node> baseContext = null;


    // ----- Getters -----


    /**
     * Get the typing base context
     *
     * @return The base context
     */
    public static Map<String, Node> getBaseContext() {
        if(baseContext == null) createBaseContext();
        return baseContext;
    }


    // ----- Class methods -----


    /**
     * Create the base typing context
     */
    protected static void createBaseContext() {
        // --- Create the context
        baseContext = new HashMap<>();

        // --- Add the weak type
        ForAllNode weakType = new ForAllNode();
        SimpleNode weakNode = weakType.getNewNode();
        weakType.setType(weakNode);

        baseContext.put("--weak", weakType);

        // --- Add the add operation
        baseContext.put("+",
                new ArrowNode(IntNode.getInstance(), new ArrowNode(IntNode.getInstance(), IntNode.getInstance()))
        );

        // --- Add the sub operation
        baseContext.put("-",
                new ArrowNode(IntNode.getInstance(), new ArrowNode(IntNode.getInstance(), IntNode.getInstance()))
        );

        // --- Add the cons operation
        ForAllNode consType = new ForAllNode();
        SimpleNode consVal = consType.getNewNode();
        ListNode consList = new ListNode(consVal);
        consType.setType(new ArrowNode(consVal, new ArrowNode(consList, consList)));

        baseContext.put("cons", consType);

        // --- Add the head operation
        ForAllNode headType = new ForAllNode();
        SimpleNode headVal = headType.getNewNode();
        ListNode headList = new ListNode(headVal);
        headType.setType(new ArrowNode(headList, headVal));

        baseContext.put("head", headType);

        // --- Add the tail operation
        ForAllNode tailType = new ForAllNode();
        SimpleNode tailVal = tailType.getNewNode();
        ListNode tailList = new ListNode(tailVal);
        tailType.setType(new ArrowNode(tailList, tailList));

        baseContext.put("tail", tailType);

        // --- Add the assign operation
        ForAllNode assignType = new ForAllNode();
        SimpleNode assignVal = assignType.getNewNode();
        RefNode assignRef = new RefNode(assignVal);
        assignType.setType(new ArrowNode(assignRef, new ArrowNode(assignVal, UnitNode.getInstance())));

        baseContext.put(":=", assignType);

        // --- Add the ref operation
        ForAllNode refType = new ForAllNode();
        SimpleNode refVal = refType.getNewNode();
        RefNode refRef = new RefNode(refVal);
        refType.setType(new ArrowNode(refVal, refRef));

        baseContext.put("@", refType);

        // --- Add the deref operation
        ForAllNode derefType = new ForAllNode();
        SimpleNode derefVal = derefType.getNewNode();
        RefNode derefRef = new RefNode(derefVal);
        derefType.setType(new ArrowNode(derefRef, derefVal));

        baseContext.put("!", derefType);
    }
}

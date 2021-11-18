package mml4j.main.typist.utils;

import mml4j.main.typist.equation_system.nodes.*;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;
import mml4j.main.typist.types.*;
import mml4j.main.typist.types.abstracts.Type;

import java.util.HashMap;
import java.util.Map;

/**
 * This class translate a node into a usable type
 *
 * @author Hugo GUERRIER
 */
public class TypeTranslator {

    // ----- Attributes -----


    /** The type counter to name all types */
    private int counter;

    /** Corresponding map */
    private final Map<Node, Type> typeMap;


    // ----- Constructors -----


    /**
     * Create a new type translator with default values
     */
    public TypeTranslator() {
        this.counter = 0;
        this.typeMap = new HashMap<>();
    }


    // ----- Internal methods -----


    /**
     * Get the next type
     *
     * @return A new fresh type
     */
    private SimpleType getNewType() {
        return new SimpleType(counter++);
    }

    /**
     * Get the corresponding node type or a new one if none exists
     *
     * @param node The node to look for
     * @return The (newly created) corresponding type
     */
    private Type getNewType(Node node) {
        Type res = typeMap.getOrDefault(node, null);
        if(res == null) {
            res = getNewType();
            typeMap.put(node, res);
        }
        return res;
    }


    // ----- Translation methods -----


    // Translate an arrow node
    public Type translate(ArrowNode arrowNode) {
        return new ArrowType(arrowNode.getLeft().acceptTranslator(this), arrowNode.getRight().acceptTranslator(this));
    }

    // Translate an integer node
    public Type translate(IntNode intNode) {
        return IntType.getInstance();
    }

    // Translate a list node
    public Type translate(ListNode listNode) {
        return new ListType(listNode.getListType().acceptTranslator(this));
    }

    // Translate a reference node
    public Type translate(RefNode refNode) {
        return new RefType(refNode.getRefType().acceptTranslator(this));
    }

    // Translate a simple node
    public Type translate(SimpleNode simpleNode) {
        return getNewType(simpleNode);
    }

    // Translate a unit node
    public Type translate(UnitNode unitNode) {
        return UnitType.getInstance();
    }

}

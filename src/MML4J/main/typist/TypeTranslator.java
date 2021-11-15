package MML4J.main.typist;

import MML4J.main.typist.equation_system.*;
import MML4J.main.typist.type.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This class translate a type graph into a type
 */
public class TypeTranslator {

    // ----- Attributes -----


    private int counter;
    private final Map<Node, Type> typeMap;


    // ----- Constructors -----


    public TypeTranslator() {
        this.counter = 0;
        this.typeMap = new HashMap<>();
    }


    // ----- Internal methods -----


    private SimpleType getNextType() {
        return new SimpleType(counter++);
    }

    private Type typeForNode(Node node) {
        Type res = typeMap.getOrDefault(node, null);
        if(res == null) {
            res = getNextType();
            typeMap.put(node, res);
        }
        return res;
    }


    // ----- Class methods -----


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
        return new ListType(listNode.getType().acceptTranslator(this));
    }

    // Translate a reference node
    public Type translate(RefNode refNode) {
        return new RefType(refNode.getContent().acceptTranslator(this));
    }

    // Translate a simple node
    public Type translate(SimpleNode simpleNode) {
        return typeForNode(simpleNode);
    }

    // Translate a unit node
    public Type translate(UnitNode unitNode) {
        return UnitType.getInstance();
    }

}

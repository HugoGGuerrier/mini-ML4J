package MML4J.main.typist;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_graph.*;
import MML4J.main.typist.type.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This class translate a type graph into a type
 */
public class TypeTranslator {

    // ----- Attributes -----


    private int counter;
    private Map<Node, Type> typeMap;


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
    public Type translate(ArrowNode arrowNode) throws TypingException {
        if(arrowNode.getChildren().size() > 0) throw new TypingException("Cannot type a non-childless node");
        return new ArrowType(arrowNode.getLeft().acceptTranslator(this), arrowNode.getRight().acceptTranslator(this));
    }

    // Translate an integer node
    public Type translate(IntNode intNode) throws TypingException {
        if(intNode.getChildren().size() > 0) throw new TypingException("Cannot type a non-childless node");
        return IntType.getInstance();
    }

    public Type translate(ListNode listNode) throws TypingException {
        if(listNode.getChildren().size() > 0) throw new TypingException("Cannot type a non-childless node");
        return new ListType(listNode.getType().acceptTranslator(this));
    }

    // Translate a simple node
    public Type translate(SimpleNode simpleNode) throws TypingException {
        if(simpleNode.getChildren().size() > 0) throw new TypingException("Cannot type a non-childless node");
        return typeForNode(simpleNode);
    }

}

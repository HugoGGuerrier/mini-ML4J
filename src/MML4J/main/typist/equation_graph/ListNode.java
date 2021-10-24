package MML4J.main.typist.equation_graph;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;

public class ListNode extends Node {

    // ----- Attributes -----


    private final Node type;


    // ----- Constructors -----


    public ListNode(Node type) {
        this.type = type;
    }


    // ----- Getters -----


    public Node getType() {
        return type;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "[" + type + "]";
    }

    @Override
    public boolean structEquals(Node other) {
        if(this == other) return true;
        if(other == null || getClass() != other.getClass()) return false;
        ListNode listNode = (ListNode) other;

        return type.structEquals(listNode.type);
    }

    @Override
    public boolean isSpecific() {
        return true;
    }


    // ----- Class methods -----


    @Override
    public Node unify() throws TypingException {
        return null;
    }

    @Override
    public Node merge(Node other) throws TypingException {
        return null;
    }

    @Override
    public boolean contains(Node other) {
        return false;
    }

    @Override
    public Type acceptTranslator(TypeTranslator translator) throws TypingException {
        return translator.translate(this);
    }

}

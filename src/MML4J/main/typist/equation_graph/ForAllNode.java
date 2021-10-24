package MML4J.main.typist.equation_graph;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;

import java.util.LinkedList;

public class ForAllNode extends Node {

    // ----- Attributes -----


    private int counter;
    private LinkedList<Node> localNodes;

    @Override
    public boolean structEquals(Node other) {
        return false;
    }

    @Override
    public boolean isSpecific() {
        return false;
    }

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
        return null;
    }

}

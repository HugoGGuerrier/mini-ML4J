package MML4J.main.typist.equation_graph;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.Generalizer;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ForAllNode extends Node {

    // ----- Attributes -----


    private int counter;
    private Set<SimpleNode> generalizedNodes;
    private Node type;


    // ----- Constructors -----


    public ForAllNode() {
        counter = 0;
        generalizedNodes = new HashSet<>();
        type = null;
    }


    // ----- Getters -----


    public Set<SimpleNode> getGeneralizedNodes() {
        return generalizedNodes;
    }

    public Node getType() {
        return type;
    }


    // ----- Setters -----


    public void setType(Node type) {
        this.type = type;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "for_all" + generalizedNodes + " " + type;
    }

    @Override
    public boolean structEquals(Node other) {
        if(this == other) return true;
        if(other == null || getClass() != other.getClass()) return false;
        ForAllNode forAllOther = (ForAllNode) other;

        boolean localNodesEqual = generalizedNodes.size() == forAllOther.generalizedNodes.size();
        for(SimpleNode general : generalizedNodes) {
            if (!forAllOther.generalizedNodes.contains(general)) {
                localNodesEqual = false;
                break;
            }
        }

        return localNodesEqual && type.structEquals(forAllOther.type);
    }

    @Override
    public boolean isSpecific() {
        return true;
    }


    // ----- Class methods -----


    public SimpleNode getNextNode() {
        SimpleNode res = new SimpleNode(counter++);
        generalizedNodes.add(res);
        return res;
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
        throw new TypingException("Cannot translate the for all node");
    }

    @Override
    public Node acceptGeneralizer(Generalizer generalizer) {
        return generalizer.generalize(this);
    }

}

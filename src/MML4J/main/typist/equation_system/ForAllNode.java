package MML4J.main.typist.equation_system;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;
import MML4J.main.typist.utils.Generalizer;
import MML4J.main.typist.utils.Ungeneralizer;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represent a generalist type
 *
 * @author Hugo GUERRIER
 */
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
    public void merge(Node other, EquationSystem system) throws TypingException {
        throw new TypingException("Cannot merge a for all node");
    }

    @Override
    public boolean contains(Node other) {
        return false;
    }

    @Override
    public Type acceptTranslator(TypeTranslator translator) {
        return null;
    }

    @Override
    public Node acceptGeneralizer(Generalizer generalizer) {
        return generalizer.generalize(this);
    }

    @Override
    public Node acceptUngeneralizer(Ungeneralizer ungeneralizer) {
        return ungeneralizer.ungeneralize(this);
    }

}

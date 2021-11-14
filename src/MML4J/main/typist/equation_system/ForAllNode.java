package MML4J.main.typist.equation_system;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;
import MML4J.main.typist.utils.Generalizer;
import MML4J.main.typist.utils.NodePair;
import MML4J.main.typist.utils.Ungeneralizer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This class represent a generalist type
 *
 * @author Hugo GUERRIER
 */
public class ForAllNode extends Node {

    // ----- Attributes -----


    private int counter;
    private Node type;
    private final Set<SimpleNode> generalizedNodes;
    private final List<NodePair> externalConstraints;


    // ----- Constructors -----


    public ForAllNode() {
        counter = 1;
        type = null;
        generalizedNodes = new HashSet<>();
        externalConstraints = new LinkedList<>();
    }


    // ----- Getters -----


    public Set<SimpleNode> getGeneralizedNodes() {
        return generalizedNodes;
    }

    public Node getType() {
        return type;
    }

    public List<NodePair> getExternalConstraints() {
        return externalConstraints;
    }


    // ----- Setters -----


    public void setType(Node type) {
        this.type = type;
    }

    public void addExternalConstraint(Node externalNode, Node internalNode) {
        NodePair extConst = new NodePair(externalNode, internalNode);
        externalConstraints.add(extConst);
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "for_all" + generalizedNodes + " " + type +
                (!externalConstraints.isEmpty() ? " | External constraint : " + externalConstraints : "");
    }

    @Override
    public boolean isSpecific() {
        return true;
    }

    @Override
    public Node instantiate(EquationSystem system) {
        return Ungeneralizer.ungeneralize(this, system);
    }


    // ----- Class methods -----


    public SimpleNode getNewNode() {
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

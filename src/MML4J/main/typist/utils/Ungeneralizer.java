package MML4J.main.typist.utils;

import MML4J.main.typist.equation_system.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is used to instantiate "for all" node, its name is not final
 *
 * @author Hugo GUERRIER
 */
public class Ungeneralizer {

    // ----- Attributes -----


    private final Set<Node> generalNodes;
    private final HashMap<Node, Node> processedNodes;


    // ----- Constructors -----


    private Ungeneralizer(Set<Node> generalNodes) {
        this.generalNodes = generalNodes;
        this.processedNodes = new HashMap<>();
    }


    // ----- Class methods -----


    public static Node ungeneralize(ForAllNode forAllNode, EquationSystem system) {
        // Prepare the ungeneralizer and the result
        Ungeneralizer ungeneralizer = new Ungeneralizer(new HashSet<>(forAllNode.getGeneralizedNodes()));
        Node res = forAllNode.getType().acceptUngeneralizer(ungeneralizer);

        // For each external constraint, add it to the system
        for(NodePair extConst : forAllNode.getExternalConstraints()) {
            system.addEquation(extConst.getLeft(), extConst.getRight().acceptUngeneralizer(ungeneralizer));
        }

        // Return the result
        return res;
    }


    // ----- Ungeneralize methods -----


    // Ungeneralize an arrow node
    public Node ungeneralize(ArrowNode arrowNode) {
        return new ArrowNode(arrowNode.getLeft().acceptUngeneralizer(this), arrowNode.getRight().acceptUngeneralizer(this));
    }

    // Ungeneralize a for all node
    public Node ungeneralize(ForAllNode forAllNode) {
        ForAllNode res = new ForAllNode();
        res.getGeneralizedNodes().addAll(forAllNode.getGeneralizedNodes());
        res.setType(forAllNode.getType().acceptUngeneralizer(this));
        return res;
    }

    // Ungeneralize an int node
    public Node ungeneralize(IntNode intNode) {
        return intNode;
    }

    // Ungeneralize a list node
    public Node ungeneralize(ListNode listNode) {
        return new ListNode(listNode.getType().acceptUngeneralizer(this));
    }

    // Ungeneralize a reference node
    public Node ungeneralize(RefNode refNode) {
        return new RefNode(refNode.getContent().acceptUngeneralizer(this));
    }

    // Ungeneralize a simple node
    public Node ungeneralize(SimpleNode simpleNode) {
        if(!generalNodes.contains(simpleNode)) {
            return simpleNode;
        }
        Node res = processedNodes.getOrDefault(simpleNode, null);
        if(res == null) {
            res = new SimpleNode(-simpleNode.getId());
            processedNodes.put(simpleNode, res);
        }
        return res;
    }

    // Ungeneralize a unit node
    public Node ungeneralize(UnitNode unitNode) {
        return unitNode;
    }

}

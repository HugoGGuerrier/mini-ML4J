package MML4J.main.typist.utils;

import MML4J.main.typist.equation_system.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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


    public static Node ungenralize(ForAllNode forAllNode) {
        Ungeneralizer ungeneralizer = new Ungeneralizer(new HashSet<>(forAllNode.getGeneralizedNodes()));
        return forAllNode.getType().acceptUngeneralizer(ungeneralizer);
    }


    // ----- Ungeneralize methods -----


    // Generalize an arrow node
    public Node ungeneralize(ArrowNode arrowNode) {
        return new ArrowNode(arrowNode.getLeft().acceptUngeneralizer(this), arrowNode.getRight().acceptUngeneralizer(this));
    }

    // Generalize a for all node
    public Node ungeneralize(ForAllNode forAllNode) {
        ForAllNode res = new ForAllNode();
        res.getGeneralizedNodes().addAll(forAllNode.getGeneralizedNodes());
        res.setType(forAllNode.getType().acceptUngeneralizer(this));
        return res;
    }

    // Generalize an int node
    public Node ungeneralize(IntNode intNode) {
        return intNode;
    }

    // Generalize a list node
    public Node ungeneralize(ListNode listNode) {
        return new ListNode(listNode.getType().acceptUngeneralizer(this));
    }

    // Generalize a simple node
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

}

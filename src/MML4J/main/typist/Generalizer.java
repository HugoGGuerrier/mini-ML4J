package MML4J.main.typist;

import MML4J.main.typist.equation_graph.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Generalizer {

    // ----- Attributes -----


    private final ForAllNode generalNode;
    private final Set<Node> linked;


    // ----- Constructor -----


    private Generalizer(ForAllNode generalNode, Set<Node> linked) {
        this.generalNode = generalNode;
        this.linked = linked;
    }


    // ----- Class methods -----


    public static ForAllNode generalize(Node toGeneralize, Map<String, Node> context) {
        ForAllNode res = new ForAllNode();
        Generalizer generalizer = new Generalizer(res, new HashSet<>(context.values()));
        res.setType(toGeneralize.acceptGeneralizer(generalizer));
        return res;
    }


    // ----- Generalization methods -----


    // Generalize an arrow node
    public Node generalize(ArrowNode arrowNode) {
        return new ArrowNode(arrowNode.getLeft().acceptGeneralizer(this), arrowNode.getRight().acceptGeneralizer(this));
    }

    // Generalize a for all node
    public Node generalize(ForAllNode forAllNode) {
        linked.addAll(forAllNode.getGeneralizedNodes());
        ForAllNode res = new ForAllNode();
        res.getGeneralizedNodes().addAll(forAllNode.getGeneralizedNodes());
        res.setType(forAllNode.getType().acceptGeneralizer(this));
        return res;
    }

    // Generalize an int node
    public Node generalize(IntNode intNode) {
        return intNode;
    }

    // Generalize a list node
    public Node generalize(ListNode listNode) {
        return new ListNode(listNode.getType().acceptGeneralizer(this));
    }

    // Generalize a simple node
    public Node generalize(SimpleNode simpleNode) {
        if(linked.contains(simpleNode)) {
            return simpleNode;
        }
        return generalNode.getNextNode();
    }

}

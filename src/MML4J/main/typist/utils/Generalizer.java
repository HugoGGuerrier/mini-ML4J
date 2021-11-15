package MML4J.main.typist.utils;

import MML4J.main.typist.equation_system.*;

import java.util.*;

/**
 * This class is used to construct "for all" node from any source node and context
 *
 * @author Hugo GUERRIER
 */
public class Generalizer {

    // ----- Attributes -----


    private final ForAllNode generalNode;
    private final Set<Node> linked;
    private final Map<Node, Node> processedNodes;


    // ----- Constructor -----


    private Generalizer(ForAllNode generalNode, Set<Node> linked) {
        this.generalNode = generalNode;
        this.linked = linked;
        this.processedNodes = new HashMap<>();
    }


    // ----- Class methods -----


    public static ForAllNode generalize(Node toGeneralize, Map<String, Node> context) {
        return generalize(toGeneralize, context, null);
    }
    
    public static ForAllNode generalize(Node toGeneralize, Map<String, Node> context, List<NodePair> externalEquations) {
        // Prepare the generalisation result
        ForAllNode res = new ForAllNode();

        // Create the generalizer and generalize the type to process
        Generalizer generalizer = new Generalizer(res, new HashSet<>(context.values()));
        res.setType(toGeneralize.acceptGeneralizer(generalizer));

        // If the external equations is not null, process it
        if(externalEquations != null) {
            for(NodePair extEq : externalEquations) {
                res.addExternalConstraint(extEq.getLeft(), extEq.getRight().acceptGeneralizer(generalizer));
                extEq.destroy();
            }
        }

        // Return the result
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

    // Generalize a reference node
    public Node generalize(RefNode refNode) {
        return new RefNode(refNode.getContent().acceptGeneralizer(this));
    }

    // Generalize a simple node
    public Node generalize(SimpleNode simpleNode) {
        if(linked.contains(simpleNode)) {
            return simpleNode;
        }
        Node res = processedNodes.getOrDefault(simpleNode, null);
        if(res == null) {
            res = generalNode.getNewNode();
            processedNodes.put(simpleNode, res);
        }
        return res;
    }

    // Generalize a unit node
    public Node generalize(UnitNode unitNode) {
        return unitNode;
    }

}

package MML4J.main.typist.utils;

import MML4J.main.typist.equation_system.*;
import MML4J.main.typist.equation_system.nodes.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is used to instantiate "for all" node, its name is not final
 *
 * @author Hugo GUERRIER
 */
public class Instantiater {

    // ----- Attributes -----


    private final Set<Node> generalNodes;
    private final HashMap<Node, Node> processedNodes;


    // ----- Constructors -----


    private Instantiater(Set<Node> generalNodes) {
        this.generalNodes = generalNodes;
        this.processedNodes = new HashMap<>();
    }


    // ----- Class methods -----


    public static Node instantiate(ForAllNode forAllNode, EquationSystem system) {
        // Prepare the instantiater and the result
        Instantiater instantiater = new Instantiater(new HashSet<>(forAllNode.getGeneralizedNodes()));
        Node res = forAllNode.getType().acceptInstantiater(instantiater);

        // For each external constraint, add it to the system
        for(Equation extConst : forAllNode.getInstanceConstraints()) {
            system.addEquation(extConst.getLeft(), extConst.getRight().acceptInstantiater(instantiater));
        }

        // Return the result
        return res;
    }


    // ----- Instantiation methods -----


    // Instantiate an arrow node
    public Node instantiate(ArrowNode arrowNode) {
        return new ArrowNode(arrowNode.getLeft().acceptInstantiater(this), arrowNode.getRight().acceptInstantiater(this));
    }

    // Instantiate a for all node
    public Node instantiate(ForAllNode forAllNode) {
        ForAllNode res = new ForAllNode();
        res.getGeneralizedNodes().addAll(forAllNode.getGeneralizedNodes());
        res.setType(forAllNode.getType().acceptInstantiater(this));
        return res;
    }

    // Instantiate an int node
    public Node instantiate(IntNode intNode) {
        return intNode;
    }

    // Instantiate a list node
    public Node instantiate(ListNode listNode) {
        return new ListNode(listNode.getListType().acceptInstantiater(this));
    }

    // Instantiate a reference node
    public Node instantiate(RefNode refNode) {
        return new RefNode(refNode.getRefType().acceptInstantiater(this));
    }

    // Instantiate a simple node
    public Node instantiate(SimpleNode simpleNode) {
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

    // Instantiate a unit node
    public Node instantiate(UnitNode unitNode) {
        return unitNode;
    }

}

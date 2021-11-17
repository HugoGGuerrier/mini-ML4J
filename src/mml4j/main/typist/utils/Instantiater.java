package mml4j.main.typist.utils;

import mml4j.main.typist.equation_system.*;
import mml4j.main.typist.equation_system.nodes.*;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;

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


    /** The general known nodes */
    private final Set<Node> generalNodes;

    /** The already instantiated nodes map */
    private final HashMap<Node, Node> processedNodes;


    // ----- Constructors -----


    /**
     * Create a new instantiater with its general nodes
     *
     * @param generalNodes The general nodes
     */
    private Instantiater(Set<Node> generalNodes) {
        this.generalNodes = generalNodes;
        this.processedNodes = new HashMap<>();
    }


    // ----- Class methods -----


    /**
     * Method to instantiate a for all node in the wanted system
     *
     * @param forAllNode The node to instantiate
     * @param system The system to instantiate the node in
     * @return The newly created node instance
     */
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
        return forAllNode;
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

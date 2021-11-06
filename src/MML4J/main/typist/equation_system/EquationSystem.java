package MML4J.main.typist.equation_system;

import MML4J.main.Utils;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.utils.NodePair;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents an equation system
 *
 * @author Hugo GUERRIER
 */
public class EquationSystem {

    // ----- Attributes -----


    private Node initNode;
    private NodePair initialEquation;
    private final List<NodePair> equations;


    // ----- Constructors -----


    public EquationSystem() {
        this.initNode = null;
        this.equations = new LinkedList<>();
    }


    // ----- Getters -----


    public Node getInitNode() {
        return initNode;
    }


    // ----- Setters -----


    public void setInitNode(Node initNode) {
        this.initNode = initNode;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("INIT : ").append(initialEquation).append("\n");

        for(NodePair equation : equations) {
            res.append(equation).append("\n");
        }

        return res.toString();
    }


    // ----- Class methods -----


    public void addEquation(Node left, Node right) {
        NodePair newEquation = new NodePair(left, right);
        if(initialEquation == null) {
            if(left == initNode) {
                initialEquation = newEquation;
                return;
            }
            else if(right == initNode) {
                newEquation.reverse();
                initialEquation = newEquation;
                return;
            }
        }
        equations.add(newEquation);
    }

    public Node unify() throws TypingException {
        // While the equations size is more than 1
        while(equations.size() > 0) {
            // Get the head of the list
            NodePair equation = equations.remove(0);
            Node left = equation.getLeft().instantiate();
            Node right = equation.getRight().instantiate();

            // Remove the equation from the system
            equation.destroy();

            // If the left and the right are not the same
            if(left != right) {
                // Test containing
                if(left.contains(right) || right.contains(left)) {
                    throw new TypingException("Recursive type definition : " + equation);
                }

                if(Utils.DEBUG) System.out.println("Merge " + left + " with " + right);

                // Merge the left with the right
                left.merge(right, this);
            }
        }

        if(Utils.DEBUG) System.out.println();

        // Return the unification result
        return initialEquation.getRight();
    }

}

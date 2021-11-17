package MML4J.main.typist.equation_system;

import MML4J.main.Utils;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_system.nodes.abstracts.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents an equation system
 *
 * @author Hugo GUERRIER
 */
public class EquationSystem {

    // ----- Attributes -----


    /** The equation system initial node */
    private Node initNode;

    /** The initial system equation */
    private Equation initialEquation;

    /** The system equations list */
    private final List<Equation> equations;

    /** External equations list (used in "let" typing) */
    private final List<Equation> externalEquations;


    // ----- Constructors -----


    /**
     * Create and initialise a new empty equation system with no initial node
     */
    public EquationSystem() {
        this.initNode = null;
        this.equations = new LinkedList<>();
        this.externalEquations = new LinkedList<>();
    }


    // ----- Getters -----


    /**
     * Get the initial node of the equation system
     *
     * @return The initial Node
     */
    public Node getInitNode() {
        return initNode;
    }

    /**
     * Get the external equation list
     *
     * @return The external equation list
     */
    public List<Equation> getExternalEquations() {
        return externalEquations;
    }


    // ----- Setters -----


    /**
     * Set the initial node of the system
     *
     * @param initNode The initial node
     */
    public void setInitNode(Node initNode) {
        this.initNode = initNode;
    }


    // ----- Override methods -----


    /**
     * Get the string representation of an equation system
     *
     * @see Object#toString()
     *
     * @return The string for the equation system
     */
    @Override
    public String toString() {
        // Prepare the result and the init equation
        StringBuilder res = new StringBuilder("INIT : ").append(initialEquation).append("\n");

        // Add the equations to the system
        for(Equation equation : equations) {
            res.append(equation).append("\n");
        }

        // If there is external equations, print it
        if(externalEquations.size() > 0) {
            res.append("External equations :\n");
            for(Equation extEq : externalEquations) {
                res.append(extEq).append("\n");
            }
        }

        // Return the result
        return res.toString();
    }


    // ----- Class methods -----


    /**
     * Add an equation to the system
     *
     * @param left The left part of the equation
     * @param right The right part of the equation
     */
    public void addEquation(Node left, Node right) {
        // Create the new equation
        Equation newEquation = new Equation(left, right);

        // If the equation contains the init node and the init equation is null, put it as init equation
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

        // Add the newly created equation
        equations.add(newEquation);
    }

    /***
     * Add an external equation to export unification
     *
     * @param externalNode The node that is external to the system
     * @param internalNode The corresponding internal node
     */
    public void addExternalEquation(Node externalNode, Node internalNode) {
        // Simply add the external equation to the external equation list
        Equation newExtEq = new Equation(externalNode, internalNode);
        externalEquations.add(newExtEq);
    }

    /**
     * Apply the unification algorithm on the system and get the unified node
     *
     * @return The node result of the unification
     * @throws TypingException If there is a problem in the unification process
     */
    public Node unify() throws TypingException {
        // While the equations size is more than 1
        while(equations.size() > 0) {

            // Get the head of the list
            Equation equation = equations.remove(0);
            Node left = equation.getLeft().instantiate(this);
            Node right = equation.getRight().instantiate(this);

            // Remove the equation from the system
            equation.destroy();

            // If the left and the right are not the same
            if(!left.equals(right)) {
                // Test containing
                if(left.contains(right) || right.contains(left)) {
                    throw new TypingException("Recursive type definition : " + equation);
                }

                if(Utils.DEBUG) System.out.println("Merge " + left + " with " + right);

                // Merge the left with the right
                left.merge(right, this);
            }

        }

        // Return the unification result or the init node if there is no initial equation
        if(initialEquation == null) return initNode.instantiate(this);
        return initialEquation.getRight().instantiate(this);
    }

}
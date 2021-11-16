package MML4J.main.typist.equation_system.nodes;

import MML4J.main.typist.equation_system.EquationSystem;
import MML4J.main.typist.interfaces.INodeGenerator;
import MML4J.main.typist.utils.TypeTranslator;
import MML4J.main.typist.types.Type;
import MML4J.main.typist.equation_system.Equation;
import MML4J.main.typist.utils.Instantiater;

import java.util.*;

/**
 * This class represent a generalist type
 *
 * @author Hugo GUERRIER
 */
public class ForAllNode extends Node implements INodeGenerator {

    // ----- Attributes -----


    /** The generalized type */
    private Node type;

    /** The set of general nodes */
    private final Set<SimpleNode> generalizedNodes;

    /** External constraints to be exported in the system */
    private final List<Equation> instanceConstraints;


    /** The node counter */
    private int counter;

    /** The corresponding map to generate nodes */
    private final Map<Node, Node> correspondence;


    // ----- Constructors -----


    /**
     * Create a void for all node
     */
    public ForAllNode() {
        super(null);
        type = null;
        generalizedNodes = new HashSet<>();
        instanceConstraints = new LinkedList<>();

        counter = 1;
        correspondence = new HashMap<>();
    }

    /**
     * Create a for all node from a node to generalize, a context and a bunch of instance constraints
     *
     * @param toGeneralize The node to generalize
     * @param context The context
     * @param instanceConstraints The instance constraints
     * @return The newly created for all node
     */
    public static ForAllNode from(Node toGeneralize, Map<String, Node> context, List<Equation> instanceConstraints) {
        // Create the result
        ForAllNode res = new ForAllNode();

        // Add all bound nodes
        for(Node linked : context.values()) {
            res.addCorrespondence(linked, linked);
        }

        // Clone the node to generalize
        res.setType(toGeneralize.clone(res));

        // Add the instance constraint to the result
        if(instanceConstraints != null) {
            for(Equation extEq : instanceConstraints) {
                res.addInstanceConstraint(extEq.getLeft(), extEq.getRight().clone(res));
                extEq.destroy();
            }
        }

        // Return the result
        return res;
    }


    // ----- Getters -----


    public Set<SimpleNode> getGeneralizedNodes() {
        return generalizedNodes;
    }

    public Node getType() {
        return type;
    }

    public List<Equation> getInstanceConstraints() {
        return instanceConstraints;
    }


    // ----- Setters -----


    public void setType(Node type) {
        this.type = type;
    }

    public void addInstanceConstraint(Node externalNode, Node internalNode) {
        Equation extConst = new Equation(externalNode, internalNode);
        instanceConstraints.add(extConst);
    }


    // ----- Generator methods -----


    @Override
    public SimpleNode getNewNode() {
        SimpleNode res = new SimpleNode(counter++);
        generalizedNodes.add(res);
        return res;
    }

    @Override
    public SimpleNode getNewNode(SimpleNode key) {
        SimpleNode res = (SimpleNode) correspondence.getOrDefault(key, null);
        if(res == null) {
            res = getNewNode();
            correspondence.put(key, res);
        }
        return res;
    }

    @Override
    public boolean hasCorrespondence(Node key) {
        return correspondence.containsKey(key);
    }

    @Override
    public void addCorrespondence(Node key, Node value) {
        correspondence.put(key, value);
    }

    @Override
    public void reset() {
        correspondence.clear();
        counter = 1;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "for_all" + generalizedNodes + " " + type +
                (!instanceConstraints.isEmpty() ? " | Instance constraint : " + instanceConstraints : "");
    }


    // ----- Class methods -----

    @Override
    public boolean isConstructor() {
        return true;
    }

    @Override
    public Node instantiate(EquationSystem system) {
        return Instantiater.instantiate(this, system);
    }

    @Override
    public Node clone(INodeGenerator generator) {
        if(generator.hasCorrespondence(this)) return this;

        // Prepare the result
        ForAllNode res = new ForAllNode();

        // Create the result
        res.setType(type.clone(generator));
        for(SimpleNode genNode : generalizedNodes) {
            res.getGeneralizedNodes().add((SimpleNode) genNode.clone(generator));
        }
        for(Equation eq : instanceConstraints) {
            res.addInstanceConstraint(eq.getLeft(), eq.getRight().clone(generator));
        }

        // Return the result
        return res;
    }

    @Override
    public Type acceptTranslator(TypeTranslator translator) {
        return null;
    }

    @Override
    public Node acceptInstantiater(Instantiater instantiater) {
        return instantiater.instantiate(this);
    }

}

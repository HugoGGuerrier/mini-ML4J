package MML4J.main.typist.equation_system.nodes.abstracts;

import MML4J.main.typist.interfaces.IMergeStrategy;

import java.util.List;

/**
 * This class is the base class for all constructor types nodes
 *
 * @author Hugo GUERRIER
 */
public abstract class ConstructorNode extends Node {
    /**
     * Create a node with just a container set
     *
     * @param strategy The strategy to apply to the node
     */
    public ConstructorNode(IMergeStrategy strategy) {
        super(strategy);
    }

    /**
     * Get the ordered constructor content
     *
     * @return The constructor node content ordered
     */
    public abstract List<Node> getContent();
}

package MML4J.main.typist.equation_system.nodes;

import MML4J.main.typist.interfaces.IMergeStrategy;

import java.util.List;

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

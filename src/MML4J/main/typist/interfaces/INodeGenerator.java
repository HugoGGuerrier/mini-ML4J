package MML4J.main.typist.interfaces;

import MML4J.main.typist.equation_system.nodes.Node;
import MML4J.main.typist.equation_system.nodes.SimpleNode;

public interface INodeGenerator {
    /**
     * Get a new node from the generator
     *
     * @return The newly created node
     */
    SimpleNode getNewNode();

    /**
     * Get the key associated node if it exists, create it else
     *
     * @param key The key
     * @return The (newly) associated node
     */
    SimpleNode getNewNode(SimpleNode key);

    /**
     * Get if the wanted key has a correspondence in the generator
     *
     * @param key The node key
     * @return True if there is a correspondence
     */
    boolean hasCorrespondence(Node key);

    /**
     * Add a node correspondence in this generator
     *
     * @param key The node key
     * @param value The node value
     */
    void addCorrespondence(Node key, Node value);

    /**
     * Reset the generator
     */
    void reset();
}

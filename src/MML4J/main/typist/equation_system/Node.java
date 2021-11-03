package MML4J.main.typist.equation_system;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;
import MML4J.main.typist.utils.Generalizer;
import MML4J.main.typist.utils.INodeContainer;
import MML4J.main.typist.utils.Ungeneralizer;

import java.util.HashSet;
import java.util.Set;

public abstract class Node {

    // ----- Attributes -----


    protected final Set<INodeContainer> containers;


    // ----- Constructors -----


    public Node() {
        containers = new HashSet<>();
    }


    // ----- Setters -----


    public void addContainer(INodeContainer container) {
        containers.add(container);
    }

    public void removeContainer(INodeContainer container) {
        containers.remove(container);
    }

    public void destroy() {
        containers.clear();
    }


    // ----- Class methods -----


    /**
     * Get if the node is a specific one
     *
     * @return If the node is specific
     */
    public abstract boolean isSpecific();

    /**
     * Merge the node with an other node
     *
     * @param other The node to merge with
     * @param system The equation system
     * @throws TypingException If there is a problem during merging
     */
    public abstract void merge(Node other, EquationSystem system) throws TypingException;

    /**
     * Get if the other node is in the current node
     *
     * @param other The other node
     * @return If the other node is in this node
     */
    public abstract boolean contains(Node other);

    /**
     * Translate a node into a usable type
     *
     * @param translator The translator
     * @return The type for the node
     */
    public abstract Type acceptTranslator(TypeTranslator translator);

    /**
     * Accept a node generalizer
     *
     * @param generalizer The generalizer
     * @return The generalized node
     */
    public abstract Node acceptGeneralizer(Generalizer generalizer);

    /**
     * Accept an ungeneralizer to instantiate the type
     *
     * @param ungeneralizer The ungeneralizer
     * @return The instantiated type
     */
    public abstract Node acceptUngeneralizer(Ungeneralizer ungeneralizer);

}

package MML4J.main.typist.equation_system.nodes;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_system.EquationSystem;
import MML4J.main.typist.interfaces.IMergeStrategy;
import MML4J.main.typist.interfaces.INodeContained;
import MML4J.main.typist.interfaces.INodeGenerator;
import MML4J.main.typist.utils.TypeTranslator;
import MML4J.main.typist.types.Type;
import MML4J.main.typist.interfaces.INodeContainer;
import MML4J.main.typist.utils.Instantiater;

import java.util.HashSet;
import java.util.Set;

public abstract class Node implements INodeContained {

    // ----- Attributes -----


    /** All containers that contains the node */
    protected final Set<INodeContainer> containers;

    /** The node merging strategy */
    protected final IMergeStrategy mergeStrategy;


    // ----- Constructors -----


    /**
     * Create a node with just a container set
     */
    public Node(IMergeStrategy strategy) {
        this.containers = new HashSet<>();
        this.mergeStrategy = strategy;
    }


    // ----- Contained methods -----


    /** @see INodeContained#getContainers() */
    @Override
    public Set<INodeContainer> getContainers() {
        return containers;
    }

    /** @see INodeContained#addContainer(INodeContainer) */
    @Override
    public void addContainer(INodeContainer container) {
        containers.add(container);
    }

    /** @see INodeContained#removeContainer(INodeContainer) */
    @Override
    public void removeContainer(INodeContainer container) {
        containers.remove(container);
    }

    /** @see INodeContained#destroyContained() */
    @Override
    public void destroyContained() {
        containers.clear();
    }


    // ----- Class methods -----


    /**
     * Merge the node with an other node
     *
     * @param other The node to merge with
     * @param system The equation system
     * @throws TypingException If there is a problem during merging
     */
    public void merge(Node other, EquationSystem system) throws TypingException {
        if(mergeStrategy == null) throw new TypingException("Try to merge an un-mergable node");
        mergeStrategy.doMerge(this, other, system);
    }

    /**
     * Get if the node is a type constructor node
     *
     * @return If the node is a constructor
     */
    public abstract boolean isConstructor();

    /**
     * Get the instantiated node (must be overriding in non-instance node i.e. ForAllNode)
     *
     * @param system The equation system to instantiate the node in
     * @return An instance of the node
     */
    public Node instantiate(EquationSystem system) {
        return this;
    }

    /**
     * Clone the node with the wanted generator
     *
     * @param generator The generator
     * @return The cloned node in the wanted generator
     */
    public abstract Node clone(INodeGenerator generator);

    /**
     * Get if the other node is in the current node
     *
     * @param other The other node
     * @return If the other node is in this node
     */
    public boolean contains(Node other) {
        return false;
    }

    /**
     * Translate a node into a usable type
     *
     * @param translator The translator
     * @return The type for the node
     */
    public abstract Type acceptTranslator(TypeTranslator translator);

    /**
     * Accept an ungeneralizer to instantiate the type
     *
     * @param instantiater The ungeneralizer
     * @return The instantiated type
     */
    public abstract Node acceptInstantiater(Instantiater instantiater);

}

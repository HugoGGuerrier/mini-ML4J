package mml4j.main.typist.equation_system.nodes.abstracts;

import mml4j.main.exceptions.TypingException;
import mml4j.main.typist.equation_system.EquationSystem;
import mml4j.main.typist.interfaces.IMergeStrategy;
import mml4j.main.typist.interfaces.INodeContained;
import mml4j.main.typist.interfaces.INodeGenerator;
import mml4j.main.typist.TypeTranslator;
import mml4j.main.typist.types.abstracts.Type;
import mml4j.main.typist.interfaces.INodeContainer;
import mml4j.main.typist.utils.Instantiater;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is the base class for all nodes it contains default functions and behavior
 *
 * @author Hugo GUERRIER
 */
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

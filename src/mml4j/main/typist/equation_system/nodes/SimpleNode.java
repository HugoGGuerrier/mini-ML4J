package mml4j.main.typist.equation_system.nodes;

import mml4j.main.typist.equation_system.merging_strategy.WeakStrategy;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;
import mml4j.main.typist.interfaces.INodeGenerator;
import mml4j.main.typist.utils.TypeTranslator;
import mml4j.main.typist.types.abstracts.Type;
import mml4j.main.typist.utils.Instantiater;

/**
 * This class represent a simple type before its translating into type
 *
 * @author Hugo GUERRIER
 */
public class SimpleNode extends Node {

    // ----- Attributes -----


    /** The node id for printing purpose */
    protected final int id;


    // ----- Constructors -----


    /**
     * Create a new simple node with its id
     *
     * @param id The node id
     */
    public SimpleNode(int id) {
        super(new WeakStrategy());
        this.id = id;
    }


    // ----- Getters -----


    public int getId() {
        return id;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        if(id < 0) {
            return "GT" + -id;
        }
        return "T" + id;
    }


    // ----- Class methods -----


    /** @see Node#isConstructor() */
    @Override
    public boolean isConstructor() {
        return false;
    }

    /** @see Node#contains(Node) */
    @Override
    public boolean contains(Node other) {
        return this == other;
    }

    /** @see Node#clone(INodeGenerator) */
    @Override
    public Node clone(INodeGenerator generator) {
        return generator.getNewNode(this);
    }

    /** @see Node#acceptTranslator(TypeTranslator) */
    @Override
    public Type acceptTranslator(TypeTranslator translator) {
        return translator.translate(this);
    }

    /** @see Node#acceptInstantiater(Instantiater) */
    @Override
    public Node acceptInstantiater(Instantiater instantiater) {
        return instantiater.instantiate(this);
    }

}

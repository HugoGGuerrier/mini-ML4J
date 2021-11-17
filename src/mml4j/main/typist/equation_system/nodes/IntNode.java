package mml4j.main.typist.equation_system.nodes;

import mml4j.main.typist.equation_system.merging_strategy.SingletonStrategy;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;
import mml4j.main.typist.interfaces.INodeGenerator;
import mml4j.main.typist.TypeTranslator;
import mml4j.main.typist.types.abstracts.Type;
import mml4j.main.typist.utils.Instantiater;

/**
 * This class represent the only instance of integer type
 *
 * @author Hugo GUERRIER
 */
public class IntNode extends Node {

    // ----- Attributes -----


    private static IntNode instance = null;


    // ----- Constructors -----


    private IntNode() {
        super(new SingletonStrategy());
    }

    public static IntNode getInstance() {
        if(instance == null) instance = new IntNode();
        return instance;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "int";
    }


    // ----- Class methods -----


    /** @see Node#isConstructor() */
    @Override
    public boolean isConstructor() {
        return true;
    }

    /** @see Node#contains(Node) */
    @Override
    public boolean contains(Node other) {
        return this == other;
    }

    /** @see Node#clone(INodeGenerator) */
    @Override
    public Node clone(INodeGenerator generator) {
        return this;
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

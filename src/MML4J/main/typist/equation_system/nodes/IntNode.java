package MML4J.main.typist.equation_system.nodes;

import MML4J.main.typist.equation_system.merging_strategy.SingletonStrategy;
import MML4J.main.typist.interfaces.INodeGenerator;
import MML4J.main.typist.utils.TypeTranslator;
import MML4J.main.typist.types.Type;
import MML4J.main.typist.utils.Instantiater;

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

    @Override
    public boolean isConstructor() {
        return true;
    }


    // ----- Class methods -----


    @Override
    public boolean contains(Node other) {
        return this == other;
    }

    @Override
    public Node clone(INodeGenerator generator) {
        return this;
    }

    @Override
    public Type acceptTranslator(TypeTranslator translator) {
        return translator.translate(this);
    }

    @Override
    public Node acceptInstantiater(Instantiater instantiater) {
        return instantiater.instantiate(this);
    }

}

package MML4J.main.typist.equation_system.nodes;

import MML4J.main.typist.equation_system.merging_strategy.WeakStrategy;
import MML4J.main.typist.interfaces.INodeGenerator;
import MML4J.main.typist.utils.TypeTranslator;
import MML4J.main.typist.types.Type;
import MML4J.main.typist.utils.Instantiater;

/**
 * This class represent a simple type before its translating into type
 *
 * @author Hugo GUERRIER
 */
public class SimpleNode extends Node {

    // ----- Attributes -----


    protected final int id;


    // ----- Constructors -----


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

    @Override
    public boolean isConstructor() {
        return false;
    }


    // ----- Class methods -----

    @Override
    public boolean contains(Node other) {
        return this == other;
    }

    @Override
    public Node clone(INodeGenerator generator) {
        return generator.getNewNode(this);
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

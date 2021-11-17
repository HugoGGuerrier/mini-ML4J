package MML4J.main.typist.equation_system.nodes;

import MML4J.main.typist.equation_system.merging_strategy.SingletonStrategy;
import MML4J.main.typist.equation_system.nodes.abstracts.Node;
import MML4J.main.typist.interfaces.INodeGenerator;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.types.abstracts.Type;
import MML4J.main.typist.utils.Instantiater;

/**
 * This class represents the only instance of the unit type
 *
 * @author Hugo GUERRIER
 */
public class UnitNode extends Node {

    // ----- Attributes -----


    private static UnitNode instance = null;


    // ----- Constructors -----


    private UnitNode() {
        super(new SingletonStrategy());
    }

    public static UnitNode getInstance() {
        if(instance == null) instance = new UnitNode();
        return instance;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "unit";
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

package MML4J.main.typist.equation_system;

import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;
import MML4J.main.typist.utils.Generalizer;
import MML4J.main.typist.utils.INodeContainer;
import MML4J.main.typist.utils.Ungeneralizer;

import java.util.HashSet;
import java.util.Set;

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
    public boolean isSpecific() {
        return false;
    }

    @Override
    public Node instantiate(EquationSystem system) {
        return this;
    }


    // ----- Class methods -----


    @Override
    public void merge(Node other, EquationSystem system) {
        // Replace this node by the other
        Set<INodeContainer> containersCopy = new HashSet<>(containers);
        for(INodeContainer cont : containersCopy) {
            cont.replaceNode(this, other);
        }
        this.destroy();
    }

    @Override
    public boolean contains(Node other) {
        return this == other;
    }

    @Override
    public Type acceptTranslator(TypeTranslator translator) {
        return translator.translate(this);
    }

    @Override
    public Node acceptGeneralizer(Generalizer generalizer) {
        return generalizer.generalize(this);
    }

    @Override
    public Node acceptUngeneralizer(Ungeneralizer ungeneralizer) {
        return ungeneralizer.ungeneralize(this);
    }

}

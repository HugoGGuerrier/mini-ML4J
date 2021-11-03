package MML4J.main.typist.equation_system;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;
import MML4J.main.typist.utils.Generalizer;
import MML4J.main.typist.utils.INodeContainer;
import MML4J.main.typist.utils.Ungeneralizer;

/**
 * This class represent the only instance of integer type
 *
 * @author Hugo GUERRIER
 */
public class IntNode extends Node {

    // ----- Attributes -----


    private static IntNode instance = null;


    // ----- Constructors -----


    private IntNode() {}

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
    public boolean isSpecific() {
        return true;
    }


    // ----- Class methods -----


    @Override
    public void merge(Node other, EquationSystem system) throws TypingException {
        // Verify the other node nature
        if(other.isSpecific() && !(other instanceof IntNode)) throw new TypingException("Cannot merge non identical specific nodes");

        // Replace the other by the int node
        for(INodeContainer otherContainer : other.containers) {
            otherContainer.replaceNode(other, this);
        }
        other.destroy();
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

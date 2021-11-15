package MML4J.main.typist.equation_system;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;
import MML4J.main.typist.utils.Generalizer;
import MML4J.main.typist.utils.INodeContainer;
import MML4J.main.typist.utils.Ungeneralizer;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the only instance of the unit type
 *
 * @author Hugo GUERRIER
 */
public class UnitNode extends Node {

    // ----- Attributes -----


    private static UnitNode instance = null;


    // ----- Constructors -----


    private UnitNode() {}

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
    public boolean isSpecific() {
        return true;
    }

    @Override
    public Node instantiate(EquationSystem system) {
        return this;
    }


    // ----- Class methods -----


    @Override
    public void merge(Node other, EquationSystem system) throws TypingException {
        // Verify the other node nature
        if(other.isSpecific() && !(other instanceof UnitNode)) throw new TypingException("Cannot merge non identical specific nodes");

        // Replace the other by the unit node
        Set<INodeContainer> otherContainersClone = new HashSet<>(other.containers);
        for(INodeContainer otherContainer : otherContainersClone) {
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

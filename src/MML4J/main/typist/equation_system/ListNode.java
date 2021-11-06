package MML4J.main.typist.equation_system;

import MML4J.main.Utils;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;
import MML4J.main.typist.utils.Generalizer;
import MML4J.main.typist.utils.INodeContainer;
import MML4J.main.typist.utils.Ungeneralizer;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represent a list type before its unification
 *
 * @author Hugo GUERRIER
 */
public class ListNode extends Node implements INodeContainer {

    // ----- Attributes -----


    private Node type;


    // ----- Constructors -----


    public ListNode(Node type) {
        this.type = type;
        type.addContainer(this);
    }


    // ----- Getters -----


    public Node getType() {
        return type;
    }


    // ----- Setters -----


    public void setType(Node type) {
        type.addContainer(this);
        if(this.type != null) this.type.removeContainer(this);
        this.type = type;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "[" + type + "]";
    }

    @Override
    public boolean isSpecific() {
        return true;
    }

    @Override
    public void replaceNode(Node oldNode, Node newNode) {
        if(type == oldNode) setType(newNode);
    }

    @Override
    public void destroy() {
        super.destroy();
        if(type != null) type.removeContainer(this);
    }

    @Override
    public Node instantiate() {
        return this;
    }


    // ----- Class methods -----


    @Override
    public void merge(Node other, EquationSystem system) throws TypingException {
        // Verify the other node nature
        if(other.isSpecific() && !(other instanceof ListNode)) throw new TypingException("Cannot merge non identical specific nodes");

        // If the other node is a list node
        if(other instanceof ListNode) {
            ListNode otherList = (ListNode) other;

            system.addEquation(type, otherList.type);
        }

        // If the other is another node
        else {
            Set<INodeContainer> otherContainersCopy = new HashSet<>(other.containers);
            for(INodeContainer otherContainer : otherContainersCopy) {
                otherContainer.replaceNode(other, this);
            }
            other.destroy();
        }
    }

    @Override
    public boolean contains(Node other) {
        if(other instanceof ListNode) {
            ListNode listOther = (ListNode) other;
            return this.type.contains(listOther.type);
        }
        return this.type.contains(other);
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

package MML4J.main.typist.equation_system;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;
import MML4J.main.typist.utils.Generalizer;
import MML4J.main.typist.utils.INodeContainer;
import MML4J.main.typist.utils.Ungeneralizer;

import java.util.HashSet;
import java.util.Set;

public class RefNode extends Node implements INodeContainer {

    // ----- Attributes -----


    protected Node content;


    // ----- Constructors -----


    public RefNode(Node content) {
        this.content = content;
        content.addContainer(this);
    }


    // ----- Getters ------


    public Node getContent() {
        return content;
    }


    // ----- Setters -----


    public void setContent(Node content) {
        content.addContainer(this);
        if(this.content != null) this.content.removeContainer(this);
        this.content = content;
    }


    // ----- Override methods -----

    @Override
    public String toString() {
        return "Ref(" + content + ")";
    }

    @Override
    public boolean isSpecific() {
        return true;
    }

    @Override
    public Node instantiate(EquationSystem system) {
        return this;
    }

    @Override
    public void destroy() {
        super.destroy();
        if(content != null) content.removeContainer(this);
    }

    @Override
    public void replaceNode(Node oldNode, Node newNode) {
        if(content == oldNode) setContent(newNode);
    }


    // ----- Class methods -----


    @Override
    public void merge(Node other, EquationSystem system) throws TypingException {
        // Very the other node nature
        if(other.isSpecific() && !(other instanceof RefNode)) throw new TypingException("Cannot merge non identical specific nodes");

        // If the other is also a ref node
        if(other instanceof RefNode) {
            RefNode refOther = (RefNode) other;
            system.addEquation(content, refOther.content);
        }

        // If the other is a simple node
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
        if(other instanceof RefNode) {
            RefNode refOther = (RefNode) other;
            return content.contains(refOther.content);
        }
        return content.contains(other);
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

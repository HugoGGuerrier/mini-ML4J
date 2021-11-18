package mml4j.main.typist.equation_system.nodes;

import mml4j.main.typist.equation_system.merging_strategy.ConstructorStrategy;
import mml4j.main.typist.equation_system.nodes.abstracts.ConstructorNode;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;
import mml4j.main.typist.interfaces.INodeContained;
import mml4j.main.typist.interfaces.INodeGenerator;
import mml4j.main.typist.utils.TypeTranslator;
import mml4j.main.typist.types.abstracts.Type;
import mml4j.main.typist.interfaces.INodeContainer;
import mml4j.main.typist.utils.Instantiater;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represent a list type before its unification
 *
 * @author Hugo GUERRIER
 */
public class ListNode extends ConstructorNode implements INodeContainer {

    // ----- Attributes -----


    /** The type inside the list */
    protected Node listType;


    // ----- Constructors -----


    /**
     * Create a new list type with the wanted content type
     *
     * @param type The type that is inside the list
     */
    public ListNode(Node type) {
        super(new ConstructorStrategy());
        this.listType = type;
        type.addContainer(this);
    }


    // ----- Getters -----


    public Node getListType() {
        return listType;
    }

    /** @see ConstructorNode#getContent() */
    @Override
    public List<Node> getContent() {
        List<Node> res = new LinkedList<>();
        res.add(listType);
        return res;
    }

    
    // ----- Setters -----


    public void setListType(Node listType) {
        listType.addContainer(this);
        if(this.listType != null) this.listType.removeContainer(this);
        this.listType = listType;
    }


    // ----- Container methods -----


    /** @see INodeContainer#replaceContained(INodeContained, INodeContained) */
    @Override
    public void replaceContained(INodeContained oldCont, INodeContained newCont) {
        if(listType == oldCont) setListType((Node) newCont);
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "[" + listType + "]";
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
        return listType.contains(other);
    }

    /** @see Node#clone(INodeGenerator) */
    @Override
    public Node clone(INodeGenerator generator) {
        if(generator.hasCorrespondence(this)) return this;
        return new ListNode(listType.clone(generator));
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

package MML4J.main.typist.equation_system.nodes;

import MML4J.main.typist.equation_system.merging_strategy.ConstructorStrategy;
import MML4J.main.typist.interfaces.INodeContained;
import MML4J.main.typist.interfaces.INodeGenerator;
import MML4J.main.typist.utils.TypeTranslator;
import MML4J.main.typist.types.Type;
import MML4J.main.typist.interfaces.INodeContainer;
import MML4J.main.typist.utils.Instantiater;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represent a list type before its unification
 *
 * @author Hugo GUERRIER
 */
public class ListNode extends ConstructorNode implements INodeContainer {

    // ----- Attributes -----


    protected Node listType;


    // ----- Constructors -----


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


    @Override
    public void replaceContained(INodeContained oldCont, INodeContained newCont) {
        if(listType == oldCont) setListType((Node) newCont);
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "[" + listType + "]";
    }

    @Override
    public boolean isConstructor() {
        return true;
    }


    // ----- Class methods -----


    @Override
    public boolean contains(Node other) {
        return listType.contains(other);
    }

    @Override
    public Node clone(INodeGenerator generator) {
        if(generator.hasCorrespondence(this)) return this;
        return new ListNode(listType.clone(generator));
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
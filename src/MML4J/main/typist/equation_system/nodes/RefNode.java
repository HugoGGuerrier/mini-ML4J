package MML4J.main.typist.equation_system.nodes;

import MML4J.main.typist.equation_system.merging_strategy.ConstructorStrategy;
import MML4J.main.typist.equation_system.nodes.abstracts.ConstructorNode;
import MML4J.main.typist.equation_system.nodes.abstracts.Node;
import MML4J.main.typist.interfaces.INodeContained;
import MML4J.main.typist.interfaces.INodeGenerator;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.types.abstracts.Type;
import MML4J.main.typist.interfaces.INodeContainer;
import MML4J.main.typist.utils.Instantiater;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a reference node for the equation generation
 *
 * @author Hugo GUERRIER
 */
public class RefNode extends ConstructorNode implements INodeContainer {

    // ----- Attributes -----


    /** The reference type */
    protected Node refType;


    // ----- Constructors -----


    /**
     * Create a reference node with the reference type
     *
     * @param refType The content
     */
    public RefNode(Node refType) {
        super(new ConstructorStrategy());
        this.refType = refType;
        refType.addContainer(this);
    }


    // ----- Getters ------


    public Node getRefType() {
        return refType;
    }

    /** @see ConstructorNode#getContent() */
    @Override
    public List<Node> getContent() {
        List<Node> res = new LinkedList<>();
        res.add(refType);
        return res;
    }


    // ----- Setters -----


    public void setRefType(Node refType) {
        refType.addContainer(this);
        if(this.refType != null) this.refType.removeContainer(this);
        this.refType = refType;
    }


    // ----- Container methods -----


    /** @see INodeContainer#replaceContained(INodeContained, INodeContained) */
    @Override
    public void replaceContained(INodeContained oldCont, INodeContained newCont) {
        if(refType == oldCont) setRefType((Node) newCont);
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "Ref(" + refType + ")";
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
        return refType.contains(other);
    }

    /** @see Node#clone(INodeGenerator) */
    @Override
    public Node clone(INodeGenerator generator) {
        if(generator.hasCorrespondence(this)) return this;
        return new RefNode(refType.clone(generator));
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

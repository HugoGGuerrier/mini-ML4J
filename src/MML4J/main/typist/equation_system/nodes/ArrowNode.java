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
 * This class represent an arrow type node
 *
 * @author Hugo GUERRIER
 */
public class ArrowNode extends ConstructorNode implements INodeContainer {

    // ----- Attributes -----


    /** The left node */
    protected Node left;

    /** The right node */
    protected Node right;


    // ----- Constructors -----


    /**
     * Create a new arrow node with the left and the right nodes
     *
     * @param left The left node
     * @param right The right node
     */
    public ArrowNode(Node left, Node right) {
        super(new ConstructorStrategy());
        this.left = left;
        this.right = right;
        left.addContainer(this);
        right.addContainer(this);
    }


    // ----- Getters -----


    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    /** @see ConstructorNode#getContent() */
    @Override
    public List<Node> getContent() {
        List<Node> res = new LinkedList<>();
        res.add(left);
        res.add(right);
        return res;
    }


    // ----- Setters -----


    /**
     * Set the left node and change the left containers list
     *
     * @param left The new left node
     */
    public void setLeft(Node left) {
        left.addContainer(this);
        if(this.left != null) this.left.removeContainer(this);
        this.left = left;
    }

    /**
     * Set the right node and change the right containers list
     *
     * @param right The new right node
     */
    public void setRight(Node right) {
        right.addContainer(this);
        if(this.right != null) this.right.removeContainer(this);
        this.right = right;
    }


    // ----- Container methods -----


    /** @see INodeContainer#replaceContained(INodeContained, INodeContained) */
    @Override
    public void replaceContained(INodeContained oldCont, INodeContained newCont) {
        if(left == oldCont) setLeft((Node) newCont);
        if(right == oldCont) setRight((Node) newCont);
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "(" + left.toString() + " -> " + right.toString() + ")";
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
        return this.left.contains(other) || this.right.contains(other);
    }

    /** @see Node#clone(INodeGenerator) */
    @Override
    public Node clone(INodeGenerator generator) {
        if(generator.hasCorrespondence(this)) return this;
        return new ArrowNode(left.clone(generator), right.clone(generator));
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

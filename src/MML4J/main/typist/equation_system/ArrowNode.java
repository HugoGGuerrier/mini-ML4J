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
 * This class represent an arrow type before its unification process
 *
 * @author Hugo GUERRIER
 */
public class ArrowNode extends Node implements INodeContainer {

    // ----- Attributes -----


    protected Node left;
    protected Node right;


    // ----- Constructors -----


    public ArrowNode(Node left, Node right) {
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


    // ----- Setters -----


    public void setLeft(Node left) {
        left.addContainer(this);
        if(this.left != null) this.left.removeContainer(this);
        this.left = left;
    }

    public void setRight(Node right) {
        right.addContainer(this);
        if(this.right != null) this.right.removeContainer(this);
        this.right = right;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "(" + left.toString() + " -> " + right.toString() + ")";
    }

    @Override
    public boolean isSpecific() {
        return true;
    }

    @Override
    public void replaceNode(Node oldNode, Node newNode) {
        if(left == oldNode) setLeft(newNode);
        if(right == oldNode) setRight(newNode);
    }

    @Override
    public void destroy() {
        super.destroy();
        if(left != null) left.removeContainer(this);
        if(right != null) right.removeContainer(this);
    }

    @Override
    public Node instantiate() {
        return this;
    }


    // ----- Class methods -----


    @Override
    public void merge(Node other, EquationSystem system) throws TypingException {
        // Verify the other node nature
        if(other.isSpecific() && !(other instanceof ArrowNode)) throw new TypingException("Cannot merge non identical specific nodes");

        // If the other node is an arrow node
        if(other instanceof ArrowNode) {
            ArrowNode arrowOther = (ArrowNode) other;

            system.addEquation(left, arrowOther.left);
            system.addEquation(right, arrowOther.right);
        }

        // If the other node is another node
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
        if(other instanceof ArrowNode) {
            ArrowNode arrowOther = (ArrowNode) other;
            return this.left.contains(arrowOther.left) && this.right.contains(arrowOther.right);
        }
        return this.left.contains(other) || this.right.contains(other);
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

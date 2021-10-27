package MML4J.main.typist.equation_graph;

import MML4J.main.Utils;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.Generalizer;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;

import java.util.HashSet;
import java.util.Set;

public class ArrowNode extends Node {

    // ----- Attributes -----


    protected Node left;
    protected Node right;


    // ----- Constructors -----


    public ArrowNode(Node left, Node right) {
        this.left = left;
        this.right = right;
        left.addParent(this);
        right.addParent(this);
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
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public void replaceChild(Node oldChild, Node newChild) {
        if(oldChild == left) left = newChild;
        else if(oldChild == right) right = newChild;
        else super.replaceChild(oldChild, newChild);
    }

    @Override
    public void destroy() {
        super.destroy();
        left = null;
        right = null;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "(" + left.toString() + " -> " + right.toString() + ")";
    }

    @Override
    public String getEquationsString() {
        return super.getEquationsString() +
                left.getEquationsString() +
                right.getEquationsString();
    }

    @Override
    public boolean structEquals(Node other) {
        if(this == other) return true;
        if(other == null || getClass() != other.getClass()) return false;
        ArrowNode arrowNode = (ArrowNode) other;

        boolean childrenEquality = children.size() == arrowNode.children.size();
        if(childrenEquality) {
            for(Node child : children) {
                boolean childEq = false;
                for(Node otherChild : arrowNode.children) {
                    if(child.structEquals(otherChild)) childEq = true;
                }
                childrenEquality = childrenEquality && childEq;
            }
        }

        return left.structEquals(arrowNode.left) && right.structEquals(arrowNode.right) && childrenEquality;
    }

    @Override
    public boolean isSpecific() {
        return true;
    }


    // ----- Class methods -----


    @Override
    public Node unify() throws TypingException {
        // Make debug print
        if(Utils.DEBUG) {
            System.out.println("Start unification on " + this + " | Children = " + children);
        }

        // Get the children clone set and unify all children
        Set<Node> childrenClone = new HashSet<>(children);
        for(Node child : childrenClone) {
            child.unify();
        }

        // Merge all children with the current node
        Node currentNode = this;
        childrenClone = new HashSet<>(children);
        for(Node child : childrenClone) {
            // Prepare the debug print
            String debugPrint = "Merging " + currentNode + " with " + child;

            currentNode = currentNode.merge(child);

            // Display the debug
            if(Utils.DEBUG) {
                System.out.println(debugPrint + " | Result = " + currentNode);
            }
        }

        // Unify the left and the right
        left.unify();
        right.unify();

        // Return the result
        return currentNode;
    }

    @Override
    public Node merge(Node other) throws TypingException {
        // If the other node is the node, just return it
        if(other == this) {
            this.removeChild(this);
            this.removeParent(this);
            return this;
        }

        // Verify that the other node
        if(this.contains(other)) throw new TypingException("Error during unification : Recursive type definition");
        if(other.isSpecific() && !(other instanceof ArrowNode)) throw new TypingException("Error during unification : Trying to merge arrow with a specific node");

        // Get the other parents
        for(Node otherParent : other.parents) {
            if(otherParent != this) {
                otherParent.replaceChild(other, this);
                this.addParent(otherParent);
            }
        }

        // Remove the child
        this.removeChild(other);

        // Get the other children
        for(Node otherChild : other.children) {
            if(otherChild != this) {
                otherChild.replaceParent(other, this);
                this.addChild(otherChild);
            }
        }

        // If the other is an arrow node, there is a special way
        if(other instanceof ArrowNode) {
            ArrowNode arrowOther = (ArrowNode) other;

            // Add the left constraint
            left.addChild(arrowOther.left);
            arrowOther.left.addParent(left);

            // Add the right constraint
            right.addChild(arrowOther.right);
            arrowOther.right.addParent(right);
        }

        // Destroy the other node
        other.destroy();

        // Return the node
        return this;
    }

    @Override
    public boolean contains(Node other) {
        if(other instanceof ArrowNode) {
            ArrowNode arrowOther = (ArrowNode) other;
            return this.left.contains(arrowOther.left) || this.right.contains(arrowOther.right);
        }
        return this.left.contains(other) || this.right.contains(other);
    }

    @Override
    public Type acceptTranslator(TypeTranslator translator) throws TypingException {
        return translator.translate(this);
    }

    @Override
    public Node acceptGeneralizer(Generalizer generalizer) {
        return generalizer.generalize(this);
    }

}

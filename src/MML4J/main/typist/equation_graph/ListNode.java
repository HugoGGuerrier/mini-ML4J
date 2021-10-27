package MML4J.main.typist.equation_graph;

import MML4J.main.Utils;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.Generalizer;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;

import java.util.HashSet;
import java.util.Set;

public class ListNode extends Node {

    // ----- Attributes -----


    private final Node type;


    // ----- Constructors -----


    public ListNode(Node type) {
        this.type = type;
    }


    // ----- Getters -----


    public Node getType() {
        return type;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "[" + type + "]";
    }

    @Override
    public boolean structEquals(Node other) {
        if(this == other) return true;
        if(other == null || getClass() != other.getClass()) return false;
        ListNode listNode = (ListNode) other;

        return type.structEquals(listNode.type);
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

        // Start the unification on the type
        type.unify();

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

        // Verify that the other doesn't contain the node
        if(other.contains(this)) throw new TypingException("Error during unification : Recursive type definition");
        if(other.isSpecific() && !(other instanceof IntNode)) throw new TypingException("Error during unification : Trying to merge int with a specific");

        // Take other's parents
        for(Node otherParent : other.parents) {
            if(otherParent != this) {
                otherParent.replaceChild(other, this);
                this.addParent(otherParent);
            }
        }

        // Remove the other from the child
        this.removeChild(other);

        // Take all other's children
        for(Node otherChild : other.children) {
            if(otherChild != this) {
                otherChild.replaceParent(other, this);
                this.addChild(otherChild);
            }
        }

        // If the other is a list node
        if(other instanceof ListNode) {
            ListNode listOther = (ListNode) other;

            // Add the equality between the two types
            type.addChild(listOther.type);
            listOther.type.addParent(type);
        }

        // Destroy the other node
        other.destroy();

        // Return the node
        return this;
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
    public Type acceptTranslator(TypeTranslator translator) throws TypingException {
        return translator.translate(this);
    }

    @Override
    public Node acceptGeneralizer(Generalizer generalizer) {
        return generalizer.generalize(this);
    }

}

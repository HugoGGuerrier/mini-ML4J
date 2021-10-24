package MML4J.main.typist.equation_graph;

import MML4J.main.Utils;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;

import java.util.HashSet;
import java.util.Set;

public class IntNode extends Node {

    // ----- Attributes -----


    private static IntNode instance = null;


    // ----- Constructors -----


    private IntNode() {}

    public static IntNode getInstance() {
        if(instance == null) instance = new IntNode();
        return instance;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "int";
    }

    @Override
    public boolean structEquals(Node other) {
        return this == other;
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

        // Start the unification on all children
        Set<Node> childrenClone = new HashSet<>(children);
        for(Node child : childrenClone) {
            child.unify();
        }

        // Merge the node with its children
        Node currentNode = this;
        childrenClone = new HashSet<>(children);
        for(Node child : childrenClone) {
            // Prepare the debug print
            String debugPrint = "Merging " + currentNode + " with " + child;

            // Merge the nodes
            currentNode = currentNode.merge(child);

            // Display the debug
            if(Utils.DEBUG) {
                System.out.println(debugPrint + " | Result = " + currentNode);
            }
        }

        // Return the current node
        return currentNode;
    }

    @Override
    public Node merge(Node other) throws TypingException {
        // Verify that the other doesn't contain the node
        if(other.contains(this)) throw new TypingException("Error during unification : Recursive type definition");
        if(other.isSpecific()) throw new TypingException("Error during unification : Trying to merge int with a specific");
        if(other instanceof ArrowNode) throw new TypingException("Error during unification : Trying to merge int and arrow");

        // Take other parents
        for(Node otherParent : other.parents) {
            if(otherParent != this) {
                otherParent.replaceChild(other, this);
                this.addParent(otherParent);
            }
        }

        // Remove the other from the child
        this.removeChild(other);

        // Take all other children
        for(Node otherChild : other.children) {
            if(otherChild != this) {
                otherChild.replaceParent(other, this);
                this.addChild(otherChild);
            }
        }

        // Destroy the other node
        other.destroy();

        // Return the node
        return this;
    }

    @Override
    public boolean contains(Node other) {
        return this == other;
    }

    @Override
    public Type acceptTranslator(TypeTranslator translator) throws TypingException {
        return translator.translate(this);
    }

}

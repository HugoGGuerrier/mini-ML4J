package MML4J.main.typist.equation_graph;

import MML4J.main.Utils;
import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.Generalizer;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.Ungeneralizer;
import MML4J.main.typist.type.Type;

import java.util.HashSet;
import java.util.Set;

public class SimpleNode extends Node {

    // ----- Attributes -----


    protected final int id;


    // ----- Constructors -----


    public SimpleNode(int id) {
        this.id = id;
    }


    // ----- Getters -----


    public int getId() {
        return id;
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "T" + id;
    }

    @Override
    public boolean structEquals(Node other) {
        if(this == other) return true;
        if(other == null || getClass() != other.getClass()) return false;
        SimpleNode simpleNode = (SimpleNode) other;

        boolean childrenEquality = children.size() == simpleNode.children.size();
        if(childrenEquality) {
            for(Node child : children) {
                boolean childEq = false;
                for(Node otherChild : simpleNode.children) {
                    if(child.structEquals(otherChild)) childEq = true;
                }
                childrenEquality = childrenEquality && childEq;
            }
        }

        return id == simpleNode.id && childrenEquality;
    }

    @Override
    public boolean isSpecific() {
        return false;
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
        // If the other node is the node, just return it
        if(other == this) {
            this.removeChild(this);
            this.removeParent(this);
            return this;
        }

        // Verify that the other doesn't contain the node
        if(other.contains(this)) throw new TypingException("Error during unification : Recursive type definition");

        // Give all node's parent to the other
        for(Node parent : this.parents) {
            if(parent != other) {
                parent.replaceChild(this, other);
                other.addParent(parent);
            }
        }

        // Give all node's child to the other
        for(Node child : this.children) {
            if(child != other) {
                child.replaceParent(this, other);
                other.addChild(child);
            }
        }

        // Remove the node from the other's parent
        other.removeParent(this);

        // Destroy the node and return the other
        this.destroy();

        // Return the result
        return other;
    }

    @Override
    public boolean contains(Node other) {
        return this == other;
    }

    @Override
    public Type acceptTranslator(TypeTranslator translator) throws TypingException {
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

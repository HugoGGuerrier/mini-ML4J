package MML4J.main.typist.equation_graph;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.TypeTranslator;
import MML4J.main.typist.type.Type;

import java.util.HashSet;
import java.util.Set;

public abstract class Node {

    // ----- Attributes -----


    protected final Set<Node> parents;
    protected final Set<Node> children;


    // ----- Constructors -----


    public Node() {
        parents = new HashSet<>();
        children = new HashSet<>();
    }


    // ----- Getters -----


    public Set<Node> getParents() {
        return parents;
    }

    public Set<Node> getChildren() {
        return children;
    }


    // ----- Setters -----


    public void addChild(Node child) {
        children.add(child);
    }

    public void removeChild(Node child) {
        children.remove(child);
    }

    public void addParent(Node parent) {
        parents.add(parent);
    }

    public void removeParent(Node parent) {
        parents.remove(parent);
    }

    public void replaceChild(Node oldChild, Node newChild) {
        if(children.remove(oldChild)) children.add(newChild);
    }

    public void replaceParent(Node oldParent, Node newParent) {
        if(parents.remove(oldParent)) parents.add(newParent);
    }

    public void destroy() {
        parents.clear();
        children.clear();
    }


    // ----- Override methods -----


    @Override
    public String toString() {
        return "VOID TYPE NODE";
    }


    // ----- Class methods -----


    /**
     * Get if the two nodes are structurally equals
     *
     * @param other The other node
     * @return True if they are equals
     */
    public abstract boolean structEquals(Node other);

    /**
     * Get if the node is a specific one
     *
     * @return If the node is specific
     */
    public abstract boolean isSpecific();

    /**
     * Start the unification of the node with all its children
     *
     * @return The node that came from the unification
     * @throws TypingException If there is an error during typing
     */
    public abstract Node unify() throws TypingException;

    /**
     * Merge the node with an other node
     *
     * @param other The node to merge with
     * @return The node that result from the merge
     * @throws TypingException If there is a problem during merging
     */
    public abstract Node merge(Node other) throws TypingException;

    /**
     * Get if the other node is in the current node
     *
     * @param other The other node
     * @return If the other node is in this node
     */
    public abstract boolean contains(Node other);

    /**
     * Translate a node into a usable type
     *
     * @param translator The translator
     * @return The type for the node
     * @throws TypingException If the translator cannot translate
     */
    public abstract Type acceptTranslator(TypeTranslator translator) throws TypingException;

    /**
     * Get all equations string representation writable from this node
     *
     * @return The equations string representations
     */
    public String getEquationsString() {
        // Prepare the string builder
        StringBuilder res = new StringBuilder();

        if(children.size() > 0) {
            // Add this node equations
            res.append(this).append(" = ");
            for(Node child : children) {
                res.append(child.toString()).append(" | ");
            }
            res.append("\n");

            for(Node child : children) {
                res.append(child.getEquationsString());
            }
        }

        return res.toString();
    }

}

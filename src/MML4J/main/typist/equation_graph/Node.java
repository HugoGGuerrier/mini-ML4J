package MML4J.main.typist.equation_graph;

import java.util.HashSet;
import java.util.Set;

public class Node {

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
    public boolean structEquals(Node other) {
        return false;
    }

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

    /**
     * Get the full representation of the node with parents and children
     *
     * @return The full representation
     */
    public String getFullDisplay() {
        return null;
    }

}

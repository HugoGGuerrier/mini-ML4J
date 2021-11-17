package mml4j.main.typist.interfaces;

/**
 * This interface represents a node container
 *
 * @author Hugo GUERRIER
 */
public interface INodeContainer {
    /**
     * Replace an old contained node in the container by a new one
     *
     * @param oldCont The contained node to replace
     * @param newCont The new contained node
     */
    void replaceContained(INodeContained oldCont, INodeContained newCont);
}

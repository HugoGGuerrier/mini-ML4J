package MML4J.main.typist.interfaces;

public interface INodeContainer {
    /**
     * Replace an old contained node in the container by a new one
     *
     * @param oldCont The contained node to replace
     * @param newCont The new contained node
     */
    void replaceContained(INodeContained oldCont, INodeContained newCont);
}
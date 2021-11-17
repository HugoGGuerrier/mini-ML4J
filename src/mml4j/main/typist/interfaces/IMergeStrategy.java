package mml4j.main.typist.interfaces;

import mml4j.main.exceptions.TypingException;
import mml4j.main.typist.equation_system.EquationSystem;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;

/**
 * Interface used to create node merging strategy
 *
 * @author Hugo GUERRIER
 */
public interface IMergeStrategy {
    /**
     * Do the merging action in the wanted equation system
     *
     * @param left The source node to merge
     * @param right The node to merge with
     * @param system The equation system
     * @throws TypingException If the merging is impossible
     */
    void doMerge(Node left, Node right, EquationSystem system) throws TypingException;
}

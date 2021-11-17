package mml4j.main.typist.equation_system.merging_strategy;

import mml4j.main.exceptions.TypingException;
import mml4j.main.typist.equation_system.EquationSystem;
import mml4j.main.typist.equation_system.nodes.abstracts.Node;
import mml4j.main.typist.interfaces.IMergeStrategy;
import mml4j.main.typist.interfaces.INodeContainer;

import java.util.HashSet;

/**
 * This class implements strategy to merge a weak node to another
 * 
 * @author Hugo GUERRIER
 */
public class WeakStrategy implements IMergeStrategy {
    /** @see IMergeStrategy#doMerge(Node, Node, EquationSystem) */
    @Override
    public void doMerge(Node left, Node right, EquationSystem system) throws TypingException {
        // Replace all left node occurrences by the right node
        for(INodeContainer leftContainer : new HashSet<>(left.getContainers())) {
            leftContainer.replaceContained(left, right);
        }

        // Destroy the left node
        left.destroyContained();
    }
}

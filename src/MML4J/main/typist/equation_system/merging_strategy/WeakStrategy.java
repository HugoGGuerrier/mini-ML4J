package MML4J.main.typist.equation_system.merging_strategy;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_system.EquationSystem;
import MML4J.main.typist.equation_system.nodes.abstracts.Node;
import MML4J.main.typist.interfaces.IMergeStrategy;
import MML4J.main.typist.interfaces.INodeContainer;

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

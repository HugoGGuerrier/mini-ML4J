package MML4J.main.typist.equation_system.merging_strategy;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_system.EquationSystem;
import MML4J.main.typist.equation_system.nodes.abstracts.Node;
import MML4J.main.typist.interfaces.IMergeStrategy;
import MML4J.main.typist.interfaces.INodeContainer;

import java.util.HashSet;

public class WeakStrategy implements IMergeStrategy {
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

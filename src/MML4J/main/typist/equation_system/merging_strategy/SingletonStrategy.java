package MML4J.main.typist.equation_system.merging_strategy;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_system.EquationSystem;
import MML4J.main.typist.equation_system.nodes.abstracts.Node;
import MML4J.main.typist.interfaces.IMergeStrategy;
import MML4J.main.typist.interfaces.INodeContainer;

import java.util.HashSet;

/**
 * This class implements the strategy to merge singletons
 * 
 * @author Hugo GUERRIER
 */
public class SingletonStrategy implements IMergeStrategy {
    /** @see IMergeStrategy#doMerge(Node, Node, EquationSystem) */
    @Override
    public void doMerge(Node left, Node right, EquationSystem system) throws TypingException {
        // Verify the other nature
        boolean sameClass = right.getClass().getCanonicalName().equals(left.getClass().getCanonicalName());
        if(right.isConstructor() && !(sameClass))
            throw new TypingException("Cannot merge non identical constructor nodes : " + left + " and " + right);

        // Replace the right node by the left one
        for(INodeContainer rightContainer : new HashSet<>(right.getContainers())) {
            rightContainer.replaceContained(right, left);
        }
        right.destroyContained();
    }
}

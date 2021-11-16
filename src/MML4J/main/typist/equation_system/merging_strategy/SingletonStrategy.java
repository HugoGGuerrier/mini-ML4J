package MML4J.main.typist.equation_system.merging_strategy;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_system.EquationSystem;
import MML4J.main.typist.equation_system.nodes.Node;
import MML4J.main.typist.interfaces.IMergeStrategy;
import MML4J.main.typist.interfaces.INodeContainer;

import java.util.HashSet;

public class SingletonStrategy implements IMergeStrategy {
    @Override
    public void doMerge(Node left, Node right, EquationSystem system) throws TypingException {
        // Verify the other nature
        boolean sameClass = right.getClass().getCanonicalName().equals(left.getClass().getCanonicalName());
        if(right.isConstructor() && !(sameClass))
            throw new TypingException("Cannot merge non identical constructor nodes");

        // Replace the right node by the left one
        for(INodeContainer rightContainer : new HashSet<>(right.getContainers())) {
            rightContainer.replaceContained(right, left);
        }
        right.destroyContained();
    }
}

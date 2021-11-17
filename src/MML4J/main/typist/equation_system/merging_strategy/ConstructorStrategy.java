package MML4J.main.typist.equation_system.merging_strategy;

import MML4J.main.exceptions.TypingException;
import MML4J.main.typist.equation_system.EquationSystem;
import MML4J.main.typist.equation_system.nodes.abstracts.ConstructorNode;
import MML4J.main.typist.equation_system.nodes.abstracts.Node;
import MML4J.main.typist.interfaces.IMergeStrategy;
import MML4J.main.typist.interfaces.INodeContainer;

import java.util.HashSet;
import java.util.List;

/**
 * This class implements the strategy to merge a constructor node with another node
 *
 * @author Hugo GUERRIER
 */
public class ConstructorStrategy implements IMergeStrategy {
    /** @see IMergeStrategy#doMerge(Node, Node, EquationSystem) */
    @Override
    public void doMerge(Node left, Node right, EquationSystem system) throws TypingException {
        // Verify that the left is a constructor node
        if(!(left instanceof ConstructorNode))
            throw new TypingException("Trying to apply a constructor strategy on a non-constructor node !!!");

        // Verify the other node nature
        boolean sameClass = right.getClass().getCanonicalName().equals(left.getClass().getCanonicalName());
        if(right.isConstructor() && !(sameClass))
            throw new TypingException("Cannot merge non identical constructor nodes : " + left + " and " + right);

        // If the other node is the same class
        if(sameClass) {
            ConstructorNode constLeft = (ConstructorNode) left;
            ConstructorNode constRight = (ConstructorNode) right;

            List<Node> leftContent = constLeft.getContent();
            List<Node> rightContent = constRight.getContent();

            for(int i = 0 ; i < leftContent.size() ; i++) {
                system.addEquation(leftContent.get(i), rightContent.get(i));
            }
        }

        // If the other is a simple node
        else {
            for(INodeContainer rightContainer : new HashSet<>(right.getContainers())) {
                rightContainer.replaceContained(right, left);
            }
            right.destroyContained();
        }
    }
}

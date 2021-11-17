package MML4J.main.typist.interfaces;

import java.util.Set;

/**
 * This interface represents all methods that a contained should offer
 *
 * @author Hugo GUERRIER
 */
public interface INodeContained {
    /**
     * Get the containers that contains the contained
     *
     * @return The containers set
     */
    Set<INodeContainer> getContainers();

    /**
     * Add the contained to a container
     *
     * @param container The container to add
     */
    void addContainer(INodeContainer container);

    /**
     * Remove the contained to a container
     *
     * @param container The container to remove
     */
    void removeContainer(INodeContainer container);

    /**
     * Destroy the contained
     */
    void destroyContained();
}

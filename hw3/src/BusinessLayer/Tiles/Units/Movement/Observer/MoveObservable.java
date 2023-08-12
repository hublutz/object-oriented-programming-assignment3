package BusinessLayer.Tiles.Units.Movement.Observer;

import BusinessLayer.Tiles.Units.Movement.MoveOperations.MoveOperation;

/**
 * Interface MoveObservable is used to notify MoveObservers of new movements
 */
public interface MoveObservable
{
    /**
     * Adds a new observer
     * @param observer The new observer to add
     */
    void addObserver(MoveObserver observer);
    /**
     * Removes an observer
     * @param observer The observer to remove
     */
    void removeObserver(MoveObserver observer);

    /**
     * Notifies all observers with the given move operation
     * @param moveOperation The move operation to notify about
     */
    void notifyObservers(MoveOperation moveOperation);
}

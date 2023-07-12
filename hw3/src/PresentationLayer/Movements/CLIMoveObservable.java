package PresentationLayer.Movements;

import BusinessLayer.Tiles.Units.Movement.Observer.MoveObservable;
import BusinessLayer.Tiles.Units.Movement.Observer.MoveObserver;
import BusinessLayer.Tiles.Units.Movement.MoveOperations.MoveOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * CLIMoveObservable is a movement observable class used in the CLI presentation layer
 */
public class CLIMoveObservable implements MoveObservable
{
    private List<MoveObserver> observers;

    /**
     * CLIMoveObservable constructor
     */
    public CLIMoveObservable()
    {
        this.observers = new ArrayList<>();
    }

    /**
     * Adds a new move observer to the list of observers
     */
    @Override
    public void addObserver(MoveObserver observer)
    {
        this.observers.add(observer);
    }

    /**
     * Removes the given observer from the list of observers
     * @param observer The observer to remove
     */
    @Override
    public void removeObserver(MoveObserver observer)
    {
        this.observers.remove(observer);
    }

    /**
     * Notifies each of the observers of a new move operation
     * @param moveOperation The movement to make
     */
    @Override
    public void notifyObservers(MoveOperation moveOperation)
    {
        for (MoveObserver observer : this.observers)
        {
            observer.onMove(moveOperation);
        }
    }
}

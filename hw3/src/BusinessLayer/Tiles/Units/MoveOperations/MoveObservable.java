package BusinessLayer.Tiles.Units.MoveOperations;

/**
 * Interface MoveObservable is used to notify MoveObservers of new movements
 */
public interface MoveObservable
{
    void addObserver(MoveObserver observer);
    void notifyObservers(MoveOperation moveOperation);
}

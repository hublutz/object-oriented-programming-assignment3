package BusinessLayer.Tiles.Units.Movement.Observer;

import BusinessLayer.Tiles.Units.Movement.MoveOperations.MoveOperation;

/**
 * Interface MoveObserver represents objects that wait for movement
 * commands to perform
 */
public interface MoveObserver
{

    /**
     * Move observer notification OnMove
     * */
    public void onMove(MoveOperation operation);
}

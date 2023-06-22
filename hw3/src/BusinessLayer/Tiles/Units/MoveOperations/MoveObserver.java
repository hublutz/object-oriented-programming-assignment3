package BusinessLayer.Tiles.Units.MoveOperations;

/**
 * Interface MoveObserver represents objects that wait for movement
 * commands to perform
 */
public interface MoveObserver
{

    /**
     * Move observer notification OnMove
     * */
    void onMove(MoveOperation operation);
}

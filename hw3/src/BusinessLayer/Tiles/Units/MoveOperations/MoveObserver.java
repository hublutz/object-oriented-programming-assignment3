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
    public void onMove(MoveOperation operation);
}

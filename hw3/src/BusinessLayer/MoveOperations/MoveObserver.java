package BusinessLayer.MoveOperations;

public interface MoveObserver {

    /**
     * Move observer notification OnMove
     * */
    public void onMove(MoveOperation operation);
}

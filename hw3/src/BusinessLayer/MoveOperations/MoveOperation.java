package BusinessLayer.MoveOperations;

import BusinessLayer.Tiles.Point;

public interface MoveOperation {
    /**
     * preforms the move operation
     * */
    public void move(Point position);
}

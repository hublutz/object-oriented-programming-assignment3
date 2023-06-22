package BusinessLayer.Tiles.Units.MoveOperations;

import BusinessLayer.Tiles.Point;
import BusinessLayer.Tiles.Units.UnitTile;

public interface MoveOperation {
    /**
     * preforms the move operation
     * */
    public void move(UnitTile unit);
}

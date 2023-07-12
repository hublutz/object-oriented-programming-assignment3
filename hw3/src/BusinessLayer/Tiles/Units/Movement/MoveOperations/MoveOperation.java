package BusinessLayer.Tiles.Units.Movement.MoveOperations;

import BusinessLayer.Tiles.Units.UnitTile;

/**
 * Interface MoveOperation represents a movement to perform on
 * a given UnitTile
 */
public interface MoveOperation
{
    /**
     * preforms the move operation
     * */
    void move(UnitTile unit);
}

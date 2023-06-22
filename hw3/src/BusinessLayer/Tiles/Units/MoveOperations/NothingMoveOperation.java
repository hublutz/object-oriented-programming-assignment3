package BusinessLayer.Tiles.Units.MoveOperations;

import BusinessLayer.Tiles.Units.UnitTile;

/**
 * MoveOperation for no movement
 */
public class NothingMoveOperation implements MoveOperation
{
    /**
     * This method performs no movements on the position
     * @param unit the unit given
     */
    @Override
    public void move(UnitTile unit) { }
}

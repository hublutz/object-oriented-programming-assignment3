package BusinessLayer.Tiles.Units.MoveOperations;

import BusinessLayer.Tiles.Point;

/**
 * MoveOperation for no movement
 */
public class NothingMoveOperation implements MoveOperation
{
    /**
     * This method performs no movements on the position
     * @param position the position given
     */
    @Override
    public void move(Point position) { }
}

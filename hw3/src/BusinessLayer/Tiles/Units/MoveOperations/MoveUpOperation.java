package BusinessLayer.Tiles.Units.MoveOperations;

import BusinessLayer.Tiles.Point;

/**
 * MoveOperation for moving up one tile
 */
public class MoveUpOperation implements MoveOperation
{
    /**
     * This method move the given position up one tile
     * @param position the position to move
     */
    @Override
    public void move(Point position)
    {
        position.move(position.getX(), position.getY() + 1);
    }
}

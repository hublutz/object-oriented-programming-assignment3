package BusinessLayer.Tiles.Units.MoveOperations;

import BusinessLayer.Tiles.Point;

/**
 * MoveOperation for moving left one tile
 */
public class MoveLeftOperation implements MoveOperation
{
    /**
     * This method move the given position left one tile
     * @param position the position to move
     */
    @Override
    public void move(Point position)
    {
        position.move(position.getX() - 1, position.getY());
    }
}

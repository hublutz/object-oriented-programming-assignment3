package BusinessLayer.Tiles.Units.MoveOperations;

import BusinessLayer.Tiles.Point;

/**
 * MoveOperation for moving down one tile
 */
public class MoveDownOperation implements MoveOperation
{

    /**
     * This method move the given position down one tile
     * @param position the position to move
     */
    @Override
    public void move(Point position)
    {
        position.move(position.getX(), position.getY() - 1);
    }
}

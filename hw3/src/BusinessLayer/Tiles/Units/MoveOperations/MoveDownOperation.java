package BusinessLayer.Tiles.Units.MoveOperations;

import BusinessLayer.Tiles.Units.UnitTile;

/**
 * MoveOperation for moving down one tile
 */
public class MoveDownOperation extends AbstractBoardMovement
{
    /**
     * MoveDownOperation constructor
     * @param gameBoard The game board containing the tiles of the level
     */
    public MoveDownOperation(GameBoard gameBoard)
    {
        super(gameBoard);
    }

    /**
     * This method move the given position down one tile
     * @param unit the unit to move
     */
    @Override
    public void move(UnitTile unit)
    {
//        position.move(position.getX(), position.getY() - 1);
    }
}

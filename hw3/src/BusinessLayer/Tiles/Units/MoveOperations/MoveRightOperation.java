package BusinessLayer.Tiles.Units.MoveOperations;

import BusinessLayer.Tiles.Units.UnitTile;

/**
 * MoveOperation for moving right one tile
 */
public class MoveRightOperation extends AbstractBoardMovement
{
    /**
     * MoveRightOperation constructor
     * @param gameBoard The game board containing the tiles of the level
     */
    public MoveRightOperation(GameBoard gameBoard)
    {
        super(gameBoard);
    }

    /**
     * This method move the given position right one tile
     * @param unit the unit to move
     */
    @Override
    public void move(UnitTile unit)
    {
//        position.move(position.getX() + 1, position.getY());
    }
}

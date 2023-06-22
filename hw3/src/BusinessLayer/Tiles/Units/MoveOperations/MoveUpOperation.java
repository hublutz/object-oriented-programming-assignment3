package BusinessLayer.Tiles.Units.MoveOperations;

import BusinessLayer.Tiles.Units.UnitTile;

/**
 * MoveOperation for moving up one tile
 */
public class MoveUpOperation extends AbstractBoardMovement
{
    /**
     * MoveUpOperation constructor
     * @param gameBoard The game board containing the tiles of the level
     */
    public MoveUpOperation(GameBoard gameBoard)
    {
        super(gameBoard);
    }

    /**
     * This method move the given position up one tile
     * @param unit the unit to move
     */
    @Override
    public void move(UnitTile unit)
    {
        //position.move(position.getX(), position.getY() + 1);
    }
}

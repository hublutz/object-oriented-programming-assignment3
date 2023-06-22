package BusinessLayer.Tiles.Units.MoveOperations;

import BusinessLayer.Tiles.Units.UnitTile;

/**
 * MoveOperation for moving left one tile
 */
public class MoveLeftOperation extends AbstractBoardMovement
{
    /**
     * MoveLeftOperation constructor
     * @param gameBoard The game board containing the tiles of the level
     */
    public MoveLeftOperation(GameBoard gameBoard)
    {
        super(gameBoard);
    }

    /**
     * This method move the given position left one tile
     * @param unit the unit to move
     */
    @Override
    public void move(UnitTile unit)
    {
//        position.move(position.getX() - 1, position.getY());
    }
}

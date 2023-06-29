package BusinessLayer.Tiles.Units.MoveOperations;

import BusinessLayer.GameBoard;

/**
 * MoveOperation for moving up one tile
 */
public class MoveUpOperation extends AbstractBoardMovement
{
    private static final int X_AXIS_CHANGE = 0;
    private static final int Y_AXIS_CHANGE = -1;

    /**
     * MoveUpOperation constructor
     * @param gameBoard The game board containing the tiles of the level
     */
    public MoveUpOperation(GameBoard gameBoard)
    {
        super(gameBoard, X_AXIS_CHANGE, Y_AXIS_CHANGE);
    }
}

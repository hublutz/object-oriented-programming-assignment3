package BusinessLayer.Tiles.Units.MoveOperations;

import BusinessLayer.GameBoard;

/**
 * MoveOperation for moving down one tile
 */
public class MoveDownOperation extends AbstractBoardMovement
{
    private static final int X_AXIS_CHANGE = 0;
    private static final int Y_AXIS_CHANGE = 1;

    /**
     * MoveDownOperation constructor
     * @param gameBoard The game board containing the tiles of the level
     */
    public MoveDownOperation(GameBoard gameBoard)
    {
        super(gameBoard, X_AXIS_CHANGE, Y_AXIS_CHANGE);
    }
}

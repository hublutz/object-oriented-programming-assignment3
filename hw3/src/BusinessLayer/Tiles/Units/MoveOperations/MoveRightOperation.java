package BusinessLayer.Tiles.Units.MoveOperations;

/**
 * MoveOperation for moving right one tile
 */
public class MoveRightOperation extends AbstractBoardMovement
{
    private static final int X_AXIS_CHANGE = 1;
    private static final int Y_AXIS_CHANGE = 0;

    /**
     * MoveRightOperation constructor
     * @param gameBoard The game board containing the tiles of the level
     */
    public MoveRightOperation(GameBoard gameBoard)
    {
        super(gameBoard, X_AXIS_CHANGE, Y_AXIS_CHANGE);
    }
}

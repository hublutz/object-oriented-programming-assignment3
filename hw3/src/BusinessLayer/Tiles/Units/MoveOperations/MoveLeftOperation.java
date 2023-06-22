package BusinessLayer.Tiles.Units.MoveOperations;

/**
 * MoveOperation for moving left one tile
 */
public class MoveLeftOperation extends AbstractBoardMovement
{
    private static final int X_AXIS_CHANGE = -1;
    private static final int Y_AXIS_CHANGE = 0;

    /**
     * MoveLeftOperation constructor
     * @param gameBoard The game board containing the tiles of the level
     */
    public MoveLeftOperation(GameBoard gameBoard)
    {
        super(gameBoard, X_AXIS_CHANGE, Y_AXIS_CHANGE);
    }
}

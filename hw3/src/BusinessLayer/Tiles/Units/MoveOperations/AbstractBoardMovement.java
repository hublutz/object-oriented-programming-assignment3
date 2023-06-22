package BusinessLayer.Tiles.Units.MoveOperations;

/**
 * Class AbstractBoardMovement represents an abstract
 * move that requires the game board to be performed
 */
public abstract class AbstractBoardMovement implements MoveOperation
{
    protected GameBoard gameBoard;

    /**
     * AbstractBoardMovement constructor
     * @param gameBoard The game board containing the tiles of the level
     */
    public AbstractBoardMovement(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
    }
}

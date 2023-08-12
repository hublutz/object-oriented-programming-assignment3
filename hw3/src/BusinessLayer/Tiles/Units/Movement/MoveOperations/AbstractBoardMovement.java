package BusinessLayer.Tiles.Units.Movement.MoveOperations;

import BusinessLayer.GameBoard;
import BusinessLayer.Tiles.Point;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.UnitTile;

/**
 * Class AbstractBoardMovement represents an abstract
 * move that requires the game board to be performed
 */
public abstract class AbstractBoardMovement implements MoveOperation
{
    protected GameBoard gameBoard;
    protected int xAxisChange;
    protected int yAxisChange;

    /**
     * AbstractBoardMovement constructor
     * @param gameBoard The game board containing the tiles of the level
     * @param xAxisChange The change to make in the x-axis value of a given unit
     * @param yAxisChange The change to make in the y-axis value of a given unit
     */
    public AbstractBoardMovement(GameBoard gameBoard, int xAxisChange, int yAxisChange)
    {
        this.gameBoard = gameBoard;
        this.xAxisChange = xAxisChange;
        this.yAxisChange = yAxisChange;
    }

    /**
     * This method tries to move the given unit by the a-axis and y-axis
     * changes of the class
     * @param unit The unit to move
     */
    @Override
    public void move(UnitTile unit)
    {
        Point newPosition = new Point(unit.getX() + xAxisChange,
                unit.getY() + yAxisChange);
        Tile newPositionTile = this.gameBoard.getTile(newPosition);
        newPositionTile.accept(unit);
    }
}

package BusinessLayer;

import BusinessLayer.Tiles.Units.Players.Player;

import java.util.Iterator;

public abstract class AbstractGameBoardIterator implements Iterator<GameBoard>
{
    protected Player currentPlayer;

    /**
     * Getter for the current level player
     */
    public Player getCurrentPlayer()
    {
        return this.currentPlayer;
    }
}

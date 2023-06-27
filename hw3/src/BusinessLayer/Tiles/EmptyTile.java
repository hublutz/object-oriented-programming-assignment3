package BusinessLayer.Tiles;

import BusinessLayer.Tiles.VisitorPattern.IVisitor;

/**
 * Class EmptyTile represents an empty space in the board
 */
public class EmptyTile extends Tile
{
    public static final char EMPTY_TILE_CHAR = '.';

    /**
     * EmptyTile constructor, initialises the tile char to be
     * '.'
     * @param x the x-axis value of the position
     * @param y the y-axis value of the position
     */
    public EmptyTile(int x, int y)
    {
        super(EMPTY_TILE_CHAR, x, y);
    }

    /**
     * Updates the EmptyTile after a game tick.
     * Practically, it is empty since the tile can't update
     */
    @Override
    public void onGameTick() { }

    /**
     * Accept action as part of the visitor pattern in the player class
     * */
    @Override
    public void accept(IVisitor visitor){
        visitor.visit(this);
    }
}

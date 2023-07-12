package BusinessLayer.Tiles;

import BusinessLayer.Tiles.VisitorPattern.IVisitor;

/**
 * Class WallTile represents a wall in the board
 */
public class WallTile extends Tile
{
    public static final char WALL_TILE_CHAR = '#';

    /**
     * WallTile constructor, initialises the tile char to be
     * '#'
     * @param x the x-axis value of the position
     * @param y the y-axis value of the position
     */
    public WallTile(int x, int y)
    {
        super(WALL_TILE_CHAR, x, y);
    }

    /**
     * Updates the WallTile after a game tick.
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

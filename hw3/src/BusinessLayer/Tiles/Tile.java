package BusinessLayer.Tiles;

import BusinessLayer.Tiles.VisitorPattern.IVisited;

/**
 * Class Tile represents a general tile in the game board
 */
public abstract class Tile implements IVisited
{
    protected char tile;
    protected Point position;

    /**
     * Tile constructor
     * @param tile the tile character
     * @param x the x-axis value of the Tile
     * @param y the y-axis value of the Tile
     */
    public Tile(char tile, int x, int y)
    {
        this.tile = tile;
        this.position = new Point(x, y);
    }

    /**
     * Check the object is a Tile and its tile character and
     * position are equal to this one's
     * @param obj an object
     * @return true is the object is a Tile, and it's tile char
     * and position are like this Tile
     */
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Tile))
        {
            return false;
        }
        Tile other = (Tile)obj;
        return this.tile == other.tile && this.position.equals(other.position);
    }

    /**
     * Represents the Tile as a String
     * @return the tile character
     */
    @Override
    public String toString()
    {
        return String.valueOf(this.tile);
    }

    /**
     * Tile character getter
     * @return the tile char
     */
    public char getTile()
    {
        return tile;
    }

    /**
     * X-axis position getter
     * @return the x-axis position of the tile
     */
    public int getX()
    {
        return this.position.getX();
    }

    /**
     * Y-axis position getter
     * @return the y-axis position of the tile
     */
    public int getY()
    {
        return this.position.getY();
    }

    /**
     * Calculates the range between two tiles
     * @param other a tile to calculate the distance from
     * @return the distance between this Tile and the other Tile
     */
    public double range(Tile other)
    {
        return this.position.range(other.position);
    }

    /**
     * Abstract method that updates the Tile after
     * one game tick
     */
    public abstract void onGameTick();

    /**
     * Moves this tile to new position
     * */
    public void move(int x, int y)
    {
        this.position.move(x,y);
    }
}

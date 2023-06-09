package BusinessLayer.Tiles;

public abstract class Tile
{
    protected char tile;
    protected Point position;

    public Tile(char tile, int x, int y)
    {
        this.tile = tile;
        this.position = new Point(x, y);
    }

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

    @Override
    public String toString()
    {
        return String.valueOf(this.tile);
    }

    public char getTile()
    {
        return tile;
    }
}

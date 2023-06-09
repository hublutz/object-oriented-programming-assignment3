package BusinessLayer.Tiles;

/**
 * Class Point represents the placement of a Tile in the board
 */
public class Point
{
    private int x;
    private int y;

    /**
     * Point constructor
     * @param x the x-axis value
     * @param y the y-axis value
     */
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * X-axis value getter
     * @return the x-axis value of the point
     */
    public int getX()
    {
        return x;
    }

    /**
     * Y-axis value getter
     * @return the y-axis value of the point
     */
    public int getY()
    {
        return y;
    }

    /**
     * Changes the axes values of the point to the given ones
     * @param x the nes x-axis value
     * @param y the new y-axis value
     */
    public void move(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the Euclidean range between two points
     * @param other the point to calculate the distance from
     * @return the distance between this point and the other one
     */
    public double range(Point other)
    {
        int xDelta = this.x - other.x;
        int yDelta = this.y - other.y;
        return Math.sqrt((xDelta * xDelta) + (yDelta * yDelta));
    }

    /**
     * Checks if the given parameter is a Point and if its axes
     * are equal to this Point's axes
     * @param obj an object
     * @return true if the object is a Point whose axes equal to this one's.
     * Else false
     */
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Point))
        {
            return false;
        }

        Point other = (Point)obj;
        return this.x == other.x && this.y == other.y;
    }
}

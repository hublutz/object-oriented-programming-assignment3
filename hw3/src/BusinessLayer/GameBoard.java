package BusinessLayer;

import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.Point;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a game board of a certain level
 */
public class GameBoard
{

    /**
     * list of all the tiles in the game
     * */
    private final List<Tile> boardTiles;
    private int rowsAmount;
    private int columnsAmount;

    /**
     * GameBoard constructor
     * @param boardTiles an array of all the boardTiles
     * */
    public GameBoard(Tile[][] boardTiles){
        this.boardTiles = new ArrayList<>();

        for(Tile[] tileRow: boardTiles){
            Collections.addAll(this.boardTiles, tileRow);
        }

        this.rowsAmount = boardTiles.length;
        this.columnsAmount = boardTiles[0].length;
    }

    /**
     * Return a tile according to its location
     * @param x the x value of the tile
     * @param y the y value of the tile
     * @return a tile in the position x y
     * */
    public Tile getTile(int x, int y)
    {
        return this.boardTiles.stream().filter(tile -> tile.getX() == x &&
                tile.getY() == y).collect(Collectors.toList()).get(0);
    }

    /**
     * Return a tile according to its location
     * @param position the position of the tile to get
     * @return a tile in the position x y
     * */
    public Tile getTile(Point position)
    {
        return this.getTile(position.getX(), position.getY());
    }

    /**
     * This method adds a new Tile to the game board
     * @param tile the new tile to add
     */
    public void addTile(Tile tile)
    {
        this.boardTiles.add(tile);
    }

    /**
     * Removes an enemy and replaces it with an empty tile
     * @param enemy the enemy to remove
     * */
    public void remove(Enemy enemy)
    {
        boardTiles.remove(enemy);
        boardTiles.add(new EmptyTile(enemy.getX(),enemy.getY()));
    }

    /**
     * Converts the board to a string
     * @return the string representing the board
     * */
    public String toString()
    {
        StringBuilder ret = new StringBuilder();

        for(int y = 0; y < this.rowsAmount; y++)
        {
            for (int x = 0; x < this.columnsAmount; x++)
                ret.append(getTile(x, y));

            ret.append("\n");
        }

        return ret.toString();
    }

    /**
     * Advances the game by a Tick
     * */
    public void tick()
    {
        boardTiles.forEach(Tile::onGameTick);
    }

}

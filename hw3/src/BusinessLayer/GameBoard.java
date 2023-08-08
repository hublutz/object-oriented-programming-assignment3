package BusinessLayer;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.Point;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.EnemyTiles.IEnemyDeathCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.RemoveEnemyDeathCallback;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.VisitorPattern.IVisitor;

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
    private final List<Enemy> enemyList;
    private final Player player;

    private int rowsAmount;
    private int columnsAmount;

    /**
     * GameBoard constructor
     * @param boardTiles an array of all the boardTiles
     * */
    public GameBoard(Tile[][] boardTiles, List<Enemy> enemyList, Player player)
    {
        this.boardTiles = new ArrayList<>();

        for(Tile[] tileRow: boardTiles)
        {
            Collections.addAll(this.boardTiles, tileRow);
        }

        this.enemyList = enemyList;
        this.player = player;
        this.rowsAmount = boardTiles.length;
        this.columnsAmount = boardTiles[0].length;
        this.initialiseEnemies();
    }

    /**
     * This method initialises every enemy in the game board with the player and a
     * death callback
     */
    private void initialiseEnemies()
    {
        this.enemyList.forEach(enemy -> enemy.initialise(player,
                new RemoveEnemyDeathCallback(this)));
    }

    /**
     * Getter of the game level player
     * @return The player of the current level
     */
    public Player getPlayer()
    {
        return this.player;
    }

    /**
     * Getter of the game level enemies
     * @return The enemy list of the current level
     */
    public List<Enemy> getEnemyList()
    {
        return this.enemyList;
    }

    /**
     * Returns the amount of enemies remaining in the board
     */
    public int getEnemyAmount()
    {
        return this.enemyList.size();
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
     * Removes an enemy and replaces it with an empty tile
     * @param enemy the enemy to remove
     * */
    public void remove(Enemy enemy)
    {
        this.boardTiles.remove(enemy);
        this.enemyList.remove(enemy);
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

    /**
     * Returns whether the current game level is finished
     */
    public boolean isLevelFinished()
    {
        return this.enemyList.isEmpty();
    }

    /**
     * Returns whether the player of the game is dead
     */
    public boolean isPlayerDead()
    {
        return this.player.isDead();
    }
}

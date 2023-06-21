package BusinessLayer.Tiles.Units.EnemyTiles;

import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.Tile;

import java.util.List;

/**
 * Class RemoveEnemyDeathCallback implements the enemy death callback,
 * removing the dead enemy from the tiles list
 */
public class RemoveEnemyDeathCallback implements IEnemyDeathCallback
{
    private final List<Tile> tilesList;

    /**
     * RemoveEnemyDeathCallback constructor
     * @param tilesList The Tiles list of the game board
     */
    public RemoveEnemyDeathCallback(List<Tile> tilesList)
    {
        this.tilesList = tilesList;
    }

    /**
     * This method removes the dead enemy from the list
     * and replaces it by an empty tile
     * @param enemy An Enemy tile that has died
     */
    @Override
    public void callEnemyDeath(Enemy enemy)
    {
        this.tilesList.remove(enemy);
        this.tilesList.add(new EmptyTile(enemy.getX(), enemy.getY()));
    }
}

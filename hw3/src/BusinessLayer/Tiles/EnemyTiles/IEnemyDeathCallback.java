package BusinessLayer.Tiles.EnemyTiles;

/**
 * Interface IEnemyDeathCallback declares behaviour
 * upon an enemy's death
 */
public interface IEnemyDeathCallback
{
    /**
     * This method acts upon the given enemy's death
     * @param enemy An Enemy tile that has died
     */
    void callEnemyDeath(Enemy enemy);
}

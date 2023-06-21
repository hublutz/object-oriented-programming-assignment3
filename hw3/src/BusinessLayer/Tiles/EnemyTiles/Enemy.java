package BusinessLayer.Tiles.EnemyTiles;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.UnitTile;

/**
 * Abstract class Enemy represents an Enemy Tile in the game board
 */
public abstract class Enemy extends UnitTile
{
    protected int experienceValue;
    protected Player player;
    protected IEnemyDeathCallback deathCallback;
    /**
     * Enemy constructor, receives all UnitTile parameters and
     * experience value and the player reference
     * @param experienceValue Experience value the player receives upon killing the enemy
     * @param player The player tile reference
     * @param deathCallback Defines the behaviour of the enemy upon death
     */
    public Enemy(char tile, int x, int y, String name, int healthPool,
                 int attackPoints, int defencePoints, IMessageCallback messageCallback,
                 int experienceValue, Player player, IEnemyDeathCallback deathCallback)
    {
        super(tile, x, y, name, healthPool, attackPoints,
                defencePoints, messageCallback);
        this.experienceValue = experienceValue;
        this.player = player;
        this.deathCallback = deathCallback;
    }

    @Override
    public void onDeath()
    {
        this.deathCallback.callEnemyDeath(this);
    }
}

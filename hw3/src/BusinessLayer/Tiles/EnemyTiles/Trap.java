package BusinessLayer.Tiles.EnemyTiles;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.UnitTile;

/**
 * Class Trap represents a trap tile in the board
 */
public class Trap extends Enemy
{
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    private static final int ATTACK_RANGE = 2;

    /**
     * Trap constructor, receives all Enemy parameters,
     * visibility and invisibility times
     * @param visibilityTime The visibility time of the trap
     * @param invisibilityTime The invisibility time of the trap
     */
    public Trap(char tile, int x, int y, String name, int healthPool, int attackPoints,
                int defencePoints, IMessageCallback messageCallback, int experienceValue,
                Player player, IEnemyDeathCallback deathCallback, int visibilityTime, int invisibilityTime)
    {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints,
                messageCallback, experienceValue, player, deathCallback);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.ticksCount = 0;
        this.visible = false;
    }

    /**
     * Performs Trap state update after a game tick
     */
    @Override
    public void onGameTick()
    {
        this.visible = this.ticksCount < this.visibilityTime;
        if (this.ticksCount == (this.visibilityTime + this.invisibilityTime))
        {
            this.ticksCount = 0;
        }
        else
        {
            this.ticksCount++;
        }

        if (this.range(this.player) < ATTACK_RANGE)
        {
            this.attack(this.player);
        }
    }

    /**
     * Attack method of Trap
     * @param unit the unit to attack
     */
    @Override
    public void attack(UnitTile unit)
    {

    }

    /**
     * Represents the Trap as a String, according to its visibility state
     * @return the tile character of the Trap, if visible, else the tile of an empty tile
     */
    @Override
    public String toString()
    {
        return String.valueOf(this.visible ? this.tile : EmptyTile.EMPTY_TILE_CHAR);
    }
}

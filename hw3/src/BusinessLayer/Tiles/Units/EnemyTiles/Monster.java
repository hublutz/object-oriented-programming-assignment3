package BusinessLayer.Tiles.Units.EnemyTiles;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.UnitTile;

/**
 * Class Monster represents a monster tile in the board
 */
public class Monster extends Enemy
{
    private int visionRange;

    private static final int MOVE_UP = 0;
    private static final int MOVE_DOWN = 1;
    private static final int MOVE_LEFT = 2;
    private static final int MOVE_RIGHT = 3;
    private static final int NO_MOVEMENT = 4;


    /**
     * Monster constructor, receives all Enemy parameters and
     * the vision range of the monster
     * @param visionRange The vision range of the monster
     */
    public Monster(char tile, int x, int y, String name, int healthPool, int attackPoints,
                   int defencePoints, IMessageCallback messageCallback, int experienceValue,
                   Player player, IEnemyDeathCallback deathCallback, int visionRange)
    {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints,
                messageCallback, experienceValue, player, deathCallback);
        this.visionRange = visionRange;
    }


    /**
     * Performs Monster movement after a game tick
     */
    @Override
    public void onGameTick()
    {

    }

    /**
     * Attack method of Monster
     * @param unit the unit to attack
     */
    @Override
    public void attack(UnitTile unit)
    {

    }
}

package BusinessLayer.Tiles.Units.Players.Hunter;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

import java.util.List;

/**
 * Class Hunter represents a Hunter player type
 */
public class Hunter extends Player
{
    private int range;
    private int arrowsCount;
    private int ticksCount;

    private static final int INITIAL_TICKS_COUNT = 0;
    private static final int ARROWS_COUNT_INCREASE = 10;
    private static final int ATTACK_POINTS_LEVEL_UP_INCREASE = 2;
    private static final int DEFENCE_POINTS_LEVEL_UP_INCREASE = 1;

    /**
     * Hunter constructor
     * @param x the x-axis value of the Hunter position
     * @param y the y-axis value of the Hunter position
     * @param name            the name of the player character
     * @param healthPool      the initial health pool of the player
     * @param attackPoints    the attack points of the player
     * @param defencePoints   the defence points of the player
     * @param messageCallback used to pass messages from the player
     * @param range the Shoot ability range
     */
    public Hunter(int x, int y, String name, int healthPool, int attackPoints,
                  int defencePoints, IMessageCallback messageCallback,
                  int range)
    {
        this(name, healthPool, attackPoints, defencePoints, messageCallback,
                range);
        this.initialise(x, y);
    }

    /**
     * Hunter constructor
     * @param name            the name of the player character
     * @param healthPool      the initial health pool of the player
     * @param attackPoints    the attack points of the player
     * @param defencePoints   the defence points of the player
     * @param messageCallback used to pass messages from the player
     * @param range the Shoot ability range
     */
    public Hunter(String name, int healthPool, int attackPoints,
                  int defencePoints, IMessageCallback messageCallback,
                  int range)
    {
        super(name, healthPool, attackPoints, defencePoints, messageCallback);
        this.range = range;
        this.arrowsCount = this.playerLevel * ARROWS_COUNT_INCREASE;
        this.ticksCount = INITIAL_TICKS_COUNT;
    }

    @Override
    public void castAbility(List<Enemy> enemies)
    {

    }
}

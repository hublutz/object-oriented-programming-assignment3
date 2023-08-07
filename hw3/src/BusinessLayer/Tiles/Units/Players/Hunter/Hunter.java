package BusinessLayer.Tiles.Units.Players.Hunter;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Hunter represents a Hunter player type
 */
public class Hunter extends Player
{
    private final int range;
    private int arrowsCount;
    private int ticksCount;

    private static final int INITIAL_TICKS_COUNT = 0;
    private static final int NO_ARROWS = 0;
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

    /**
     * arrowsCount Getter
     * @return the amount of arrows of the hunter
     */
    public int getArrowsCount()
    {
        return arrowsCount;
    }

    /**
     * arrowsCount Setter
     * @param arrowsCount The new arrow count for the hunter
     */
    public void setArrowsCount(int arrowsCount)
    {
        this.arrowsCount = arrowsCount;
    }

    /**
     * This method performs the level up procedure of the Hunter player class
     */
    @Override
    public void levelUp()
    {
        super.levelUp();
        this.arrowsCount += this.playerLevel * ARROWS_COUNT_INCREASE;
        this.attackPoints += this.playerLevel * ATTACK_POINTS_LEVEL_UP_INCREASE;
        this.defencePoints += this.playerLevel * DEFENCE_POINTS_LEVEL_UP_INCREASE;
    }

    /**
     * This method performs the game tick procedure of the Hunter player class
     */
    @Override
    public void onGameTick()
    {
        super.onGameTick();
        if (this.ticksCount == ARROWS_COUNT_INCREASE)
        {
            this.arrowsCount += this.playerLevel;
            this.ticksCount = INITIAL_TICKS_COUNT;
        }
        else
        {
            this.ticksCount++;
        }
    }

    /**
     * This method casts the Shoot ability of the Hunter
     * @param enemies the enemies list of the game board
     */
    @Override
    public void castAbility(List<Enemy> enemies)
    {
        if (this.arrowsCount > NO_ARROWS)
        {
            List<Enemy> enemiesByRange = enemies.stream().sorted((enemy1, enemy2) ->
                            Double.compare(this.range(enemy1), this.range(enemy2))).
                            collect(Collectors.toList());
            if (!enemiesByRange.isEmpty() && enemiesByRange.get(0).range(this) <= this.range)
            {
                this.arrowsCount--;
                Enemy closestEnemy = enemiesByRange.get(0);

                this.messageCallback.passMessage("Hunter Shoot!");
                this.messageCallback.passMessage(this.description());
                this.messageCallback.passMessage(closestEnemy.description());

                closestEnemy.defend(this.attackPoints);
                this.checkIfEnemyIsDeadAndGetEx(closestEnemy);
                if (closestEnemy.isDead())
                {
                    closestEnemy.onDeath();
                }
            }
            else
            {
                this.messageCallback.passMessage("Can't cast Hunter ability! No enemies in range");
            }
        }
        else
        {
            this.messageCallback.passMessage("Can't cast Hunter ability! not enough arrows");
        }
    }

    /**
     * This method provides a description and statistics for the Hunter
     * @return a String containing the Hunter description
     */
    @Override
    public String description()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(super.description());

        builder.append("\t- Range: ");
        builder.append(this.range);
        builder.append('\n');
        builder.append("\t- Arrows Count: ");
        builder.append(this.arrowsCount);
        builder.append('\n');
        builder.append("\t- Ticks Count: ");
        builder.append(this.ticksCount);
        builder.append('\n');

        return builder.toString();
    }
}

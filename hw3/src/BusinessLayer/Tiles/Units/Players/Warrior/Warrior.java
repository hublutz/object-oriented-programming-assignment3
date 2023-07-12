package BusinessLayer.Tiles.Units.Players.Warrior;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Warrior extends Player {

    private static final int INITIAL_REMAINING_COOLDOWN = 0;
    private static final int TIME_INCREASE_HEALTH_ON_ABILITY = 10;
    private static final int ABILITY_RANGE = 3;
    private static final int AMOUNT_ABILITY_DAMAGE_FROM_HEALTHPOOL = 10;
    private static final int LEVEL_UP_HEALTH_POOL_INCREASE = 5;
    private static final int LEVEL_UP_ATTACK_INCREASE = 2;
    private static final int LEVEL_UP_DEFENCE_INCREASE = 1;

    private int abilityCooldown;
    private int remainingCooldown;

    /**
     * Warrior constructor
     *
     * @param abilityCooldown the ability cooldown of the warier
     */
    public Warrior(int x, int y, String name, int healthPool, int attackPoints, int defencePoints,
                   IMessageCallback messageCallback, int abilityCooldown)
    {
        this(name, healthPool, attackPoints, defencePoints, messageCallback, abilityCooldown);
        this.initialise(x, y);
    }

    /**
     * Warrior constructor
     *
     * @param abilityCooldown the ability cooldown of the warier
     */
    public Warrior(String name, int healthPool, int attackPoints, int defencePoints,
                   IMessageCallback messageCallback, int abilityCooldown)
    {
        super(name, healthPool, attackPoints, defencePoints, messageCallback);
        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = INITIAL_REMAINING_COOLDOWN;
    }

    /**
     * Casts the ability of the warrior
     * @param enemies the list of enemies in the board
     */
    @Override
    public void castAbility(List<Enemy> enemies)
    {
        if (this.remainingCooldown == INITIAL_REMAINING_COOLDOWN)
        {
            this.messageCallback.passMessage("Avenger's Shield!");
            this.remainingCooldown = this.abilityCooldown;
            try {
                this.health.increaseHealthAmount(defencePoints *
                        TIME_INCREASE_HEALTH_ON_ABILITY);
            }
            catch (Exception exception)
            {
                throw new RuntimeException(exception);
            }

            List<Enemy> enemiesInRange = enemies.stream().filter((enemy) -> this.range(enemy) < ABILITY_RANGE)
                    .collect(Collectors.toList());
            if (!enemiesInRange.isEmpty())
            {
                Enemy enemy = enemiesInRange.get(new Random().nextInt(enemiesInRange.size()));
                enemy.receiveDamage(this.health.getHealthPool() /
                        AMOUNT_ABILITY_DAMAGE_FROM_HEALTHPOOL);
                checkIfEnemyIsDeadAndGetEx(enemy);
            }
        }
        else
        {
            this.messageCallback.passMessage(
                    "The Warrior has another " + this.remainingCooldown +
                            " turns of cool down before he can cast his ability");
        }
    }

    /**
     * This method levels up the warrior player
     */
    @Override
    public void levelUp()
    {
        super.levelUp();
        this.remainingCooldown = INITIAL_REMAINING_COOLDOWN;
        try
        {
            this.health.increaseHealthPool(LEVEL_UP_HEALTH_POOL_INCREASE *
                    this.playerLevel);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        this.attackPoints += (LEVEL_UP_ATTACK_INCREASE * this.playerLevel);
        this.defencePoints += (LEVEL_UP_DEFENCE_INCREASE * this.playerLevel);

        this.messageCallback.passMessage(this.description());
    }

    /**
     * This method performs game tick procedure for the warrior
     */
    @Override
    public void onGameTick()
    {
        super.onGameTick();
        if(this.remainingCooldown > 0)
            this.remainingCooldown--;
    }

    /**
     * This method returns the description of the Warrior
     */
    @Override
    public String description()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(super.description());

        builder.append("\t- Ability Cool Down: ");
        builder.append(this.abilityCooldown);
        builder.append('\n');
        builder.append("\t- Remaining Cool Down: ");
        builder.append(this.remainingCooldown);
        builder.append('\n');

        return builder.toString();
    }




    public int getRemainingCooldown(){
        return remainingCooldown;
    }


}


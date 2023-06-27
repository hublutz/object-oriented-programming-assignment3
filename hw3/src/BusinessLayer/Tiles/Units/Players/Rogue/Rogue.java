package BusinessLayer.Tiles.Units.Players.Rogue;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Rogue extends Player {
    private static final int ABILITY_RANGE = 2;
    private static final int MAX_ENERGY = 100;
    private static final int TICK_ENERGY_INCREASE = 10;
    private static final int LEVEL_UP_ATTACK_MULTIPLIER = 3;

    private int cost;
    private int currentEnergy;

    /**
     * Tile constructor
     * @param cost the cost of the rogues ability
     */
    public Rogue(char tile, int x, int y, String name, int healthPool, int attackPoints, int defencePoints,
                 IMessageCallback messageCallback, int cost) {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints, messageCallback);
        this.currentEnergy = MAX_ENERGY;
        this.cost = cost;
    }

    /**
     * This method casts the ability of the rogue
     * @param enemies the list of enemies in the board
     */
    @Override
    public void castAbility(List<Enemy> enemies)
    {
        if(this.currentEnergy >= this.cost)
        {
            this.currentEnergy -= this.cost;
            List<Enemy> enemiesInRange = enemies.stream().filter((enemy) -> this.range(enemy) < ABILITY_RANGE)
                    .collect(Collectors.toList());
            for (Enemy enemy : enemiesInRange)
            {
                enemy.defend(this.attackPoints);
                this.checkIfEnemyIsDeadAndGetEx(enemy);
            }
        }
        else
        {
            this.messageCallback.passMessage(
                    "The Rogue doesn't have enough energy: " + this.currentEnergy +
                            " the cost of the Fan of Knives is:" + this.cost);
        }
    }

    /**
     * This method performs logic for the rogue, for each game tick
     */
    @Override
    public void onGameTick()
    {
        super.onGameTick();
        this.currentEnergy = Math.min(this.currentEnergy + TICK_ENERGY_INCREASE,
                MAX_ENERGY);
    }

    /**
     * This method levels up the rogue player
     */
    @Override
    public void levelUp()
    {
        super.levelUp();
        this.currentEnergy = MAX_ENERGY;
        this.attackPoints += (LEVEL_UP_ATTACK_MULTIPLIER * this.playerLevel);
    }

    /**
     * This method returns the description of the Rogue
     */
    @Override
    public String description()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(super.description());

        builder.append("\t- Ability Cost: ");
        builder.append(this.cost);
        builder.append('\n');
        builder.append("\t- Current Energy: ");
        builder.append(this.currentEnergy);
        builder.append('\n');

        return builder.toString();
    }
}

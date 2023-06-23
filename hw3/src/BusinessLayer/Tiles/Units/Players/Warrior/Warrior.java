package BusinessLayer.Tiles.Units.Players.Warrior;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.VisitorPattern.IVisitor;
import BusinessLayer.Tiles.WallTile;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Warrior extends Player {

    private static final int INITIAL_REMAINING_COOLDOWN = 0;
    private static final int TIME_INCREASE_HEALTH_ON_ABILITY = 10;
    private static final int ABILITY_RANGE = 3;
    private static final int AMOUNT_ABILITY_DAMAGE_FROM_HEALTHPOOL = 10;
    private int abilityCooldown;

    private int remainingCooldown;

    /**
     * Warrior constructor
     *
     * @param abilityCooldown the ability cooldown of the warier
     */
    public Warrior(char tile, int x, int y, String name, int healthPool, int attackPoints, int defencePoints,
                   IMessageCallback messageCallback, int abilityCooldown) {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints, messageCallback);
        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = INITIAL_REMAINING_COOLDOWN;
    }

    @Override
    public void castAbility(List<Enemy> enemies) {
        if (this.remainingCooldown == 0) {
            this.remainingCooldown = abilityCooldown;
            this.health.increaseHealthAmount(defencePoints * TIME_INCREASE_HEALTH_ON_ABILITY);


            List<Enemy> enemiesInRange = enemies.stream().filter((enemy) -> this.position.range(enemy.getPosition()) < ABILITY_RANGE)
                    .collect(Collectors.toList());
            Enemy enemy = enemiesInRange.get(new Random().nextInt(enemiesInRange.size()));
            enemy.receiveDamage(this.health.getHealthPool() / AMOUNT_ABILITY_DAMAGE_FROM_HEALTHPOOL);
            checkIfEnemyIsDeadAndGetEx(enemy);

        }else{
            messageCallback.passMessage("The Warrior has another " + remainingCooldown +" turns of cooldown before he can cast his ability");
        }
    }

    @Override
    public void onGameTick() {
        if(remainingCooldown>0)
            this.remainingCooldown--;
    }
}

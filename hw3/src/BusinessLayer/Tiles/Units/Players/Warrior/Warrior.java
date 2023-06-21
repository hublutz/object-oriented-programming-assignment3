package BusinessLayer.Tiles.Units.Players.Warrior;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.VisitorPattern.IVisitor;
import BusinessLayer.Tiles.WallTile;

import java.util.List;

public class Warrior extends Player {

    private static final int INITIAL_REMAINING_COOLDOWN = 0;
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

    }

    @Override
    public void onGameTick() {

    }
}

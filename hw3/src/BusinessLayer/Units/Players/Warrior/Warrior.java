package BusinessLayer.Units.Players.Warrior;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Units.Players.Player;

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
    public void castAbility() {

    }

    @Override
    public void onGameTick() {

    }
}

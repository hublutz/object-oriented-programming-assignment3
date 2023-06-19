package BusinessLayer.Players.Warrior;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Players.Player;

public class Warrior extends Player {

    private int abilityCooldown;

    private int remainingCooldown;

    /**
     * Tile constructor
     *
     * @param tile            the tile character
     * @param x               the x-axis value of the Tile
     * @param y               the y-axis value of the Tile
     * @param name
     * @param healthPool
     * @param attackPoints
     * @param defencePoints
     * @param messageCallback
     */
    public Warrior(char tile, int x, int y, String name, int healthPool, int attackPoints, int defencePoints,
                   IMessageCallback messageCallback, int abilityCooldown) {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints, messageCallback);
        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = 0;
    }

    @Override
    public void castAbility() {

    }

    @Override
    public void onGameTick() {

    }
}

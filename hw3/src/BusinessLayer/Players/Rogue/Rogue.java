package BusinessLayer.Players.Rogue;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Players.Player;

public class Rogue extends Player {
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
    public Rogue(char tile, int x, int y, String name, int healthPool, int attackPoints, int defencePoints, IMessageCallback messageCallback) {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints, messageCallback);
    }

    @Override
    public void castAbility() {

    }

    @Override
    public void onGameTick() {

    }
    @Override
    public void levelUp(){
        super.levelUp();
    }
}

package BusinessLayer.Players.Rogue;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Players.Player;

public class Rogue extends Player {
    final private int INITIAL_ENERGY =100;

    private int cost;
    private int currentEnergy;

    /**
     * Tile constructor
     * @param cost the cost of the rogues ability
     */
    public Rogue(char tile, int x, int y, String name, int healthPool, int attackPoints, int defencePoints,
                 IMessageCallback messageCallback, int cost) {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints, messageCallback);
        this.currentEnergy = INITIAL_ENERGY;
        this.cost = cost;
    }

    @Override
    public void castAbility() {
        this.currentEnergy -=this.cost;
    }

    @Override
    public void onGameTick() {

    }
    @Override
    public void levelUp(){
        super.levelUp();
    }
}

package BusinessLayer.Players;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.UnitTile;

import java.util.Random;

public abstract class Player extends UnitTile {
    final public int INITIAL_EXPERIENCE =0;
    final public int INITIAL_LEVEL =0;

    protected int experience;
    protected int playerLevel;

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
    public Player(char tile, int x, int y, String name, int healthPool, int attackPoints, int defencePoints, IMessageCallback messageCallback) {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints, messageCallback);
        this.experience = INITIAL_EXPERIENCE;
        this.playerLevel = INITIAL_LEVEL;
    }

    public void levelUp(){

    }

    public abstract void castAbility();

    @Override
    public void attack(UnitTile unitTile){
        unitTile.defend(new Random().nextInt(this.attackPoints));

        if(unitTile.isDead()){
            unitTile.onDeath(this);// I think we should remove the death observer
            // need to move player to the enemies spot
        }
    }
    @Override
    public void onDeath(UnitTile killer){

    }


}

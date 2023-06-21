package BusinessLayer.Tiles.Units;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Tile;

import java.util.Random;

public abstract class UnitTile extends Tile {

    protected String name;
    protected int attackPoints;
    protected int  defencePoints;
    protected Health health;
    protected IMessageCallback messageCallback;


    /**
     * Tile constructor
     *
     * @param tile the tile character
     * @param x    the x-axis value of the Tile
     * @param y    the y-axis value of the Tile
     */
    public UnitTile(char tile, int x, int y, String name, int healthPool, int attackPoints, int defencePoints, IMessageCallback messageCallback) {
        super(tile, x, y);
        this.name = name;
        this.health = new Health(healthPool);
        this.attackPoints = attackPoints;
        this.defencePoints = defencePoints;
        this.messageCallback = messageCallback;
    }
    /**
     * The method that is used to attack other units
     *
     * @param unit the unit to attack
     */
    public abstract void attack(UnitTile unit);

    /**
     * The method that is used to defend against attacks from other units
     *
     * @param attackRoll the attack roll of the attacking unit
     */
    public void defend(int attackRoll){
        int defendRoll = new Random().nextInt(defencePoints);
        if(attackRoll -defendRoll>0)
            health.decreaseHealthAmount(attackRoll -defendRoll);
    }

    /**
     * The methods check if the unit is dead
     * @return true if the unit is dead, else false
     */
    public boolean isDead()
    {
        return this.health.isDead();
    }

    /**
     * This method is called upon a Unit's death
     */
    public abstract void onDeath(UnitTile killer);
}

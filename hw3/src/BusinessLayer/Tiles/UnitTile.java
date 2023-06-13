package BusinessLayer.Tiles;

import BusinessLayer.Health.Health;
import BusinessLayer.IMessageCallback.IMessageCallback;

import java.util.Random;

public abstract class UnitTile extends Tile{

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

    public void attack(UnitTile unit){ //player needs to overide
        int attackRoll = new Random().nextInt(attackPoints);
        unit.defend(attackRoll);

    }

    public void defend(int attackRoll){
        int defendRoll = new Random().nextInt(defencePoints);
        if(attackRoll -defendRoll>0)
            health.decreaseHealthAmount(attackRoll -defendRoll);
    }

}

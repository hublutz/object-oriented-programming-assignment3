package BusinessLayer.Tiles.Units.EnemyTiles;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.Units.UnitTile;
import BusinessLayer.Tiles.VisitorPattern.IVisitor;
import BusinessLayer.Tiles.WallTile;

import java.util.Random;

/**
 * Abstract class Enemy represents an Enemy Tile in the game board
 */
public abstract class Enemy extends UnitTile
{
    protected int experienceValue;

    protected Player player;
    protected IEnemyDeathCallback deathCallback;

    /**
     * Enemy constructor, receives all UnitTile parameters and
     * experience value and the player reference
     * @param experienceValue Experience value the player receives upon killing the enemy
     * @param player The player tile reference
     * @param deathCallback Defines the behaviour of the enemy upon death
     */
    public Enemy(char tile, int x, int y, String name, int healthPool,
                 int attackPoints, int defencePoints, IMessageCallback messageCallback,
                 int experienceValue, Player player, IEnemyDeathCallback deathCallback)
    {
        this(tile, name, healthPool, attackPoints, defencePoints, messageCallback, experienceValue);
        this.initialise(x, y, player, deathCallback);
    }

    /**
     * Enemy constructor
     * @param tile the tile char of the enemy
     * @param name the name of the enemy
     * @param healthPool the initial health pool if the enemy
     * @param attackPoints the attack points of the enemy
     * @param defencePoints the defence points of the enemy
     * @param messageCallback used to pass messages from the enemy
     * @param experienceValue the experience given to the player upon killing the enemy
     */
    public Enemy(char tile, String name, int healthPool,
                 int attackPoints, int defencePoints, IMessageCallback messageCallback,
                 int experienceValue)
    {
        super(tile, name, healthPool, attackPoints, defencePoints, messageCallback);
        this.experienceValue = experienceValue;
    }

    /**
     * This method initialises the Enemy with its position, player
     * and death callback
     * @param x the x-axes value of the position
     * @param y the y-axes value of the position
     * @param player the player of the board
     * @param deathCallback the death callback of the enemy, called upon its death
     */
    public void initialise(int x, int y, Player player, IEnemyDeathCallback deathCallback)
    {
        this.initialise(x, y);
        this.initialise(player, deathCallback);
    }

    /**
     * This method initialises the Enemy with its player
     * and death callback
     * @param player the player of the board
     * @param deathCallback the death callback of the enemy, called upon its death
     */
    public void initialise(Player player, IEnemyDeathCallback deathCallback)
    {
        this.player = player;
        this.deathCallback = deathCallback;
    }

    /**
     * Enemy Player setter
     */
    public void setPlayer(Player player)
    {
        this.player = player;
    }

    @Override
    public void onDeath()
    {
        this.messageCallback.passMessage("Enemy " + this.name + " died");
        this.deathCallback.callEnemyDeath(this);
    }

    /**
     * experienceValue getter
     * */
    public int getExperienceValue()
    {
        return this.experienceValue;
    }

    /**
     * Enemy accept as part of visitor pattern
     */
    @Override
    public void accept(IVisitor visitor){
        visitor.visit(this);
    }

    /**
     * Enemy visits empty tile
     * */
    @Override
    public void visit(EmptyTile empty) {
        this.switchPlaces(empty);
    }
    /**
     * Enemy visits wall tile
     * */
    @Override
    public void visit(WallTile wall) {}

    /**
     * Enemy visits player tile
     * */
    @Override
    public void visit(Player player)
    {
        this.attack(player);
    }

    /**
     * Enemy attack method, enrolls a random attack value
     * and attacks the given unit
     * @param unit the unit to attack
     */
    @Override
    public void attack(UnitTile unit)
    {
        unit.defend(new Random().nextInt(this.attackPoints));
        if (unit.isDead())
        {
            unit.onDeath();
        }
    }

    /**
     * Enemy visits enemy tile
     * */
    @Override
    public void visit(Enemy enemy) {}

    /**
     * This method returns the stats of the enemy
     */
    @Override
    public String description()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(super.description());

        builder.append("\t- Enemy Experience Value: ");
        builder.append(this.experienceValue);
        builder.append('\n');

        return builder.toString();
    }
}

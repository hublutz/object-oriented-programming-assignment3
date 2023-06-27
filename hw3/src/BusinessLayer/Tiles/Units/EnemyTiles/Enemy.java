package BusinessLayer.Tiles.Units.EnemyTiles;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.Units.UnitTile;
import BusinessLayer.Tiles.VisitorPattern.IVisitor;
import BusinessLayer.Tiles.WallTile;

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
        super(tile, x, y, name, healthPool, attackPoints,
                defencePoints, messageCallback);
        this.experienceValue = experienceValue;
        this.player = player;
        this.deathCallback = deathCallback;
    }

    @Override
    public void onDeath()
    {
        this.deathCallback.callEnemyDeath(this);
    }

    /**
     * experienceValue getter
     * */
    public int getExperienceValue() {
        return experienceValue;
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
    public void visit(Player player) {
        this.attack(player);
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

package BusinessLayer.Tiles.Units.Players;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.UnitTile;
import BusinessLayer.Tiles.VisitorPattern.IVisitor;
import BusinessLayer.Tiles.WallTile;

import java.security.KeyPair;
import java.util.List;
import java.util.Random;

public abstract class Player extends UnitTile {
    private static final int LEVEL_UP_ON_TIMES_LEVEL = 50;
    private static final int ON_LEVEL_UP_ADD_ATTACK_IN_RELATION_TO_LEVEL = 4;
    private static final int ON_LEVEL_UP_ADD_DEFENCE_IN_RELATION_TO_LEVEL = 1;
    private static final int ON_LEVEL_UP_ADD_HEALTHPOOL_IN_RELATION_TO_LEVEL = 10;
    private static final char DEAD_CHAR = 'X';
    final public int INITIAL_EXPERIENCE =0;
    final public int INITIAL_LEVEL =1;

    protected int experience;
    protected int playerLevel;

    /**
     * Player constructor
     */
    public Player(char tile, int x, int y, String name, int healthPool, int attackPoints, int defencePoints, IMessageCallback messageCallback) {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints, messageCallback);
        this.experience = INITIAL_EXPERIENCE;
        this.playerLevel = INITIAL_LEVEL;
    }

    public void levelUp(){

        this.experience -=  LEVEL_UP_ON_TIMES_LEVEL * this.playerLevel;
        this.playerLevel++;
        try {
            this.health.increaseHealthPool(ON_LEVEL_UP_ADD_HEALTHPOOL_IN_RELATION_TO_LEVEL*playerLevel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.health.refillHealth();
        this.attackPoints += ON_LEVEL_UP_ADD_ATTACK_IN_RELATION_TO_LEVEL * playerLevel;
        this.defencePoints += ON_LEVEL_UP_ADD_DEFENCE_IN_RELATION_TO_LEVEL * playerLevel;
    }

    public abstract void castAbility(List<Enemy> enemies);

    @Override
    public void attack(UnitTile unitTile){
        unitTile.defend(new Random().nextInt(this.attackPoints));

    }
    @Override
    public void onDeath(){
        this.tile = DEAD_CHAR;
    }

    @Override
    public  void onGameTick(){
        while (experience >= playerLevel * LEVEL_UP_ON_TIMES_LEVEL ){
            this.levelUp();
        }
    }

    /**
     * Accept action as part of the visitor pattern in the player class
     * */
    @Override
    public void accept(IVisitor visitor){
        visitor.visit(this);
    }

    /**
     * Player visits empty tile
     * */
    @Override
    public void visit(EmptyTile empty) {
        switchPlaces(empty);
    }

    /**
     * Player visits wall tile
     * */
    @Override
    public void visit(WallTile wall) {
    }

    /**
     * Player visits player tile
     * */
    @Override
    public void visit(Player player) {

    }

    /**
     * Player visits enemy tile
     * */
    @Override
    public void visit(Enemy enemy) {
        this.attack(enemy);

        if (enemy.isDead()){
            this.experience += enemy.getExperienceValue();
            this.switchPlaces(enemy);
        }

    }

    protected void checkIfEnemyIsDeadAndGetEx(Enemy enemy){
        if (enemy.isDead())
            this.experience  += enemy.getExperienceValue();
    }


}

package BusinessLayer.Players;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.UnitTile;

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
        this.tile = DEAD_CHAR;
    }

    @Override
    public  void onGameTick(){
        while (experience >= playerLevel * LEVEL_UP_ON_TIMES_LEVEL ){
            this.levelUp();
        }
    }



}

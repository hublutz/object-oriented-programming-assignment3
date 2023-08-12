package Tests.UnitTests;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.EnemyTiles.IEnemyDeathCallback;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.Units.UnitTile;

public class TestEnemy extends Enemy {
    private static final char DEFAULT_TILE = 'e';
    private static final String DEFAULT_NAME = "Test Enemy";
    private static final int DEFAULT_ATTACK_POINTS = 10;
    private static final int DEFAULT_DEFENCE_POINTS = 10;
    private static final int DEFAULT_EXPERIENCE_VALUE = 10;


    /**

     */
    public TestEnemy(char tile, int x, int y, String name, int healthPool, int attackPoints, int defencePoints, IMessageCallback messageCallback,
                     int experienceValue, Player player, IEnemyDeathCallback deathCallback)
    {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints, messageCallback, experienceValue, player, deathCallback);

        defended = false;
    }

    public TestEnemy(int x, int y, int healthPool, IMessageCallback messageCallback,
                     Player player)
    {
        this(DEFAULT_TILE, x, y, DEFAULT_NAME, healthPool, DEFAULT_ATTACK_POINTS,
                DEFAULT_DEFENCE_POINTS, messageCallback, DEFAULT_EXPERIENCE_VALUE,
                player, null);
    }

    public boolean defended;

    @Override
    public void onGameTick() {

    }

    @Override
    public void attack(UnitTile unit) {

    }


    @Override
    public void defend(int attackRoll) {
        defended = true;
    }

    @Override
    public void receiveDamage(int amount){
        defended = true;
    }
}

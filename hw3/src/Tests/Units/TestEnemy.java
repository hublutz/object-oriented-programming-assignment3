package Tests.Units;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.EnemyTiles.IEnemyDeathCallback;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.Units.UnitTile;

public class TestEnemy extends Enemy {
    /**

     */
    public TestEnemy(char tile, int x, int y, String name, int healthPool, int attackPoints, int defencePoints, IMessageCallback messageCallback, int experienceValue, Player player, IEnemyDeathCallback deathCallback) {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints, messageCallback, experienceValue, player, deathCallback);

        defended = false;
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

    }
}

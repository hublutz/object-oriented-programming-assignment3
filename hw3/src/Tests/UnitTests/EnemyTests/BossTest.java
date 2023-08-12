package Tests.UnitTests.EnemyTests;

import BusinessLayer.GameBoard;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.EnemyTiles.EnemyMovementFactory;
import BusinessLayer.Tiles.Units.EnemyTiles.IEnemyDeathCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Monster.Boss;
import BusinessLayer.Tiles.Units.EnemyTiles.Monster.Monster;
import BusinessLayer.Tiles.Units.Movement.MoveOperations.*;
import BusinessLayer.Tiles.Units.Players.Player;
import Tests.UnitTests.AbstractUnitTest;
import Tests.UnitTests.EnemyMovementFactoryTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BossTest extends AbstractUnitTest {
    private Boss boss;
    private Player player;
    private int exValue = 0;
    private int visionRange;
    private EnemyMovementFactory  movementFactory;
    private IEnemyDeathCallback callBack = new IEnemyDeathCallback() {
        @Override
        public void callEnemyDeath(Enemy enemy) {

        }
    };
    private int abilityFreq;

    private boolean attacked;


    @Before
    public void initTest(){
        attacked = false;
        movementFactory = new EnemyMovementFactoryTest();
        abilityFreq =3;
        visionRange = 10000;
        player = new Player(x,y,name,healthPool,attackPoints,defencePoints,messageCallback) {
            @Override
            public void defend(int attackRoll){
                attacked = true;
            }
            @Override
            public void castAbility(List<Enemy> enemies) {

            }
        };
        boss = new Boss(c,x,y,name,healthPool,attackPoints,defencePoints,messageCallback,exValue,player,
                callBack, visionRange, movementFactory,abilityFreq);
    }

    @Test
    public void testOnTick(){
        boss.onGameTick();
        Assert.assertEquals("should be only one combat tick",boss.getCombatTicks(),1);
        boss.onGameTick();
        boss.onGameTick();
        boss.onGameTick();
        Assert.assertEquals("should casted an ability and reset ticks",0 ,boss.getCombatTicks());
        Assert.assertTrue("should attack player", attacked);
    }

    @Test
    public void testOnTickExitCombat(){
        boss.onGameTick();
        Assert.assertEquals("should be only one combat tick",boss.getCombatTicks(),1);
        boss.setVisionRange(-100);
        boss.onGameTick();
        Assert.assertEquals("should exited combat and reset ticks", boss.getCombatTicks(),0);
    }
}

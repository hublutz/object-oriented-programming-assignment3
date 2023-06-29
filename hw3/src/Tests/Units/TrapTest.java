package Tests.Units;

import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.EnemyTiles.Trap.Trap;
import BusinessLayer.Tiles.Units.Players.Mage.Mage;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.Units.Players.Rogue.Rogue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class TrapTest extends AbstractUnitTest{

    private Trap trap;
    private Player player;

    private boolean def;

    private int visibleTime;
    private int invisibleTime;


    @Before
    public void initTest(){
        visibleTime =1;
        invisibleTime =2;
        def = false;
        player = new Player(c,x+100,y+100,name,healthPool,attackPoints,defencePoints,messageCallback) {
            @Override
            public void defend(int attackRoll){
                def =true;
            }

            @Override
            public void castAbility(List<Enemy> enemies) {

            }
        };
        trap = new Trap(c,x,y,name,healthPool,attackPoints,defencePoints,messageCallback,0,player,null,visibleTime,invisibleTime);
    }

    @Test
    public void testVisibility(){
        trap.onGameTick();
        for(int i =0; i< visibleTime; i++){
            Assert.assertTrue("should be visible",trap.getVisible());
            Assert.assertNotEquals("should be visible", ".",trap.toString());
            trap.onGameTick();
        }

        for(int i =0; i< invisibleTime; i++){
            Assert.assertFalse("shouldn't be visible",trap.getVisible());
            Assert.assertEquals("shouldn't be visible", ".",trap.toString());

            trap.onGameTick();
        }
        trap.onGameTick();
        Assert.assertTrue("should be visible",trap.getVisible());
    }

    @Test
    public void testAttackOutOfRange(){
        trap.onGameTick();

        Assert.assertFalse("shouldn't attack player",def);
    }

    @Test
    public void testAttackInRangeClose(){
        player.move(x,y);

        trap.onGameTick();

        Assert.assertTrue("should attack player",def);
    }

    @Test
    public void testAttackInRangeFar(){
        player.move(x+2,y);

        trap.onGameTick();

        Assert.assertTrue("shouldn't attack player",def);
    }
}

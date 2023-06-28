package Tests.Units;

import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.EnemyTiles.Trap;
import BusinessLayer.Tiles.Units.Players.Mage.Mage;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.Units.Players.Rogue.Rogue;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class TrapTest extends AbstractUnitTest{

    private Trap trap;
    private Player player;

    private boolean def;

    int visibleTime;
    int invisibleTime;


    @BeforeEach
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
        for(int i =0; i< visibleTime; i++){
            Assert.assertTrue(trap.getVisible());
            trap.onGameTick();
        }

        for(int i =0; i< invisibleTime; i++){
            Assert.assertFalse(trap.getVisible());
            trap.onGameTick();
        }
        Assert.assertTrue(trap.getVisible());
    }

    @Test
    public void testAttackOutOfRange(){
        trap.onGameTick();

        Assert.assertFalse(def);
    }

    @Test
    public void testAttackInRangeClose(){
        player.move(x,y);

        trap.onGameTick();

        Assert.assertTrue(def);
    }

    @Test
    public void testAttackInRangeFar(){
        player.move(x+1,y+1);

        trap.onGameTick();

        Assert.assertTrue(def);
    }
}

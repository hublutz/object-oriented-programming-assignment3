package Tests.Units;

import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Rogue.Rogue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

public class RogueTests extends AbstractUnitTest {

    private Rogue rogue;
    private int cost;

    public RogueTests() {
        cost = 15;
    }

    @Before
    public void initTests() {
        rogue = new Rogue(c, x, y, name, healthPool, attackPoints, defencePoints, messageCallback, cost);
    }


    /**
     * tests cast Ability
     */
    @Test
    public void testCastSpell() {
        TestEnemy enemy = new TestEnemy(c, x, y, name, healthPool, attackPoints, defencePoints, messageCallback, 10, this.rogue, null);
        TestEnemy enemy1 = new TestEnemy(c, x, y, name, healthPool, attackPoints, defencePoints, messageCallback, 10, this.rogue, null);


        TestEnemy enemyToFar = new TestEnemy(c, 10, 10, name, healthPool, attackPoints, defencePoints, messageCallback, 10, this.rogue, null);

        List<Enemy> enemyList = new ArrayList<Enemy>();
        enemyList.add(enemy);
        enemyList.add(enemy1);
        enemyList.add(enemyToFar);
        rogue.castAbility(enemyList);


        Assert.assertTrue("should hit this enemy", enemy.defended);
        Assert.assertTrue("should hit this enemy", enemy1.defended);
        Assert.assertTrue("shouldn't hit this enemy because he is to far", enemyToFar.defended);
        Assert.assertEquals("cost should be decreased from energy",100 - this.cost, rogue.getCurrentEnergy());
    }

    /**
     * tests cooldown On tick
     */
    @Test
    public void OnTick() {
        TestEnemy enemy = new TestEnemy(c, x, y, name, healthPool, attackPoints, defencePoints, messageCallback, 10, this.rogue, null);

        List<Enemy> enemyList = new ArrayList<Enemy>();
        enemyList.add(enemy);
        rogue.castAbility(enemyList);

        int currentEnergy = rogue.getCurrentEnergy();

        rogue.onGameTick();

        Assert.assertEquals("current energy should be increased on tick",currentEnergy + 10, rogue.getCurrentEnergy());
    }

    /**
     * Tests level up
     */
    @Test
    public void LevelUpTest() {

        int attack = rogue.getAttack();

        rogue.levelUp();
        Assert.assertEquals("energy on level up should be refilled",100, rogue.getCurrentEnergy());
        Assert.assertEquals("attack should be updated",attack + 7 * rogue.getLevel() , rogue.getAttack());

    }


    @Test
    public void testNotEnoughEnergy() {
        rogue = new Rogue(c, x, y, name, healthPool, attackPoints, defencePoints, messageCallback, cost);

        TestEnemy enemy = new TestEnemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.rogue, null) ;

        List<Enemy> enemyList1= new ArrayList<Enemy>();
        enemyList1.add(enemy);
        rogue.setCurrentEnergy(0);

        rogue.castAbility(enemyList1);

        Assert.assertFalse("spell shouldnt be casted", enemy.defended);
    }

}

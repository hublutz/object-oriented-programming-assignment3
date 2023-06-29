package Tests.Units;

import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;

import BusinessLayer.Tiles.Units.Players.Warrior.Warrior;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

public class WarriorTests extends AbstractUnitTest {

    private Warrior warrior;
    private int abilityCooldown;

    public WarriorTests(){
        abilityCooldown =1;
    }

    @Before
    public void initTests(){
        warrior = new Warrior(c,x,y,name,healthPool,attackPoints,defencePoints,messageCallback,abilityCooldown);
    }


    /**
     * tests cast Ability
     * */
    @Test
    public void testCastSpell(){
        TestEnemy enemy = new TestEnemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.warrior, null);

        TestEnemy enemyToFar = new TestEnemy(c,10,10,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.warrior, null);

        List<Enemy> enemyList = new ArrayList<Enemy>();
        enemyList.add(enemy);
        enemyList.add(enemyToFar);
        warrior.castAbility(enemyList);


        Assert.assertTrue("should hit this enemy", enemy.defended);
        Assert.assertFalse("shouldn't hit this enemy because he is to far", enemyToFar.defended);
        Assert.assertEquals(this.abilityCooldown,warrior.getRemainingCooldown());
    }

    /**
     * tests cooldown On tick
     * */
    @Test
    public void testCoolDown(){
        TestEnemy enemy = new TestEnemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.warrior, null);

        TestEnemy enemyToFar = new TestEnemy(c,10,10,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.warrior, null);

        List<Enemy> enemyList = new ArrayList<Enemy>();
        enemyList.add(enemy);
        enemyList.add(enemyToFar);
        warrior.castAbility(enemyList);

        int currentCoolDown = warrior.getRemainingCooldown();

        warrior.onGameTick();

        Assert.assertEquals("cooldown should be updated",currentCoolDown-1,warrior.getRemainingCooldown());
    }

    /**
     * Tests level up
     * */
    @Test
    public void LevelUpTest(){


        int healthPool = warrior.getHealth().getHealthPool();
        int attack = warrior.getAttack();
        int def = warrior.getDefense();

        warrior.levelUp();
        Assert.assertEquals("cooldown should be updated",0, warrior.getRemainingCooldown());
        Assert.assertEquals("health should be updated",healthPool +5*warrior.getLevel() ,warrior.getHealth().getHealthPool());
        Assert.assertEquals("attack should be updated",attack + 6*warrior.getLevel() , warrior.getAttack());
        Assert.assertEquals("def should be updated",def +2*warrior.getLevel() ,warrior.getDefense());
    }


    @Test
    public void testNotEnoughCooldown() {
        warrior = new Warrior(c,x,y,name,healthPool,attackPoints,defencePoints,messageCallback,10);


        TestEnemy enemy = new TestEnemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.warrior, null) ;

        List<Enemy> enemyList= new ArrayList<Enemy>();
        enemyList.add(enemy);

        warrior.castAbility(enemyList);
        warrior.onGameTick();
        warrior.castAbility(enemyList);

        Assert.assertNotEquals("swcound spell shouldnt be casted", 0, warrior.getRemainingCooldown());
    }
}

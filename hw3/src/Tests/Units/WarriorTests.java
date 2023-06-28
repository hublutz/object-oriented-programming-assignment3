package Tests.Units;

import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Warrior.Warrior;
import BusinessLayer.Tiles.Units.UnitTile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

public class WarriorTests extends AbstractPlayerTest{

    Warrior warrior;
    int abilityCooldown;
    List<Boolean> def;

    public WarriorTests(){
        def = new ArrayList<Boolean>();
        abilityCooldown =1;
    }

    @BeforeAll
    public void initTests(){
        warrior = new Warrior(c,x,y,name,healthPool,attackPoints,defencePoints,messageCallback,abilityCooldown);
    }


    /**
     * tests cast Ability
     * */
    @Test
    public void testCastSpell(){
        Enemy enemy = new Enemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.warrior, null) {



            @Override
            public void attack(UnitTile unit) {

            }

            @Override
            public void  defend(int attackRoll){
                def.add(true);
            }

            @Override
            public void onGameTick() {

            }
        };

        Enemy enemyToFar = new Enemy(c,10,10,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.warrior, null) {

            @Override
            public void attack(UnitTile unit) {

            }

            @Override
            public void  defend(int attackRoll){
                def.add(true);
            }

            @Override
            public void onGameTick() {

            }
        };

        List<Enemy> l= new ArrayList<Enemy>();
        l.add(enemy);
        l.add(enemyToFar);
        warrior.castAbility(l);


        Assert.assertTrue("should hit this enemy", def.get(0));
        Assert.assertFalse("shouldn't hit this enemy because he is to far", def.get(1));
        Assert.assertEquals(this.abilityCooldown,warrior.getRemainingCooldown());
    }

    /**
     * tests cooldown On tick
     * */
    @Test
    public void testCoolDown(){
        Enemy enemy = new Enemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.warrior, null) {



            @Override
            public void attack(UnitTile unit) {

            }

            @Override
            public void  defend(int attackRoll){
                def.add(true);
            }

            @Override
            public void onGameTick() {

            }
        };

        Enemy enemyToFar = new Enemy(c,10,10,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.warrior, null) {

            @Override
            public void attack(UnitTile unit) {

            }

            @Override
            public void  defend(int attackRoll){
                def.add(true);
            }

            @Override
            public void onGameTick() {

            }
        };

        List<Enemy> l= new ArrayList<Enemy>();
        l.add(enemy);
        l.add(enemyToFar);
        warrior.castAbility(l);

        int currentCoolDown = warrior.getRemainingCooldown();

        warrior.onGameTick();

        Assert.assertEquals(currentCoolDown-1,warrior.getRemainingCooldown());
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
        Assert.assertEquals(0, warrior.getRemainingCooldown());
        Assert.assertEquals(healthPool +5*warrior.getLevel() ,warrior.getHealth().getHealthPool());
        Assert.assertEquals(attack + 6*warrior.getLevel() , warrior.getAttack());
        Assert.assertEquals(def +2*warrior.getLevel() ,warrior.getDefense());
    }
}

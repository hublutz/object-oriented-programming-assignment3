package Tests.Units;

import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Rogue.Rogue;
import BusinessLayer.Tiles.Units.Players.Warrior.Warrior;
import BusinessLayer.Tiles.Units.UnitTile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

public class RogueTests extends AbstractPlayerTest {

    Rogue rogue;
    int cost;
    List<Boolean> def;

    public RogueTests() {
        def = new ArrayList<Boolean>();
        cost = 15;
    }

    @BeforeAll
    public void initTests() {
        rogue = new Rogue(c, x, y, name, healthPool, attackPoints, defencePoints, messageCallback, cost);
    }


    /**
     * tests cast Ability
     */
    @Test
    public void testCastSpell() {
        Enemy enemy = new Enemy(c, x, y, name, healthPool, attackPoints, defencePoints, messageCallback, 10, this.rogue, null) {


            @Override
            public void attack(UnitTile unit) {

            }

            @Override
            public void defend(int attackRoll) {
                def.add(true);
            }

            @Override
            public void onGameTick() {

            }
        };
        Enemy enemy1 = new Enemy(c, x, y, name, healthPool, attackPoints, defencePoints, messageCallback, 10, this.rogue, null) {


            @Override
            public void attack(UnitTile unit) {

            }

            @Override
            public void defend(int attackRoll) {
                def.add(true);
            }

            @Override
            public void onGameTick() {

            }
        };


        Enemy enemyToFar = new Enemy(c, 10, 10, name, healthPool, attackPoints, defencePoints, messageCallback, 10, this.rogue, null) {

            @Override
            public void attack(UnitTile unit) {

            }

            @Override
            public void defend(int attackRoll) {
                def.add(true);
            }

            @Override
            public void onGameTick() {

            }
        };

        List<Enemy> l = new ArrayList<Enemy>();
        l.add(enemy);
        l.add(enemy1);
        l.add(enemyToFar);
        rogue.castAbility(l);


        Assert.assertTrue("should hit this enemy", def.get(0));
        Assert.assertTrue("should hit this enemy", def.get(1));
        Assert.assertTrue("shouldn't hit this enemy because he is to far", def.size() < 3);
        Assert.assertEquals(100 - this.cost, rogue.getCurrentEnergy());
    }

    /**
     * tests cooldown On tick
     */
    @Test
    public void OnTick() {
        Enemy enemy = new Enemy(c, x, y, name, healthPool, attackPoints, defencePoints, messageCallback, 10, this.rogue, null) {


            @Override
            public void attack(UnitTile unit) {

            }

            @Override
            public void defend(int attackRoll) {
                def.add(true);
            }

            @Override
            public void onGameTick() {

            }
        };


        List<Enemy> l = new ArrayList<Enemy>();
        l.add(enemy);
        rogue.castAbility(l);

        int currentEnergy = rogue.getCurrentEnergy();

        rogue.onGameTick();

        Assert.assertEquals(currentEnergy + 10, rogue.getCurrentEnergy());
    }

    /**
     * Tests level up
     */
    @Test
    public void LevelUpTest() {

        int levelB = rogue.getLevel();
        int attack = rogue.getAttack();

        rogue.levelUp();
        Assert.assertEquals(100, rogue.getCurrentEnergy());
        Assert.assertEquals(attack + 7 * rogue.getLevel() , rogue.getAttack());

    }

}

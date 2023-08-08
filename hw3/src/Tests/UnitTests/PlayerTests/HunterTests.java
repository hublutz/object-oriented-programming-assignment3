package Tests.UnitTests.PlayerTests;

import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Hunter.Hunter;
import Tests.UnitTests.AbstractUnitTest;
import Tests.UnitTests.TestEnemy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Class HunterTests in responsible for testing the Hunter methods and
 * procedures
 */
public class HunterTests extends AbstractUnitTest
{
    private Hunter hunter;
    private final int range;

    public HunterTests()
    {
        this.range = 10;
    }

    /**
     * This method initialises a new Hunter player before each test
     */
    @Before
    public void initTests()
    {
        this.hunter = new Hunter(this.x, this.y, this.name,
                this.healthPool, this.attackPoints, this.defencePoints,
                this.messageCallback, this.range);
    }

    /**
     * This method tests the Shoot ability is cast when the arrows count is
     * large enough and there is an enemy in range
     */
    @Test
    public void testShootAbilityOneEnemySuccess()
    {
        TestEnemy testEnemy = new TestEnemy(this.x, this.y, 1000,
                this.messageCallback, this.hunter);
        List<Enemy> enemyList = List.of(testEnemy);
        int arrowsCount = 1;
        this.hunter.setArrowsCount(arrowsCount);

        this.hunter.castAbility(enemyList);

        Assert.assertTrue("The enemy should have been hit", testEnemy.defended);
        Assert.assertEquals("The arrows count should decrease to " + (arrowsCount - 1),
                arrowsCount - 1, this.hunter.getArrowsCount());
    }

    /**
     * This method tests the Shoot ability fails if the enemy is too far
     */
    @Test
    public void testShootAbilityEnemyTooFarFailure()
    {
        TestEnemy testEnemy = new TestEnemy(1000, 1000, 1000,
                this.messageCallback, this.hunter);
        List<Enemy> enemyList = List.of(testEnemy);
        int arrowsCount = 1;
        this.hunter.setArrowsCount(arrowsCount);

        this.hunter.castAbility(enemyList);

        Assert.assertFalse("The enemy shouldn't have been hit - it's too far", testEnemy.defended);
        Assert.assertEquals("The arrows count should be " + arrowsCount,
                arrowsCount, this.hunter.getArrowsCount());
    }

    /**
     * This method tests the Shoot ability fails if the Hunter doesn't have enough arrows
     */
    @Test
    public void testShootAbilityNoArrowsFailure()
    {
        TestEnemy testEnemy = new TestEnemy(0, 0, 1000,
                this.messageCallback, this.hunter);
        List<Enemy> enemyList = List.of(testEnemy);
        int noArrowsAmount = 0;
        this.hunter.setArrowsCount(noArrowsAmount);

        this.hunter.castAbility(enemyList);

        Assert.assertFalse("The enemy shouldn't have been hit - no arrows", testEnemy.defended);
        Assert.assertEquals("The arrows count should be " + noArrowsAmount,
                noArrowsAmount, this.hunter.getArrowsCount());
    }

    /**
     * This method tests the Shoot ability fails if there are no enemies at all
     */
    @Test
    public void testShootAbilityNoEnemiesFailure()
    {
        List<Enemy> enemyList = new ArrayList<>();
        int arrowsCount = 1;
        this.hunter.setArrowsCount(arrowsCount);

        this.hunter.castAbility(enemyList);

        Assert.assertEquals("The arrows count should be " + arrowsCount,
                arrowsCount, this.hunter.getArrowsCount());
    }

    /**
     * This method tests the Shoot ability hits only one enemy, the closest, when there are multiple
     * in range
     */
    @Test
    public void testShootAbilityMultipleEnemiesInRangeSuccess()
    {
        TestEnemy testEnemy1 = new TestEnemy(this.x, this.y, 1000,
                this.messageCallback, this.hunter);
        TestEnemy testEnemy2 = new TestEnemy(this.x + 1, this.y + 1, 1000,
                this.messageCallback, this.hunter);
        TestEnemy testEnemy3 = new TestEnemy(this.x + 2, this.y + 1, 1000,
                this.messageCallback, this.hunter);
        List<Enemy> enemyList = List.of(testEnemy1, testEnemy2);
        int arrowsCount = 3;
        this.hunter.setArrowsCount(arrowsCount);

        this.hunter.castAbility(enemyList);

        Assert.assertTrue("The closest enemy should have been hit", testEnemy1.defended);
        Assert.assertFalse("The further enemy shouldn't have been hit", testEnemy2.defended);
        Assert.assertFalse("The further enemy shouldn't have been hit", testEnemy3.defended);
        Assert.assertEquals("The arrows count should decrease to " + (arrowsCount - 1),
                arrowsCount - 1, this.hunter.getArrowsCount());
    }

    /**
     * This method tests only one enemy is hit in the Shoot ability when
     * two have the same range from the Hunter
     */
    @Test
    public void testShootAbilitySameRangeSuccess()
    {
        TestEnemy testEnemy1 = new TestEnemy(this.x, this.y, 1000,
                this.messageCallback, this.hunter);
        TestEnemy testEnemy2 = new TestEnemy(this.x, this.y, 1000,
                this.messageCallback, this.hunter);
        List<Enemy> enemyList = List.of(testEnemy1, testEnemy2);
        int arrowsCount = 2;
        this.hunter.setArrowsCount(arrowsCount);

        this.hunter.castAbility(enemyList);

        // XOR returns true if only one value is true. If both are false or both are true - it returns false
        Assert.assertTrue("Only one enemy should have been hit", testEnemy1.defended ^ testEnemy2.defended);
        Assert.assertEquals("The arrows count should decrease to " + (arrowsCount - 1),
                arrowsCount - 1, this.hunter.getArrowsCount());
    }

    /**
     * This method tests the Hunter ticks count increases by one
     * on a "normal" game tick
     */
    @Test
    public void testGameTickIncrementSuccess()
    {
        int previousTickCount = this.hunter.getTicksCount();

        this.hunter.onGameTick();

        Assert.assertEquals("The ticks count should increase by 1",
                previousTickCount + 1, this.hunter.getTicksCount());
    }

    /**
     * This method tests the Hunter ticks count resets upon 10 ticks
     * and the Hunter gets new arrows
     */
    @Test
    public void testGameTickIncrementArrowsCountSuccess()
    {
        int initialTicksCount = 0;
        int playerLevel = this.hunter.getLevel();
        int previousArrowsCount = this.hunter.getArrowsCount();

        int ticksForIncrease = 10;
        for (int i = 0; i <= ticksForIncrease; i++)
        {
            this.hunter.onGameTick();
        }

        Assert.assertEquals("The ticks count should reset to " + initialTicksCount,
                initialTicksCount, this.hunter.getTicksCount());
        Assert.assertEquals("The arrows count should increase by " + playerLevel,
                previousArrowsCount + playerLevel, this.hunter.getArrowsCount());
    }

    /**
     * This method tests the level up procedure of the Hunter player class succeeds
     */
    @Test
    public void testHunterLevelUpSuccess()
    {
        final int arrowsCountIncrease = 10;
        final int playerAttackIncrease = 4, playerDefenceIncrease = 1;
        final int hunterAttackIncrease = 2, hunterDefenceIncrease = 1;

        int previousLevel = this.hunter.getLevel();
        int expectedLevel = previousLevel + 1;
        int previousArrowsCount = this.hunter.getArrowsCount();
        int expectedArrowsCount = previousArrowsCount + (arrowsCountIncrease * expectedLevel);
        int previousAttackPoints = this.hunter.getAttack();
        int expectedAttackPoints = previousAttackPoints + (playerAttackIncrease * expectedLevel) + (hunterAttackIncrease * expectedLevel);
        int previousDefencePoints = this.hunter.getDefense();
        int expectedDefencePoints = previousDefencePoints + (playerDefenceIncrease * expectedLevel) + (hunterDefenceIncrease * expectedLevel);

        this.hunter.levelUp();
        int currentLevel = this.hunter.getLevel();

        Assert.assertEquals("The Hunter level should increase by 1",
                expectedLevel, currentLevel);
        Assert.assertEquals("The Hunter arrows count should increase by " + arrowsCountIncrease + " * level",
                expectedArrowsCount, this.hunter.getArrowsCount());
        Assert.assertEquals("The Hunter attack points should have regular player increase and " + hunterAttackIncrease + " * level",
                expectedAttackPoints, this.hunter.getAttack());
        Assert.assertEquals("The Hunter defence points should have regular player increase and " + hunterDefenceIncrease + " * level",
                expectedDefencePoints, this.hunter.getDefense());
    }
}

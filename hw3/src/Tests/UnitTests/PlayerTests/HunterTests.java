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
}

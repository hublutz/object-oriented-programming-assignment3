package Tests.UnitTests;

import BusinessLayer.Tiles.Units.Health;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HealthTests {

    private Health health;
    private int initialHealthPool;
    @Before
    public void initTest(){
        initialHealthPool =100;
        health = new Health(initialHealthPool);
    }

    @Test
    public void isDeadTest(){
        health = new Health(1);
        try {
            health.decreaseHealthAmount(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue("should be dead",health.isDead());
    }
    @Test
    public void isDeadTestBelowZero(){
        health = new Health(1);
        try {
            health.decreaseHealthAmount(100);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue("should be dead",health.isDead());
    }

    @Test
    public void decreaseHealth(){
        try {
            health.decreaseHealthAmount(10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals("health should be decreased",initialHealthPool -10, health.getHealthAmount());
    }

    @Test
    public void decreaseBelowLimitHealth(){
        try {
            health.decreaseHealthAmount(1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(" health should be zero",0, health.getHealthAmount());
    }

    @Test(expected = RuntimeException.class)
    public void decreaseHealthNegative(){
        try {
            health.decreaseHealthAmount(-10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals("health shouldn't change",initialHealthPool, health.getHealthAmount());
    }

    @Test
    public void increaseHealth(){
        try {
            health.decreaseHealthAmount(10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            health.increaseHealthAmount(5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals("health should decrease",initialHealthPool -5, health.getHealthAmount());
    }

    @Test
    public void increaseHealthAbovePool(){
        try {
            health.increaseHealthAmount(5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals("healthPool shouldn't  increase",initialHealthPool, health.getHealthAmount());
    }

    @Test(expected = RuntimeException.class)
    public void increaseHealthNegative(){
        try {
            health.increaseHealthAmount(-5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals("health shouldnt increase",initialHealthPool, health.getHealthAmount());
    }

    @Test
    public void increaseHealthPool(){
        try {
            health.increaseHealthPool(5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals("healthPool should increase",initialHealthPool +5, health.getHealthPool());
    }

    @Test(expected = RuntimeException.class)
    public void increaseHealthPoolNegative(){
        try {
            health.increaseHealthPool(-5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}

package Tests.Units;

import BusinessLayer.Tiles.Units.Health;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class HealthTests {

    private Health health;
    private int healthAmount;
    @BeforeEach
    public void initTest(){
        healthAmount =100;
        health = new Health(healthAmount);
    }

    @Test
    public void isDeadTest(){
        health = new Health(1);
        health.decreaseHealthAmount(1);
        Assert.assertTrue(health.isDead());
    }
    @Test
    public void isDeadTestBelowZero(){
        health = new Health(1);
        health.decreaseHealthAmount(100);
        Assert.assertTrue(health.isDead());
    }

    @Test
    public void decreaseHealth(){
        health.decreaseHealthAmount(10);
        Assert.assertEquals(healthAmount -10, health.getHealthAmount());
    }

    @Test
    public void decreaseBelowLimitHealth(){
        health.decreaseHealthAmount(1000);
        Assert.assertEquals(0, health.getHealthAmount());
    }

    @Test
    public void decreaseHealthNegative(){
        health.decreaseHealthAmount(-10);
        Assert.assertEquals(healthAmount, health.getHealthAmount());
    }

    @Test
    public void increaseHealth(){
        health.decreaseHealthAmount(10);
        health.increaseHealthAmount(5);
        Assert.assertEquals(healthAmount -5, health.getHealthAmount());
    }

    @Test
    public void increaseHealthAbovePool(){
        health.increaseHealthAmount(5);
        Assert.assertEquals(healthAmount, health.getHealthAmount());
    }

    @Test
    public void increaseHealthNegative(){
        health.increaseHealthAmount(-5);
        Assert.assertEquals(healthAmount, health.getHealthAmount());
    }

    @Test
    public void increaseHealthPool(){
        try {
            health.increaseHealthPool(5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(healthAmount +5, health.getHealthPool());
    }

    @Test(expected = RuntimeException.class)
    public void increaseHealthPoolNegative(){
        try {
            health.increaseHealthPool(5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}

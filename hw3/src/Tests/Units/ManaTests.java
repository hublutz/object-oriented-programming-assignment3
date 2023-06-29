package Tests.Units;
import BusinessLayer.Tiles.Units.Players.Mage.Mana;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManaTests {

    private Mana mana;
    private int startingMana;

    @Before
    public void initTests(){
        startingMana = 100;
        mana = new Mana(startingMana);
        mana.refillMana(startingMana);

    }

    /**
     * Test initial mana
     * */
    @Test
    public void testInitialMana(){
        mana = new Mana(startingMana);
        Assert.assertEquals("initial mana should be starting mana /4", startingMana/4 , mana.getManaAmount());
    }

    /**
     * Test initial manaPool
     * */
    @Test
    public void testInitialManaPool(){
        Assert.assertEquals("initial mana poll should be starting mana", startingMana , mana.getManaPool());
    }

    /**
     * Tests adding mana more than limit
     * */
    @Test
    public void testAddMoreThenLimit(){
        startingMana = 100;
        mana = new Mana(startingMana);
        mana.refillMana(startingMana);
        Assert.assertEquals("shouldn't add beyond pool", startingMana, mana.getManaAmount());
    }

    /**
     * Tests increasing mana limit with negative num
     * */
    @Test(expected = RuntimeException.class)
    public void testIncreaseManaPoolFail(){
        try {
           mana.increaseManaPool(-10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Tests increasing mana limit with positive num
     * */
    @Test
    public void testIncreaseManaPool(){

        int increaseAmount = 10;
        try {
            mana.increaseManaPool(increaseAmount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        mana.refillMana(increaseAmount);

        Assert.assertEquals("should increase by " + increaseAmount, startingMana +increaseAmount, mana.getManaAmount());
    }

    /**
     * Tests using mana with negative num
     * */
    @Test(expected = RuntimeException.class)
    public void testUseManaFail(){
        try {
            mana.useMana(-10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Tests using mana with positive num
     * */
    @Test
    public void testUseMana(){
        int amount =10;
        try {
            mana.useMana(amount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals("mana should reduce by " + amount, this.startingMana - amount, mana.getManaAmount());
    }

}

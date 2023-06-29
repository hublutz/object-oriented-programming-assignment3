package Tests.Units;


import BusinessLayer.Tiles.Units.Players.Mage.Mage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class PlayerTests extends AbstractUnitTest {

    private Mage mage;

    @BeforeEach
    public void initTests(){
        mage = new Mage(c,x,y,name,healthPool,attackPoints,defencePoints,messageCallback,1,1,1,1,1);
    }

    @Test
    public void testLevelUp(){
        mage.setExperience(50);
        mage.receiveDamage(1);
        int levelB = mage.getLevel();
        int attackB = mage.getAttack();
        int defB = mage.getDefense();

        mage.onGameTick();

        Assert.assertEquals("level should be updated",levelB+1, mage.getLevel());
        Assert.assertEquals("attack should be updated",attackB + mage.getLevel()*4, mage.getAttack());
        Assert.assertEquals("def should be updated",defB + mage.getLevel(), mage.getDefense());
        Assert.assertEquals("health should be updated",mage.getHealth().getHealthPool(), mage.getHealth().getHealthAmount());

    }

    @Test
    public void testLevelUpTwoLevels(){
        mage.setExperience(100);

        int levelB = mage.getLevel();

        mage.onGameTick();

        Assert.assertEquals("level should be updated",levelB+2, mage.getLevel());
    }

    @Test
    public void testLevelUpLess50Ex(){
        mage.setExperience(49);
        int levelB = mage.getLevel();

        mage.onGameTick();

        Assert.assertEquals("level shouldn't be updated",levelB, mage.getLevel());

    }


}
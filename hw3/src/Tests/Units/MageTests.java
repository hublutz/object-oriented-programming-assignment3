package Tests.Units;

import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Mage.Mage;
import BusinessLayer.Tiles.Units.UnitTile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class MageTests  extends AbstractUnitTest {

    private Mage mage;
    public int abRange;
    public int hits;
    public int manaPool ;
    public int manaCost;
    public int spellPower;

    @BeforeAll
    public void BeforeAll(){
        hits =3;
        abRange = 3;
        manaPool = 100;
        manaCost = 10;
        spellPower = 10;
    }

    @BeforeEach
    public void initTests(){

        mage = new Mage(this.c,this.x,this.y, this.name, healthPool, attackPoints, defencePoints, messageCallback,abRange,hits,spellPower,manaCost,manaPool);
    }

    /**
     * tests cast Ability
     * */
    @Test
    public void testCastSpell(){
        TestEnemy enemy = new TestEnemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.mage, null) ;
        TestEnemy enemy1 = new TestEnemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.mage, null);
        TestEnemy enemy2 = new TestEnemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.mage, null) ;

        TestEnemy enemyToManyHits = new TestEnemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.mage, null);

        TestEnemy enemyToFar = new TestEnemy(c,10,10,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.mage, null);


        List<TestEnemy> enemyList= new ArrayList<TestEnemy>();
        List<Enemy> enemyList2 = new ArrayList<Enemy>();
        enemyList.add(enemy);
        enemyList2.add(enemy);
        enemyList.add(enemy1);
        enemyList2.add(enemy1);
        enemyList.add(enemy2);
        enemyList2.add(enemy2);
        enemyList.add(enemyToManyHits);
        enemyList2.add(enemyToManyHits);
        enemyList.add(enemyToFar);
        enemyList2.add(enemyToFar);

        mage.castAbility(enemyList2);

        int totalHits =0;
        for (TestEnemy e: enemyList)
            if(e.defended)
                totalHits++;


        Assert.assertEquals("should hit 3 of the enemies at random", totalHits,this.hits);
        Assert.assertFalse("shouldn't hit this enemy because he is to far", enemyToFar.defended);
        Assert.assertEquals("mana should be lower", manaPool-manaCost, mage.getMana().getManaAmount());
    }


    /**
     * Tests level up
     * */
    @Test
    public void LevelUpTest(){

        int levelB = mage.getLevel();
        int manaPool = mage.getMana().getManaPool();
        int spellPower = mage.getSpellPower();
        mage.levelUp();
        Assert.assertEquals("mana pool should be updated",mage.getMana().getManaPool(), manaPool + 25*levelB);
        Assert.assertEquals("spell power should be updated",mage.getSpellPower(),spellPower +10*levelB);
    }

    /**
     * Tests on tick
     * */
    @Test
    public void OnTickTest(){

        fullManaPoolTick();
        emptyManaPoolTick();
    }

    private void fullManaPoolTick() {
        int manaAmount =mage.getMana().getManaAmount();
        mage.onGameTick();
        Assert.assertEquals("mana should be updated",manaAmount,mage.getMana().getManaAmount());
    }

    private void emptyManaPoolTick() {
        try {
            mage.getMana().useMana(this.manaPool-1);
        } catch (Exception e) {

        }
        int manaAmount =mage.getMana().getManaAmount();
        mage.onGameTick();
        Assert.assertEquals("mana amount should be updated",manaAmount,mage.getMana().getManaAmount() +mage.getLevel());
    }

    @Test
    public void testNotEnoughMana() {
        mage = new Mage(this.c,this.x,this.y, this.name, healthPool, attackPoints, defencePoints, messageCallback,abRange,hits,spellPower,1000000,manaPool);
        int manaB = mage.getMana().getManaAmount();

        TestEnemy enemy = new TestEnemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.mage, null) ;

        List<Enemy> enemyList1= new ArrayList<Enemy>();
        enemyList1.add(enemy);

        mage.castAbility(enemyList1);

        Assert.assertEquals("spell shouldnt be casted", manaB, mage.getMana().getManaAmount());
    }
}

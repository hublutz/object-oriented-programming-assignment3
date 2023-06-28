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
    public List<Boolean> def;

    @BeforeAll
    public void BeforeAll(){
        def = new ArrayList<Boolean>();
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
        Enemy enemy = new Enemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.mage, null) {



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
        Enemy enemy1 = new Enemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.mage, null) {

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
        Enemy enemy2 = new Enemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.mage, null) {

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

        Enemy enemyToManyHits = new Enemy(c,x,y,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.mage, null) {
           ;
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

        Enemy enemyToFar = new Enemy(c,10,10,name,healthPool,attackPoints,defencePoints, messageCallback, 10, this.mage, null) {

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
        l.add(enemy1);
        l.add(enemy2);
        l.add(enemyToManyHits);
        l.add(enemyToFar);
        mage.castAbility(l);

        int totalHits =0;
        for (boolean b: def)
            if(b)
                totalHits++;


        Assert.assertEquals("should hit 3 of the enemies at random", totalHits,this.hits);
        Assert.assertFalse("shouldn't hit this enemy because he is to far", def.get(4));
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
        Assert.assertEquals(mage.getMana().getManaPool(), manaPool + 25*levelB);
        Assert.assertEquals(mage.getSpellPower(),spellPower +10*levelB);
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
        Assert.assertEquals(manaAmount,mage.getMana().getManaAmount());
    }

    private void emptyManaPoolTick() {
        try {
            mage.getMana().useMana(this.manaPool-1);
        } catch (Exception e) {

        }
        int manaAmount =mage.getMana().getManaAmount();
        mage.onGameTick();
        Assert.assertEquals(manaAmount,mage.getMana().getManaAmount() +mage.getLevel());
    }


}

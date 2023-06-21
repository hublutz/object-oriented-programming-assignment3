package BusinessLayer.Players.Mage;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Players.Player;

public class Mage extends Player {

    private static final int TIMES_INCREASE_POOL_ON_LEVEL_UP = 25;
    private static final int DIV_TIMES_REFILL_ON_LEVEL_UP = 4;
    private Mana mana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    /**
     * Mage constructor
     *
     * @param manaCost the initial mana cost of spells
     * @param spellPower the initial spell power
     * @param hitsCount the initial hitsCount
     * @param abilityRange the initial ability range

     */
    public Mage(char tile, int x, int y, String name, int healthPool, int attackPoints, int defencePoints,
                IMessageCallback messageCallback, int abilityRange, int hitsCount, int spellPower, int manaCost, int manaPool) {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints, messageCallback);

        this.manaCost = manaCost;
        this.spellPower =spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
        this.mana = new Mana(manaPool);

    }

    @Override
    public void castAbility() {
        try {
            if(mana.useMana(this.manaCost)){

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onGameTick() {

    }

    @Override
    public void levelUp(){
        super.levelUp();

        try {
            mana.increaseManaPool(TIMES_INCREASE_POOL_ON_LEVEL_UP * this
                    .playerLevel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.mana.refillMana(mana.getManaPool()/DIV_TIMES_REFILL_ON_LEVEL_UP);

    }
}

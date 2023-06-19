package BusinessLayer.Players.Mage;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Players.Player;

public class Mage extends Player {

    private Mana mana;
    private int manaCost;
    private int spellPower;
    private int hitsCount;
    private int abilityRange;

    /**
     * Tile constructor
     *
     * @param tile            the tile character
     * @param x               the x-axis value of the Tile
     * @param y               the y-axis value of the Tile
     * @param name
     * @param healthPool
     * @param attackPoints
     * @param defencePoints
     * @param messageCallback
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

    }

    @Override
    public void onGameTick() {

    }

    @Override
    public void levelUp(){

    }
}

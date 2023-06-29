package BusinessLayer.Tiles.Units.Players.Mage;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class represents the Mage player type
 */
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
    public Mage(int x, int y, String name, int healthPool, int attackPoints, int defencePoints,
                IMessageCallback messageCallback, int abilityRange, int hitsCount, int spellPower, int manaCost, int manaPool) {
        super(x, y, name, healthPool, attackPoints, defencePoints, messageCallback);

        this.manaCost = manaCost;
        this.spellPower =spellPower;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
        this.mana = new Mana(manaPool);
    }

    /**
     * Mage constructor
     * @param manaCost the initial mana cost of spells
     * @param spellPower the initial spell power
     * @param hitsCount the initial hitsCount
     * @param abilityRange the initial ability range
     */
    public Mage(String name, int healthPool, int attackPoints, int defencePoints,
                IMessageCallback messageCallback, int abilityRange, int hitsCount,
                int spellPower, int manaCost, int manaPool) {
        this(0, 0, name, healthPool, attackPoints, defencePoints, messageCallback,
                abilityRange, hitsCount, spellPower, manaCost, manaPool);
    }

    /**
     * This method cast the ability of the mage
     * @param enemies the list of enemies in the board
     */
    @Override
    public void castAbility(List<Enemy> enemies)
    {
        try
        {
            if(mana.useMana(this.manaCost))
            {
                this.messageCallback.passMessage("Blizzard!");
                List<Enemy> enemiesInRange = enemies.stream().
                        filter((enemy -> this.range(enemy) < abilityRange))
                        .collect(Collectors.toList());
                int hits =0;

                while (hits < hitsCount && !enemiesInRange.isEmpty())
                {
                    Enemy enemy = enemiesInRange.get(new Random().nextInt(enemiesInRange.size()));
                    enemy.defend(this.spellPower);
                    checkIfEnemyIsDeadAndGetEx(enemy);
                    if(enemy.isDead())
                        enemiesInRange.remove(enemy);
                    hits++;
                }
            }
            else
            {
                this.messageCallback.passMessage(
                        "The Mage doesn't have enough mana: " + this.mana.getManaAmount() +
                                " the cost of Blizzard is:" + this.manaCost);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method performs logic for the mage for each game tick
     */
    @Override
    public void onGameTick()
    {
        super.onGameTick();
        this.mana.refillMana(this.playerLevel);
    }

    /**
     * This method performs the level up procedure for a mage
     */
    @Override
    public void levelUp()
    {
        super.levelUp();

        try
        {
            mana.increaseManaPool(TIMES_INCREASE_POOL_ON_LEVEL_UP *
                    this.playerLevel);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        this.mana.refillMana(mana.getManaPool() / DIV_TIMES_REFILL_ON_LEVEL_UP);
        this.spellPower += (10 * this.playerLevel);

        this.messageCallback.passMessage(this.description());
    }

    /**
     * This method returns the description of the Mage
     */
    @Override
    public String description()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(super.description());

        builder.append("\t- Mana Pool: ");
        builder.append(this.mana.getManaPool()); builder.append('\n');
        builder.append("\t- Mana Amount: ");
        builder.append(this.mana.getManaAmount()); builder.append('\n');
        builder.append("\t- Mana Cost: ");
        builder.append(this.manaCost); builder.append('\n');
        builder.append("\t- Spell Power: ");
        builder.append(this.spellPower); builder.append('\n');
        builder.append("\t- Hits Count: ");
        builder.append(this.hitsCount); builder.append('\n');
        builder.append("\t- Ability Range: ");
        builder.append(this.abilityRange); builder.append('\n');

        return builder.toString();
    }

    public Mana getMana(){
        return mana;
    }

    public int getSpellPower(){
        return spellPower;
    }
}

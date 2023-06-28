package BusinessLayer.Tiles.Units.Players.Mage;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
    public void castAbility(List<Enemy> enemies) {
        try {
            if(mana.useMana(this.manaCost)){
                List<Enemy> enemiesInRange = enemies.stream().filter((enemy -> this.position.range(enemy.getPosition())< abilityRange))
                        .collect(Collectors.toList());
                int hits =0;
                while (hits< hitsCount && !enemiesInRange.isEmpty()) {
                    Enemy enemy = enemiesInRange.get(new Random().nextInt(enemiesInRange.size()));
                    enemy.defend(spellPower);
                    checkIfEnemyIsDeadAndGetEx(enemy);
                    if(enemy.isDead())
                        enemiesInRange.remove(enemy);
                    hits++;
                }
            }else{
                messageCallback.passMessage("The Mage doesn't have enough mana: " + mana.getManaAmount() +" the cost of Blizzard is:" + manaCost);
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

    public Mana getMana(){
        return mana;
    }

    public int getSpellPower(){
        return spellPower;
    }
}

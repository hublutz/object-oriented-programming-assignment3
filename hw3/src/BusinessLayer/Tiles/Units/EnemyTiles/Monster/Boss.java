package BusinessLayer.Tiles.Units.EnemyTiles.Monster;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.EnemyTiles.EnemyMovementFactory;
import BusinessLayer.Tiles.Units.EnemyTiles.IEnemyDeathCallback;
import BusinessLayer.Tiles.Units.HeroicUnit;
import BusinessLayer.Tiles.Units.Movement.MoveOperations.MoveOperation;
import BusinessLayer.Tiles.Units.Players.Player;

import java.util.Collection;
import java.util.List;

public class Boss extends Monster implements HeroicUnit {

    private final int abilityFrequency;
    private int combatTicks;


    public Boss(char tile, int x, int y, String name, int healthPool, int attackPoints, int defencePoints, IMessageCallback messageCallback,
                int experienceValue, Player player, IEnemyDeathCallback deathCallback, int visionRange, EnemyMovementFactory movementFactory,
                int abilityFrequency) {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints, messageCallback, experienceValue, player, deathCallback, visionRange, movementFactory);
        this.combatTicks = 0;
        this.abilityFrequency = abilityFrequency;
    }

    public Boss(char tile, String name, int healthPool, int attackPoints, int defencePoints, IMessageCallback messageCallback,
                int experienceValue, int visionRange, EnemyMovementFactory movementFactory, int abilityFrequency) {
        super(tile, name, healthPool, attackPoints, defencePoints, messageCallback, experienceValue, visionRange, movementFactory);
        this.combatTicks = 0;
        this.abilityFrequency = abilityFrequency;
    }

    @Override
    public void castAbility(Collection<Enemy>... args) {
        messageCallback.passMessage("Boss special ability!");
        messageCallback.passMessage(this.description());
        player.defend(this.attackPoints);
        if(player.isDead())
            player.onDeath();
        messageCallback.passMessage("Boss attacked player " + player.description());
    }

    @Override
    public void onGameTick() {
        MoveOperation moveOperation;

        if (this.range(this.player) < this.visionRange) {
            if (this.combatTicks == this.abilityFrequency) {
                this.combatTicks = 0;
                this.castAbility();
                moveOperation = this.movementFactory.
                        getMoveOperation(EnemyMovementFactory.EnemyMovements.NO_MOVEMENT);
            } else {
                this.combatTicks++;
                int dx = this.getX() - this.player.getX();
                int dy = this.getY() - this.player.getY();
                moveOperation = getPlayerApproachMoveOperation (dx,dy);
            }

        } else {
            this.combatTicks =0;
            moveOperation = this.movementFactory.getRandomMovement();
        }
        moveOperation.move(this);
    }

    @Override
    public String description() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.description());

        builder.append("\t- Boss Ability Frequency: ");
        builder.append(this.abilityFrequency);
        builder.append("\t- Boss Combat Ticks: ");
        builder.append(this.combatTicks);
        builder.append('\n');

        return builder.toString();
    }
    /**
     * used only in tests
     * */
    public int getCombatTicks(){
        return  combatTicks;
    }
    /**
     * used only in tests
     * */
    public void setVisionRange(int newVision){
        this.visionRange = newVision;
    }
}
    

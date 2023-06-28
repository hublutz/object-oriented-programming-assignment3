package BusinessLayer.Tiles.Units.Players.Rogue;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Rogue extends Player {
    private static final int ABILITY_RANGE = 2;
    final private int INITIAL_ENERGY =100;

    private int cost;
    private int currentEnergy;

    /**
     * Tile constructor
     * @param cost the cost of the rogues ability
     */
    public Rogue(char tile, int x, int y, String name, int healthPool, int attackPoints, int defencePoints,
                 IMessageCallback messageCallback, int cost) {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints, messageCallback);
        this.currentEnergy = INITIAL_ENERGY;
        this.cost = cost;
    }

    @Override
    public void castAbility(List<Enemy> enemies) {
        if(this.currentEnergy >= this.cost) {
            this.currentEnergy -= this.cost;
            enemies.stream().filter((enemy) -> this.position.range(enemy.getPosition()) < ABILITY_RANGE)
                    .forEach(enemy -> enemy.defend(attackPoints));
        }else{
            messageCallback.passMessage("The Rogue doesn't have enough energy: " + currentEnergy +" the cost of the Fan of Knives is:" + cost);
        }
    }

    @Override
    public void onGameTick() {

    }
    @Override
    public void levelUp(){
        super.levelUp();
    }

    public int getCurrentEnergy(){
        return currentEnergy;
    }

    public int getAttack() {
        return attackPoints;
    }
}

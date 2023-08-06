package BusinessLayer.Tiles.Units.EnemyTiles.Monster;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.EnemyTiles.EnemyMovementFactory;
import BusinessLayer.Tiles.Units.EnemyTiles.IEnemyDeathCallback;
import BusinessLayer.Tiles.Units.Movement.MoveOperations.MoveOperation;
import BusinessLayer.Tiles.Units.Players.Player;

/**
 * Class Monster represents a monster tile in the board
 */
public class Monster extends Enemy
{
    protected int visionRange;
    protected final EnemyMovementFactory movementFactory;


    /**
     * Monster constructor, receives all Enemy parameters and
     * the vision range of the monster
     * @param visionRange The vision range of the monster
     * @param movementFactory The movement factory for the monster
     */
    public Monster(char tile, int x, int y, String name, int healthPool, int attackPoints,
                   int defencePoints, IMessageCallback messageCallback, int experienceValue,
                   Player player, IEnemyDeathCallback deathCallback, int visionRange,
                   EnemyMovementFactory movementFactory)
    {
        this(tile, name, healthPool, attackPoints, defencePoints, messageCallback, experienceValue,
                visionRange, movementFactory);
        this.initialise(x, y, player, deathCallback);
    }

    /**
     * Monster constructor, receives all Enemy parameters and
     * the vision range of the monster
     * @param visionRange The vision range of the monster
     * @param movementFactory The movement factory for the monster
     */
    public Monster(char tile, String name, int healthPool, int attackPoints,
                   int defencePoints, IMessageCallback messageCallback, int experienceValue,
                   int visionRange, EnemyMovementFactory movementFactory)
    {
        super(tile, name, healthPool, attackPoints, defencePoints, messageCallback, experienceValue);
        this.movementFactory = movementFactory;
        this.visionRange = visionRange;
    }


    /**
     * Performs Monster movement after a game tick
     */
    @Override
    public void onGameTick()
    {
        MoveOperation moveOperation;

        if (this.range(this.player) < this.visionRange)
        {
            int dx = this.getX() - this.player.getX();
            int dy = this.getY() - this.player.getY();
            if (Math.abs(dx) > Math.abs(dy))
            {
                if (dx > 0)
                    moveOperation = this.movementFactory.
                            getMoveOperation(EnemyMovementFactory.EnemyMovements.MOVE_LEFT);
                else
                    moveOperation = this.movementFactory.
                            getMoveOperation(EnemyMovementFactory.EnemyMovements.MOVE_RIGHT);
            }
            else
            {
                if (dy > 0)
                    moveOperation = this.movementFactory.
                            getMoveOperation(EnemyMovementFactory.EnemyMovements.MOVE_UP);
                else
                    moveOperation = this.movementFactory.
                            getMoveOperation(EnemyMovementFactory.EnemyMovements.MOVE_DOWN);
            }
        }
        else
            moveOperation = this.movementFactory.getRandomMovement();

        moveOperation.move(this);
    }

    /**
     * This method returns the stats of the monster
     */
    @Override
    public String description() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.description());

        builder.append("\t- Monster Vision Range: ");
        builder.append(this.visionRange);
        builder.append('\n');

        return builder.toString();
    }
}

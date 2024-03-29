package BusinessLayer.Tiles.Units.EnemyTiles.Monster;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.EnemyTiles.IEnemyDeathCallback;
import BusinessLayer.Tiles.Units.MoveOperations.MoveOperation;
import BusinessLayer.Tiles.Units.Players.Player;

/**
 * Class Monster represents a monster tile in the board
 */
public class Monster extends Enemy
{
    private int visionRange;
    private final MonsterMovementFactory movementFactory;


    /**
     * Monster constructor, receives all Enemy parameters and
     * the vision range of the monster
     * @param visionRange The vision range of the monster
     * @param movementFactory The movement factory for the monster
     */
    public Monster(char tile, int x, int y, String name, int healthPool, int attackPoints,
                   int defencePoints, IMessageCallback messageCallback, int experienceValue,
                   Player player, IEnemyDeathCallback deathCallback, int visionRange,
                   MonsterMovementFactory movementFactory)
    {
        super(tile, x, y, name, healthPool, attackPoints, defencePoints,
                messageCallback, experienceValue, player, deathCallback);
        this.visionRange = visionRange;

        this.movementFactory = movementFactory;
    }

    /**
     * Monster constructor, receives all Enemy parameters and
     * the vision range of the monster
     * @param visionRange The vision range of the monster
     * @param movementFactory The movement factory for the monster
     */
    public Monster(char tile, String name, int healthPool, int attackPoints,
                   int defencePoints, IMessageCallback messageCallback, int experienceValue,
                   Player player, IEnemyDeathCallback deathCallback, int visionRange,
                   MonsterMovementFactory movementFactory)
    {
        this(tile, 0, 0, name, healthPool, attackPoints, defencePoints,
                messageCallback, experienceValue, player, deathCallback,
                visionRange, movementFactory);
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
                            getMoveOperation(MonsterMovementFactory.MonsterMovements.MOVE_LEFT);
                else
                    moveOperation = this.movementFactory.
                            getMoveOperation(MonsterMovementFactory.MonsterMovements.MOVE_RIGHT);
            }
            else
            {
                if (dx > 0)
                    moveOperation = this.movementFactory.
                            getMoveOperation(MonsterMovementFactory.MonsterMovements.MOVE_UP);
                else
                    moveOperation = this.movementFactory.
                            getMoveOperation(MonsterMovementFactory.MonsterMovements.MOVE_DOWN);
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

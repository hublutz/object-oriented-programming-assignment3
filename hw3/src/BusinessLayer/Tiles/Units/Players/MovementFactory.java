package BusinessLayer.Tiles.Units.Players;

import BusinessLayer.GameBoard;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.MoveOperations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Class MovementFactory generates MoveOperations according to chars given
 * from the CLI
 */
public class MovementFactory
{
    public enum PlayerMovements
    {
        MOVE_UP, MOVE_DOWN, MOVE_RIGHT, MOVE_LEFT,
        CAST_ABILITY, NO_MOVEMENT
    }

    private Map<PlayerMovements, Supplier<MoveOperation>> movementsMap;
    private GameBoard currentGameBoard;
    private final List<Enemy> enemyList;
    private final Player player;

    /**
     * MovementFactory constructor
     * @param enemyList the list of enemies in the game
     * @param player the player of the game
     */
    public MovementFactory(List<Enemy> enemyList, Player player)
    {
        this.enemyList = enemyList;
        this.player = player;
        this.currentGameBoard = null;
        this.initialiseMovementsMap();
    }

    /**
     * This method initialises the movements hash map of the factory
     */
    private void initialiseMovementsMap()
    {
        this.movementsMap = new HashMap<>();
        this.movementsMap.put(PlayerMovements.MOVE_UP, () -> new MoveUpOperation(this.currentGameBoard));
        this.movementsMap.put(PlayerMovements.MOVE_DOWN, () -> new MoveDownOperation(this.currentGameBoard));
        this.movementsMap.put(PlayerMovements.MOVE_LEFT, () -> new MoveLeftOperation(this.currentGameBoard));
        this.movementsMap.put(PlayerMovements.MOVE_RIGHT, () -> new MoveRightOperation(this.currentGameBoard));
        this.movementsMap.put(PlayerMovements.CAST_ABILITY, () -> new CastAbilityMoveOperation(this.player, this.enemyList));
        this.movementsMap.put(PlayerMovements.NO_MOVEMENT, NothingMoveOperation::new);
    }

    /**
     * Setter of currentGameBoard
     * @param gameBoard the new game board
     */
    public void setCurrentGameBoard(GameBoard gameBoard)
    {
        this.currentGameBoard = gameBoard;
    }

    /**
     * This method creates a movement operation according to given char
     * @param movement the requested movement
     * @return A move operation requested
     */
    public MoveOperation getMoveOperation(PlayerMovements movement) throws Exception
    {
        if (this.movementsMap.containsKey(movement))
        {
            return this.movementsMap.get(movement).get();
        }
        else
        {
            throw new Exception("The requested movement is illegal");
        }
    }
}

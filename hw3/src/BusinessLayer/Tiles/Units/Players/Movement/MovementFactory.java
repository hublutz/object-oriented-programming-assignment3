package BusinessLayer.Tiles.Units.Players.Movement;

import BusinessLayer.GameBoard;
import BusinessLayer.Tiles.Units.Movement.MoveOperations.*;

import java.util.HashMap;
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

    /**
     * MovementFactory constructor
     */
    public MovementFactory(GameBoard currentGameBoard)
    {
        this.currentGameBoard = currentGameBoard;
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
        this.movementsMap.put(PlayerMovements.CAST_ABILITY, () -> new CastAbilityMoveOperation(this.currentGameBoard.getPlayer(),
                this.currentGameBoard.getEnemyList()));
        this.movementsMap.put(PlayerMovements.NO_MOVEMENT, NothingMoveOperation::new);
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

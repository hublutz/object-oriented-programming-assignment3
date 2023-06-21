package BusinessLayer.Tiles.Units.EnemyTiles;

import BusinessLayer.Tiles.Units.MoveOperations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Class MonsterMovementFactory is a factory of monster movements
 */
public class MonsterMovementFactory
{
    /**
     * Enum MonsterMovements represents codes for monster movements, used in MonsterMovementFactory
     */
    public enum MonsterMovements
    {
        MOVE_UP,
        MOVE_DOWN,
        MOVE_LEFT,
        MOVE_RIGHT,
        NO_MOVEMENT
    }

    private final Map<MonsterMovements, Supplier<MoveOperation>> movements;

    /**
     * MonsterMovementFactory constructor
     */
    public MonsterMovementFactory()
    {
        this.movements = new HashMap<>();
        this.initialiseMovements();
    }

    /**
     * This method initialises the movements map
     */
    private void initialiseMovements()
    {
        this.movements.put(MonsterMovements.MOVE_UP, MoveUpOperation::new);
        this.movements.put(MonsterMovements.MOVE_DOWN, MoveDownOperation::new);
        this.movements.put(MonsterMovements.MOVE_LEFT, MoveLeftOperation::new);
        this.movements.put(MonsterMovements.MOVE_RIGHT, MoveRightOperation::new);
        this.movements.put(MonsterMovements.NO_MOVEMENT, NothingMoveOperation::new);
    }

    /**
     * Thi method gets the movement
     * @param movement the requested movement
     * @return a MoveOperation of the requested type
     */
    public MoveOperation getMoveOperation(MonsterMovements movement)
    {
        return this.movements.get(movement).get();
    }

    /**
     * This method returns a random move operation
     * @return a random move operation
     */
    public MoveOperation getRandomMovement()
    {
        Random random = new Random();
        ArrayList<Supplier<MoveOperation>> moveOperationsArray = new ArrayList<>(this.movements.values());
        return moveOperationsArray.get(random.nextInt(moveOperationsArray.size())).get();
    }
}

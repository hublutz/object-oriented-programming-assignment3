package Tests.UnitTests;

import BusinessLayer.GameBoard;
import BusinessLayer.Tiles.Units.EnemyTiles.EnemyMovementFactory;
import BusinessLayer.Tiles.Units.Movement.MoveOperations.MoveOperation;
import BusinessLayer.Tiles.Units.Movement.MoveOperations.MoveUpOperation;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

public class EnemyMovementFactoryTest extends EnemyMovementFactory {

    public EnemyMovementFactoryTest(){

    }

    @Override
    public MoveOperation getMoveOperation(EnemyMovements movement)
    {
        return new TestOperation();
    }

    public MoveOperation getRandomMovement()
    {
        return new TestOperation();
    }

}

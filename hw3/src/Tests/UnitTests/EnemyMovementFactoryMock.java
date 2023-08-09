package Tests.UnitTests;


import BusinessLayer.Tiles.Units.EnemyTiles.EnemyMovementFactory;
import BusinessLayer.Tiles.Units.Movement.MoveOperations.MoveOperation;


public class EnemyMovementFactoryMock extends EnemyMovementFactory {

    public EnemyMovementFactoryMock(){

    }

    @Override
    public MoveOperation getMoveOperation(EnemyMovements movement)
    {
        return new MockOperation();
    }

    public MoveOperation getRandomMovement()
    {
        return new MockOperation();
    }

}

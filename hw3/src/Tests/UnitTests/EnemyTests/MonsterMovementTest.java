package Tests.UnitTests.EnemyTests;

import BusinessLayer.GameBoard;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.EnemyTiles.EnemyMovementFactory;
import BusinessLayer.Tiles.Units.EnemyTiles.IEnemyDeathCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Monster.Monster;
import BusinessLayer.Tiles.Units.Movement.MoveOperations.MoveDownOperation;
import BusinessLayer.Tiles.Units.Movement.MoveOperations.MoveLeftOperation;
import BusinessLayer.Tiles.Units.Movement.MoveOperations.MoveRightOperation;
import BusinessLayer.Tiles.Units.Movement.MoveOperations.MoveUpOperation;
import BusinessLayer.Tiles.Units.Players.Player;
import Tests.UnitTests.AbstractUnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MonsterMovementTest extends AbstractUnitTest {

    private Monster monster;
    private Player player;
    private int exValue = 0;
    private int visionRange = 0;
    private EnemyMovementFactory  movementFactory;
    private IEnemyDeathCallback callBack = new IEnemyDeathCallback() {
        @Override
        public void callEnemyDeath(Enemy enemy) {

        }
    };

    @Before
    public void initTest(){
        movementFactory = new EnemyMovementFactory( null);
        monster = new Monster(c,x,y,name,healthPool,attackPoints,defencePoints,messageCallback,exValue,player,
                callBack, visionRange, movementFactory);
    }

    @Test
    public void testGetMovement(){
        Assert.assertTrue("should be up",monster.getPlayerApproachMoveOperation(1,1) instanceof MoveUpOperation);
        Assert.assertTrue("should be down",monster.getPlayerApproachMoveOperation(-1,-1) instanceof MoveDownOperation);
        Assert.assertTrue("should be left",monster.getPlayerApproachMoveOperation(1,0) instanceof MoveLeftOperation);
        Assert.assertTrue("should be right",monster.getPlayerApproachMoveOperation(-3,-2) instanceof MoveRightOperation);

    }

}

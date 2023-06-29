package BusinessLayer;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.MoveOperations.MoveObservable;
import BusinessLayer.Tiles.Units.MoveOperations.MoveOperation;
import BusinessLayer.Tiles.Units.Players.MovementFactory;
import BusinessLayer.Tiles.Units.Players.Player;

import java.util.Iterator;
import java.util.List;

/**
 * Class GameManager is responsible for running the game procedure
 */
public class GameManager
{
    private Player player;
    private PlayerMovementConverter movementConverter;
    private MovementFactory playerMovementFactory;
    private MoveObservable moveObservable;
    private IMessageCallback messageCallback;
    private Iterator<GameBoard> gameBoardSupplier;
    private List<Enemy> enemyList;

    /**
     * GameManager constructor
     * @param player The player of the game
     * @param playerMovementConverter Movement converter, used to generate which type of movement to make
     * @param moveObservable Observable, notifies observers to move
     * @param enemyList The enemy list of the game
     * @param messageCallback Used to pass messages
     * @param gameBoardSupplier Iterator that supplies the manager with game boards
     */
    public GameManager(Player player, PlayerMovementConverter playerMovementConverter,
                       MoveObservable moveObservable, List<Enemy> enemyList, IMessageCallback messageCallback,
                       Iterator<GameBoard> gameBoardSupplier)
    {
        this.player = player;
        this.enemyList = enemyList;
        this.movementConverter = playerMovementConverter;
        this.playerMovementFactory = new MovementFactory(this.enemyList, this.player);
        this.moveObservable = moveObservable;
        this.messageCallback = messageCallback;
        this.gameBoardSupplier = gameBoardSupplier;
    }

    /**
     * This method runs the game, loading every game board in the iterator
     * @throws Exception Throws an exception if an illegal movement was gotten
     */
    public void runGame() throws Exception
    {
        while (this.gameBoardSupplier.hasNext())
        {
            GameBoard currentGameBoard = this.gameBoardSupplier.next();
            this.player.getHealth().refillHealth();
            while (!this.player.isDead() && !this.enemyList.isEmpty())
            {
                MovementFactory.PlayerMovements movement = this.movementConverter.generatePlayerMovement();
                MoveOperation moveOperation = this.playerMovementFactory.getMoveOperation(movement);
                this.moveObservable.notifyObservers(moveOperation);
                currentGameBoard.tick();
            }
            if (player.isDead())
            {
                this.messageCallback.passMessage("Game Over! You lost");
                return;
            }
        }

        this.messageCallback.passMessage("Game Over! You won");
    }
}

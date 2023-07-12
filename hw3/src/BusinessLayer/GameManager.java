package BusinessLayer;

import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.MoveOperations.MoveObservable;
import BusinessLayer.Tiles.Units.MoveOperations.MoveOperation;
import BusinessLayer.Tiles.Units.Players.MovementFactory;
import BusinessLayer.Tiles.Units.Players.Player;

/**
 * Class GameManager is responsible for running the game procedure
 */
public class GameManager
{
    private PlayerMovementConverter movementConverter;
    private MoveObservable moveObservable;
    private IMessageCallback messageCallback;
    private AbstractGameBoardIterator gameBoardSupplier;

    /**
     * GameManager constructor
     * @param playerMovementConverter Movement converter, used to generate which type of movement to make
     * @param moveObservable Observable, notifies observers to move
     * @param messageCallback Used to pass messages
     * @param gameBoardSupplier Iterator that supplies the manager with game boards
     */
    public GameManager(PlayerMovementConverter playerMovementConverter,
                       MoveObservable moveObservable, IMessageCallback messageCallback,
                       AbstractGameBoardIterator gameBoardSupplier)
    {
        this.movementConverter = playerMovementConverter;
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
            Player currentPlayer = currentGameBoard.getPlayer();
            this.moveObservable.addObserver(currentPlayer);

            MovementFactory playerMovementFactory = new MovementFactory(currentGameBoard);

            while (!currentGameBoard.isPlayerDead()&& !currentGameBoard.isLevelFinished())
            {
                this.messageCallback.passMessage("<~~ Current Board: ~~>");
                this.messageCallback.passMessage(currentGameBoard.toString());

                MovementFactory.PlayerMovements movement = this.movementConverter.generatePlayerMovement();
                MoveOperation moveOperation = playerMovementFactory.getMoveOperation(movement);

                this.moveObservable.notifyObservers(moveOperation);
                currentGameBoard.tick();
            }

            this.moveObservable.removeObserver(currentPlayer);
            if (currentGameBoard.isPlayerDead())
            {
                this.messageCallback.passMessage("Game Over! You lost");
                return;
            }
        }

        this.messageCallback.passMessage("Game Over! You won");
    }
}

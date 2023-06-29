package PresentationLayer;

import BusinessLayer.GameBoard;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.MoveOperations.*;
import BusinessLayer.Tiles.Units.Players.Player;

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
    private Map<Character, Supplier<MoveOperation>> movementsMap;
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
        this.movementsMap.put('w', () -> new MoveUpOperation(this.currentGameBoard));
        this.movementsMap.put('s', () -> new MoveDownOperation(this.currentGameBoard));
        this.movementsMap.put('a', () -> new MoveLeftOperation(this.currentGameBoard));
        this.movementsMap.put('d', () -> new MoveRightOperation(this.currentGameBoard));
        this.movementsMap.put('e', () -> new CastAbilityMoveOperation(this.player, this.enemyList));
        this.movementsMap.put('q', NothingMoveOperation::new);
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
     * @param movementChar the char representing which movement to make
     * @return A move operation corresponding to the char
     */
    public MoveOperation getMoveOperation(char movementChar) throws Exception
    {
        if (this.movementsMap.containsKey(movementChar))
        {
            return this.movementsMap.get(movementChar).get();
        }
        else
        {
            throw new Exception("The given movement char is illegal");
        }
    }
}

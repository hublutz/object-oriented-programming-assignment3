package PresentationLayer.Factories;

import BusinessLayer.GameBoard;
import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.EnemyTiles.Monster.Monster;
import BusinessLayer.Tiles.Units.EnemyTiles.Monster.MonsterMovementFactory;
import BusinessLayer.Tiles.Units.EnemyTiles.RemoveEnemyDeathCallback;
import BusinessLayer.Tiles.Units.EnemyTiles.Trap.Trap;
import BusinessLayer.Tiles.Units.Players.Mage.Mage;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.Units.Players.Rogue.Rogue;
import BusinessLayer.Tiles.Units.Players.Warrior.Warrior;
import BusinessLayer.Tiles.WallTile;
import PresentationLayer.PrintMessageCallback;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.Map;
import java.util.stream.Collectors;

public class TileFactory
{
    private List<Supplier<Player>> playersList;
    private Map<Character, Supplier<Enemy>> enemiesMap;
    private Player selectedPlayer;
    private MonsterMovementFactory monsterMovementFactory;
    private List<Enemy> enemyList;
    private GameBoard currentGameBoard;
    private final IMessageCallback messageCallback;

    public TileFactory(List<Enemy> enemyList)
    {
        this.initialisePlayersList();
        this.initialiseEnemyMap();
        this.selectedPlayer = null;
        this.monsterMovementFactory = new MonsterMovementFactory(null);
        this.enemyList = enemyList;
        this.currentGameBoard = null;
        this.messageCallback = new PrintMessageCallback();
    }

    /**
     * This method initialises the list of players available
     */
    private void initialisePlayersList()
    {
        this.playersList = Arrays.asList(
                () -> new Warrior("Jon Snow", 300, 30, 4,
                        this.messageCallback, 3),
                () -> new Warrior("The Hound", 400, 20, 6,
                        this.messageCallback, 5),
                () -> new Mage("Melisandre", 100, 5, 1,
                        this.messageCallback, 300, 30, 15, 5, 6),
                () -> new Mage("Thoros of Myr", 250, 25, 4,
                        this.messageCallback, 150, 20, 20, 3, 4),
                () -> new Rogue("Arya Stark", 150, 40, 2,
                        this.messageCallback, 20),
                () -> new Rogue("Bronn", 250, 35, 3,
                        this.messageCallback, 50)
        );
    }

    /**
     * This method initialises the enemy map
     */
    private void initialiseEnemyMap()
    {
        List<Supplier<Enemy>> enemies = Arrays.asList(
                () -> new Monster('s', "Lannister Solider", 80, 8, 3, this.messageCallback,
                        25, this.selectedPlayer, new RemoveEnemyDeathCallback(this.currentGameBoard),
                        3, this.monsterMovementFactory),
                () -> new Monster('k', "Lannister Knight", 200, 14, 8, this.messageCallback,
                        50, this.selectedPlayer, new RemoveEnemyDeathCallback(this.currentGameBoard),
                        4, this.monsterMovementFactory),
                () -> new Monster('q', "Queen's Guard", 400, 20, 15, this.messageCallback,
                        100, this.selectedPlayer, new RemoveEnemyDeathCallback(this.currentGameBoard),
                        5, this.monsterMovementFactory),
                () -> new Monster('z', "Wright", 600, 30, 15, this.messageCallback,
                        100, this.selectedPlayer, new RemoveEnemyDeathCallback(this.currentGameBoard),
                        3, this.monsterMovementFactory),
                () -> new Monster('b', "Bear-Wright", 1000, 75, 30, this.messageCallback,
                        250, this.selectedPlayer, new RemoveEnemyDeathCallback(this.currentGameBoard),
                        4, this.monsterMovementFactory),
                () -> new Monster('g', "Giant-Wright",1500, 100, 40, this.messageCallback,
                        500, this.selectedPlayer, new RemoveEnemyDeathCallback(this.currentGameBoard),
                        5, this.monsterMovementFactory),
                () -> new Monster('w', "White Walker", 2000, 150, 50, this.messageCallback,
                        1000, this.selectedPlayer, new RemoveEnemyDeathCallback(this.currentGameBoard),
                        6, this.monsterMovementFactory),
                () -> new Monster('M', "The Mountain", 1000, 60, 25, this.messageCallback,
                        500, this.selectedPlayer, new RemoveEnemyDeathCallback(this.currentGameBoard),
                        6, this.monsterMovementFactory),
                () -> new Monster('C', "Queen Cersei", 100, 10, 10, this.messageCallback,
                        1000, this.selectedPlayer, new RemoveEnemyDeathCallback(this.currentGameBoard),
                        1, this.monsterMovementFactory),
                () -> new Monster('K', "Night's King", 5000, 300, 150, this.messageCallback,
                        5000, this.selectedPlayer, new RemoveEnemyDeathCallback(this.currentGameBoard),
                        8, this.monsterMovementFactory),
                () -> new Trap('B', "Bonus Trap", 1, 1, 1, this.messageCallback, 250,
                        this.selectedPlayer, new RemoveEnemyDeathCallback(this.currentGameBoard), 1, 10),
                () -> new Trap('Q', "Queen's Trap", 250, 50, 10, this.messageCallback, 100,
                        this.selectedPlayer, new RemoveEnemyDeathCallback(this.currentGameBoard) , 3, 10),
                () -> new Trap('D', "Death Trap", 500, 100, 20, this.messageCallback, 250,
                        this.selectedPlayer, new RemoveEnemyDeathCallback(this.currentGameBoard), 1, 10)
        );

        this.enemiesMap = enemies.stream().collect(Collectors.
                toMap(s -> s.get().getTile(), Function.identity()));
    }

    /**
     * Setter for selectedPlayer
     * @param player the player chosen
     */
    public void setSelectedPlayer(Player player)
    {
        this.selectedPlayer = player;
    }

    /**
     * Setter for currentGameBoard
     * @param gameBoard the current board of the game
     */
    public void setCurrentGameBoard(GameBoard gameBoard)
    {
        this.currentGameBoard = gameBoard;
        this.monsterMovementFactory.setGameBoard(gameBoard);
    }

    /**
     * This method returns a list of available players to choose from
     */
    public List<Player> getPlayersList()
    {
        return this.playersList.stream().map(Supplier::get).
                collect(Collectors.toList());
    }

    /**
     * This method creates a new enemy according to the given char
     * @param enemyTile the tile of the enemy to create
     * @param x the x-axis value of the enemy
     * @param y the y-axis value of the enemy
     * @return A new enemy chosen
     * @throws Exception Is the given enemy tile is illegal
     */
    public Enemy createEnemy(char enemyTile, int x, int y) throws Exception
    {
        if (this.enemiesMap.containsKey(enemyTile))
        {
            Enemy enemy = this.enemiesMap.get(enemyTile).get();
            enemy.move(x, y);
            this.enemyList.add(enemy);
            return enemy;
        }
        else
        {
            throw new Exception("The requested enemy tile is illegal");
        }
    }

    /**
     * This method produces a new player according to the chosen player index
     * @param chosenPlayerIndex the index of the chosen player type
     * @param x the x-axis value of the player
     * @param y the y-axis value of the player
     * @return The player chosen
     */
    public Player createPlayer(int chosenPlayerIndex, int x, int y)
    {
        Player player = this.playersList.get(chosenPlayerIndex).get();
        this.setSelectedPlayer(player);
        player.move(x, y);
        return player;
    }

    /**
     * This method creates a new Empty Tile
     * @param x the x-axis value of the empty tile
     * @param y the y-axis value of the empty tile
     * @return The new empty tile
     */
    public EmptyTile createEmptyTile(int x, int y)
    {
        return new EmptyTile(x, y);
    }

    /**
     * This method creates a new Wall Tile
     * @param x the x-axis value of the wall tile
     * @param y the y-axis value of the wall tile
     * @return The new wall tile
     */
    public WallTile createWallTile(int x, int y)
    {
        return new WallTile(x, y);
    }
}

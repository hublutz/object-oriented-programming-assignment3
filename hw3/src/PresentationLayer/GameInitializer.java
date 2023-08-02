package PresentationLayer;

import BusinessLayer.GameBoard;
import BusinessLayer.GameManager;
import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.Tiles.Units.Players.Movement.PlayerMovementConverter;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Movement.Observer.MoveObservable;
import BusinessLayer.Tiles.Units.Players.Player;
import PresentationLayer.Factories.TileFactory;
import PresentationLayer.MessageCallbacks.PrintMessageCallback;
import PresentationLayer.Movements.CLIMoveObservable;
import PresentationLayer.Movements.CLIPlayerMovementConverter;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * CLass GameInitializer is responsible for initializing the game from the
 * CLI presentation layer
 */
public class GameInitializer
{
    private String levelsFolder;
    private List<Enemy> gameEnemyList;
    private TileFactory tileFactory;
    private int chosenPlayerIndex;

    /**
     * GameInitializer constructor
     * @param args the command line arguments of the program
     */
    public GameInitializer(String[] args)
    {
        this.levelsFolder = this.extractLevelsFolder(args);;
        this.gameEnemyList = new ArrayList<>();
        this.tileFactory = new TileFactory(this.gameEnemyList);
    }

    /**
     * This method extracts the levels folder given as a command line args
     * @param args the command line arguments of the program
     * @return the levels folder given to the game, if valid
     */
    private String extractLevelsFolder(String[] args)
    {
        if (args.length < 1)
        {
            throw new IllegalArgumentException("You must provide a levels folder for the game!");
        }

        String folderName = args[0];
        File folder = new File(folderName);
        if (!folder.exists())
        {
            throw new IllegalArgumentException("The given levels folder doesn't exist");
        }

        return folderName;
    }

    /**
     * This method allows the player to choose its character
     */
    public void choosePlayer()
    {
        List<Player> availablePlayers = this.tileFactory.getPlayersList();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Dungeons and Dragons!");
        System.out.println("<~~ Choose your player: ~~>");
        for (int i = 0; i < availablePlayers.size(); i++)
        {
            System.out.println("Player " + (i + 1) + ":");
            System.out.println(availablePlayers.get(i).description());
        }
        int playerChoice = scanner.nextInt();
        while (playerChoice <= 0 || playerChoice > availablePlayers.size())
        {
            System.out.println("Illegal choice! Please choose again: ");
            playerChoice = scanner.nextInt();
        }
        this.chosenPlayerIndex = playerChoice - 1;
    }

    /**
     * This method runs the game
     */
    public void runGame() throws Exception
    {
        Iterator<GameBoard> gameBoardIterator = new GameBoardFileIterator(this.levelsFolder,
                this.chosenPlayerIndex, this.tileFactory);
        PlayerMovementConverter movementConverter = new CLIPlayerMovementConverter();
        MoveObservable moveObservable = new CLIMoveObservable();
        IMessageCallback messageCallback = new PrintMessageCallback();

        GameManager gameManager = new GameManager(movementConverter, moveObservable,
                messageCallback, gameBoardIterator);
        gameManager.runGame();
    }
}

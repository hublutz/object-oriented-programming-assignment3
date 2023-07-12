package PresentationLayer;

import BusinessLayer.AbstractGameBoardIterator;
import BusinessLayer.GameManager;
import BusinessLayer.IMessageCallback.IMessageCallback;
import BusinessLayer.PlayerMovementConverter;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.MoveOperations.MoveObservable;
import BusinessLayer.Tiles.Units.Players.Player;
import PresentationLayer.Factories.TileFactory;
import PresentationLayer.MessageCallbacks.PrintMessageCallback;
import PresentationLayer.Movements.CLIMoveObservable;
import PresentationLayer.Movements.CLIPlayerMovementConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameInitializer
{
    private String levelsFolder;
    private List<Enemy> gameEnemyList;
    private TileFactory tileFactory;
    private int chosenPlayerIndex;

    public GameInitializer(String[] args)
    {
        if (args.length < 1)
        {
            throw new IllegalArgumentException("You must provide a levels folder for the game!");
        }
        this.levelsFolder = args[0];
        this.gameEnemyList = new ArrayList<>();
        this.tileFactory = new TileFactory(this.gameEnemyList);
    }

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

    public void runGame() throws Exception
    {
        AbstractGameBoardIterator gameBoardIterator = new GameBoardFileIterator(this.levelsFolder,
                this.chosenPlayerIndex, this.tileFactory);
        PlayerMovementConverter movementConverter = new CLIPlayerMovementConverter();
        MoveObservable moveObservable = new CLIMoveObservable();
        IMessageCallback messageCallback = new PrintMessageCallback();

        GameManager gameManager = new GameManager(movementConverter, moveObservable,
                messageCallback, gameBoardIterator);
        gameManager.runGame();
    }
}

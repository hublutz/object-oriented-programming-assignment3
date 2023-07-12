package PresentationLayer;

import BusinessLayer.AbstractGameBoardIterator;
import BusinessLayer.GameBoard;
import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.WallTile;
import PresentationLayer.Factories.TileFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class GameBoardFileIterator is a GameBoard iterator that provides
 * game levels from files
 */
public class GameBoardFileIterator extends AbstractGameBoardIterator
{
    private Queue<File> files;
    private int chosenPlayerIndex;
    private TileFactory tileFactory;

    /**
     * GameBoardFileIterator constructor
     * @param folderName the folder that contains the game levels
     * @param chosenPlayerIndex the index of the player types chosen
     * @param tileFactory factory of tiles
     */
    public GameBoardFileIterator(String folderName, int chosenPlayerIndex,
                                 TileFactory tileFactory)
    {
        this.tileFactory = tileFactory;
        this.files = new LinkedList<>();
        this.chosenPlayerIndex = chosenPlayerIndex;

        File folder = new File(folderName);
        for (File file : folder.listFiles())
        {
            this.files.add(file);
        }
    }

    /**
     * Returns whether there is a next game level
     */
    @Override
    public boolean hasNext()
    {
        return !files.isEmpty();
    }

    /**
     * Returns the next GameBoard, parsed from the next level file
     */
    @Override
    public GameBoard next()
    {
        if (!this.hasNext())
        {
            throw new NoSuchElementException("No more level files to parse!");
        }

        File currentFile = this.files.remove();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(currentFile.toURI()));
            int rowLength = lines.size();
            int columnLength = lines.get(0).length();
            Tile[][] tiles = new Tile[rowLength][columnLength];
            List<Enemy> enemyList = new ArrayList<>();
            Player newPlayer;

            for (int row = 0; row < rowLength; row++)
            {
                for (int column = 0; column < columnLength; column++)
                {
                    char tile = lines.get(row).charAt(column);
                    Tile newTile;
                    switch (tile)
                    {
                        case EmptyTile.EMPTY_TILE_CHAR -> newTile = this.tileFactory.createEmptyTile(column, row);
                        case WallTile.WALL_TILE_CHAR -> newTile = this.tileFactory.createWallTile(column, row);
                        case Player.PLAYER_TILE -> {
                            this.currentPlayer = this.tileFactory.createPlayer(this.chosenPlayerIndex, column, row);
                            newPlayer = this.currentPlayer;
                            newTile = this.currentPlayer;
                        }
                        default ->
                        {
                            Enemy newEnemy = this.tileFactory.createEnemy(tile, column, row);
                            enemyList.add(newEnemy);
                            newTile = newEnemy;
                        }
                    }
                    tiles[row][column] = newTile;
                }
            }

            GameBoard gameBoard = new GameBoard(tiles);
            this.tileFactory.setCurrentGameBoard(gameBoard);
            return gameBoard;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}

package BusinessLayer;

import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {

    private List<Tile> boardTiles;

    public GameBoard(Tile[][] boardTiles){
        this.boardTiles = new ArrayList<>();

        for(Tile[] tileRow: boardTiles){
            Collections.addAll(this.boardTiles, tileRow);
        }
    }

    public Tile getTile(int x, int y){
        return boardTiles.stream().filter(tile -> tile.getX() == x && tile.getY() == y).collect(Collectors.toList()).get(0);
    }

    public void remove(Enemy enemy){
        boardTiles.remove(enemy);
        boardTiles.add(new EmptyTile(enemy.getX(),enemy.getY()));
    }

    public String toString(){
        StringBuilder ret = new StringBuilder();
        for(int y =0; y<Math.sqrt(boardTiles.size()); y++) {
            for (int x = 0; x < Math.sqrt(boardTiles.size()); x++)
                ret.append(getTile(x, y));

            ret.append("\n");
        }
        return ret.toString();
    }

    public void tick(){
        boardTiles.forEach(Tile::onGameTick);
    }

}

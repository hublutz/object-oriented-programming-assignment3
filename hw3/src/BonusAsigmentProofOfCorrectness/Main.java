package BonusAsigmentProofOfCorrectness;

import BusinessLayer.GameBoard;
import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.EnemyTiles.Monster.Monster;
import BusinessLayer.Tiles.VisitorPattern.IVisitor;

import java.util.ArrayList;

public class Main {





     public static void main(String[] args) {

         EmptyTile tile1= new EmptyTile(0,0);
         EmptyTile tile2= new EmptyTile(0,1);
         EmptyTile tile3= new EmptyTile(1,0);
         EmptyTile tile4= new EmptyTile(1,1);

         Tile[][] tileArray = new Tile[2][2];
         tileArray[0][0] = tile1;
         tileArray[0][1] = tile2;
         tileArray[1][0] = tile3;
         tileArray[1][1] = tile4;


         GameBoard board = new GameBoard(tileArray,new ArrayList<Enemy>(),null);
         System.out.println( "is the tile in 0,0 tile 1? -" + (board.getTile(0,0) == tile1));
         System.out.println( "is the tile in 0,1 tile 1? -" +(board.getTile(0,1) == tile1));
         System.out.println( "is the tile in 0,1 tile 2? -" +(board.getTile(0,1) == tile2));

         // the search and insert in TreeMap is O(log(N))
         // the deletion of an enemy is also O(log(N))
         // there is no duplicate keys in the TreeMap because we used Cantor pairing function


     }

}

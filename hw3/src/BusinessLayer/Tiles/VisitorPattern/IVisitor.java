package BusinessLayer.Tiles.VisitorPattern;

import BusinessLayer.Tiles.EmptyTile;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.WallTile;

public interface IVisitor {


    /**
     * The visit functions as part of the visitor pattern
     * */
    public void visit(EmptyTile empty);
    public void visit(WallTile wall);
    public void visit(Player player);
    public void visit(Enemy enemy);


}

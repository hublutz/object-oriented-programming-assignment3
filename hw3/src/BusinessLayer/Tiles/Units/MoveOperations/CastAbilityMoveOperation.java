package BusinessLayer.Tiles.Units.MoveOperations;

import BusinessLayer.Tiles.Point;
import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;

import java.util.List;

/**
 * MoveOperation for performing ability cast of the player
 */
public class CastAbilityMoveOperation implements MoveOperation
{
    private Player player;
    private List<Enemy> enemyList;

    /**
     * CastAbilityMoveOperation constructor
     * @param player The player of the game
     * @param enemyList The list of enemies in the game board
     */
    public CastAbilityMoveOperation(Player player, List<Enemy> enemyList)
    {
        this.player = player;
        this.enemyList = enemyList;
    }

    /**
     * This method calls the ability cast of the player
     * @param position The position given
     */
    @Override
    public void move(Point position)
    {
        player.castAbility(enemyList);
    }
}

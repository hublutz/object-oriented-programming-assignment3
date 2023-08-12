package BusinessLayer.Tiles.Units.Movement.MoveOperations;

import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;
import BusinessLayer.Tiles.Units.Players.Player;
import BusinessLayer.Tiles.Units.UnitTile;

import java.util.Collection;
import java.util.List;

/**
 * MoveOperation for performing ability cast of the player
 */
public class CastAbilityMoveOperation implements MoveOperation
{
    private Player player;
    private Collection<Enemy> enemyList;

    /**
     * CastAbilityMoveOperation constructor
     * @param player The player of the game
     * @param enemyList The list of enemies in the game board
     */
    public CastAbilityMoveOperation(Player player, Collection<Enemy> enemyList)
    {
        this.player = player;
        this.enemyList = enemyList;
    }

    /**
     * This method calls the ability cast of the player
     * @param unit The unit given
     */
    @Override
    public void move(UnitTile unit)
    {
        player.castAbility(enemyList);
    }
}

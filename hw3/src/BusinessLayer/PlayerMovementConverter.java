package BusinessLayer;

import BusinessLayer.Tiles.Units.Players.MovementFactory;

public interface PlayerMovementConverter
{
    /**
     * This method generates a new PlayerMovement constant
     */
    MovementFactory.PlayerMovements generatePlayerMovement();
}

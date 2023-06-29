package PresentationLayer.Movements;

import BusinessLayer.PlayerMovementConverter;
import BusinessLayer.Tiles.Units.Players.MovementFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CLIPlayerMovementConverter implements PlayerMovementConverter
{
    private Map<Character, MovementFactory.PlayerMovements> movementsTransformer;

    /**
     * CLIPlayerMovementConverter constructor
     */
    public CLIPlayerMovementConverter()
    {
        this.movementsTransformer = new HashMap<>();
        this.movementsTransformer.put('w', MovementFactory.PlayerMovements.MOVE_UP);
        this.movementsTransformer.put('s', MovementFactory.PlayerMovements.MOVE_DOWN);
        this.movementsTransformer.put('a', MovementFactory.PlayerMovements.MOVE_LEFT);
        this.movementsTransformer.put('d', MovementFactory.PlayerMovements.MOVE_RIGHT);
        this.movementsTransformer.put('e', MovementFactory.PlayerMovements.CAST_ABILITY);
        this.movementsTransformer.put('q', MovementFactory.PlayerMovements.NO_MOVEMENT);
    }

    /**
     * Generates a player movement according to keyboard character input
     * @return The player movement corresponding to the player choice
     */
    @Override
    public MovementFactory.PlayerMovements generatePlayerMovement()
    {
        char movement;
        System.out.println("Player, enter movement: ");
        Scanner scanner = new Scanner(System.in);
        movement = scanner.next().charAt(0);
        while (! this.movementsTransformer.containsKey(movement))
        {
            System.out.println("Invalid movement requested! Try again:");
            movement = scanner.next().charAt(0);
        }

        return this.movementsTransformer.get(movement);
    }
}

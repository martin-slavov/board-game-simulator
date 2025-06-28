package game.board.squares;

import game.players.Player;
import game.traps.Trap;
import game.traps.TrapInitializer; // Import the new utility class

import java.util.List;

/**
 * Represents the "Trap" square on the board, where a player can choose to set a trap
 * for other players at a cost.
 */
public class TrapSquare extends Square {

    private final List<Trap> availableTraps;

    // TODO: Need a way to track active traps on the board.

    /**
     * Constructs a TrapSquare.
     * The square automatically initializes the list of available traps.
     *
     * @param position The position of the square on the board.
     */
    public TrapSquare(int position) {
        super("Trap", SquareType.TRAP, position);
        this.availableTraps = TrapInitializer.initializeTraps();
    }

    /**
     * Defines the action to be performed when a player lands on this square.
     * The player can choose to set a trap from the available list for an investment cost.
     *
     * @param player The player who landed on the square.
     */
    @Override
    public void performAction(Player player) {
        System.out.println(player.getName() + " landed on a " + getName() + " square.");

        // TODO: Implement logic for setting a trap.
    }
}
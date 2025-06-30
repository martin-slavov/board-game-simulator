package game.board.squares;

import game.Game;
import game.players.Player;

/**
 * Represents the "Steal" square on the board.
 * Landing on this square gives a player the opportunity to steal a portion of an investment from another player.
 */
public class StealSquare extends Square {

    // TODO: Maybe moving this to a constants class for better maintainability.

    /**
     * Constructs a StealSquare.
     */
    public StealSquare() {
        super(SquareType.STEAL);
    }

    /**
     * Defines the action to be performed when a player lands on this square.
     * The player can attempt to steal an investment from another player.
     *
     * @param player The player who landed on the square.
     */
    @Override
    public void performAction(Player player, Game game) {
        System.out.println(player.getName() + " landed on a " + getType() + " square.");

        if (player.isCanNotUseSteelSquare()) {
            System.out.println("Този път няма а може да се възползвате от тази опция");
            player.setCanNotUseSteelSquare(false);
        }
        // TODO: Implement the logic for stealing an investment.
    }
}
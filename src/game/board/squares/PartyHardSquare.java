package game.board.squares;

import game.players.Player;

/**
 * Represents the "Party Hard" square on the board.
 * Passing through this square costs a player a fixed amount of money.
 */
public class PartyHardSquare extends Square {

    private static final double PARTY_COST = 25.0;

    /**
     * Constructs a PartyHardSquare. It is always named "Party Hard" and has the PARTY_HARD type.
     */
    public PartyHardSquare() {
        super(SquareType.PARTY_HARD);
    }

    /**
     * Defines the action to be performed when a player lands on this square or passes through it.
     * The player pays a fixed cost.
     *
     * @param player The player who landed on the square.
     */
    @Override
    public void performAction(Player player) {
        System.out.println(player.getName() + " has passed through the " + getType() + " square.");
        player.deductMoney(PARTY_COST);

        System.out.println(player.getName() + " paid " + PARTY_COST + " for the party. New balance: " + player.getBalance());
        // TODO: Add logic to check for bankruptcy if balance drops below zero.
    }
}
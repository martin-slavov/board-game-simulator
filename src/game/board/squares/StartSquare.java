package game.board.squares;

import game.Game;
import game.players.Player;

public class StartSquare extends Square {
    private static final double BONUS_AMOUNT = 200.0;

    /**
     * Constructs the StartSquare.
     * It is always named "Start" and has the START type.
     */

    public StartSquare() {
        super(SquareType.START);
    }

    /**
     * Defines the action to be performed when a player lands on this square.
     * The player receives a bonus amount of money.
     *
     * @param player The player who lands on the square.
     */
    @Override
    public void performAction(Player player, Game game) {
        System.out.println(player.getName() + " landed on " + getType());
        if (player.isInNeutralState()) return;

        player.addMoney(BONUS_AMOUNT);
        System.out.println(player.getName() + " received " + BONUS_AMOUNT + " money. " +
                "New balance: " + player.getBalance());
    }
}

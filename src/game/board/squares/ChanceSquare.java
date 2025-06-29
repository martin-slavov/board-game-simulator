package game.board.squares;

import game.dice.Dice;
import game.players.Player;

/**
 * Represents the "Chance" square on the board.
 * Landing on this square results in a random gain or loss of money based on a dice roll.
 */
public class ChanceSquare extends Square {
    private final Dice dice;

    /**
     * Constructs a ChanceSquare.
     */
    public ChanceSquare() {
        super(SquareType.CHANCE);
        this.dice = new Dice();
    }

    /**
     * Defines the action to be performed when a player lands on this square.
     * A 100-sided dice is rolled to determine a random monetary gain or loss.
     *
     * @param player The player who landed on the square.
     */
    @Override
    public void performAction(Player player) {
        System.out.println(player.getName() + " landed on a " + getType() + " square.");

        int outcomeRoll = dice.rollTenSidedDice();
        int chanceRoll = dice.rollHundredSidedDice();

        // TODO: Implement the logic based on the two dice rolls.
        // if (outcomeRoll % 2 == 0) {
        //     // Positive outcome (even roll)
        //     //   ...
        //     // player.addMoney(amount);
        //     // System.out.println("... received " + amount);
        // } else {
        //     // Negative outcome (odd roll)
        //     //   ...
        //     // player.deductMoney(amount);
        //     // System.out.println("... loses " + amount);
        // }
    }
}

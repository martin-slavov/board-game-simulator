package game.board.squares;

import game.Game;
import game.dice.Dice;
import game.players.Player;

/**
 * Represents the "Chance" square on the board.
 * Landing on this square results in a random gain or loss of money based on a die roll.
 */
public class ChanceSquare extends Square {

    /**
     * Constants for chance roll outcomes.
     */
    private static final int TIERS_1_39_AMOUNT = 50;
    private static final int TIERS_40_60_AMOUNT = 100;
    private static final int TIERS_65_79_AMOUNT = 150;
    private static final int TIERS_80_94_AMOUNT = 200;
    private static final int TIERS_95_100_AMOUNT = 250;

    /**
     * Constructs a ChanceSquare.
     */
    public ChanceSquare() {
        super(SquareType.CHANCE);
    }

    /**
     * Defines the action to be performed when a player lands on this square.
     * A 100-sided dice is rolled to determine a random monetary gain or loss.
     * TODO: Complete the Javadoc.
     *
     * @param player The player who landed on the square.
     * @param game The main Game instance (not used directly in this method, but required by the parent class signature).
     */
    @Override
    public void performAction(Player player, Game game) {
        System.out.println(player.getName() + " landed on a " + getType() + " square.");

        // Determine if the outcome will be good or bad.
        int outcomeRoll = determineOutcomeRoll(player);

        // Roll the 100-sided die to get the amount of money.
        int chanceRoll = Dice.rollHundredSidedDice();

        // Apply the outcome based on the roll.
        if (outcomeRoll % 2 == 0) {
            // Even roll -> Positive outcome.
            positiveOutcome(player, chanceRoll);
        } else {
            // Odd roll -> Negative outcome.
            negativeOutcome(player, chanceRoll);
        }
    }

    /**
     * Determines the outcome roll for the chance event, considering any special effects on the player.
     *
     * @param player The player for whom to determine the outcome roll.
     * @return An integer representing the outcome roll (even for good luck, odd for bad luck).
     */
    private static int determineOutcomeRoll(Player player) {
        // Check if the player is under a "bad luck" effect from a trap.
        if (player.isUnderBadLuckEffect()) {

            // If so, remove the effect and force a negative outcome roll.
            player.setUnderBadLuckEffect(false);
            System.out.println("The 'Gambling Boss' trap effect is active! You only receive bad luck.");

            return 1; // Return an odd number to force a negative outcome.

        } else {
            // If no effect is active, roll a 10-sided die to determine the outcome.
            return Dice.rollTenSidedDice();
        }
    }

    /**
     * Applies a positive monetary outcome to the player based on the chance roll.
     *
     * @param player The player who gains money.
     * @param chanceRoll The result of the 100-sided die roll.
     */
    private void positiveOutcome(Player player, int chanceRoll) {
        // Calculate the amount to be added.
        int amount = getAmountByChanceRoll(chanceRoll);

        // Add the money and print the result.
        player.addMoney(amount);
        System.out.println(player.getName() + " received " + amount);
    }

    /**
     * Applies a negative monetary outcome to the player based on the chance roll.
     *
     * @param player The player who loses money.
     * @param chanceRoll The result of the 100-sided die roll.
     */
    private static void negativeOutcome(Player player, int chanceRoll) {
        // Negative outcome (odd roll)
        int amount = getAmountByChanceRoll(chanceRoll);

        // Deduct the money and print the result.
        player.deductMoney(amount);
        System.out.println(player.getName() + " loses " + amount);
    }

    /**
     * Calculates the monetary amount based on the result of a 100-sided chance roll.
     * This method centralizes the logic to avoid code duplication.
     *
     * @param chanceRoll The roll from the 100-sided die.
     * @return The corresponding monetary amount.
     */
    private static int getAmountByChanceRoll(int chanceRoll) {
        // Use if-else if structure to determine the amount based on roll tiers.
        if (1 <= chanceRoll && chanceRoll <= 39) {
            return TIERS_1_39_AMOUNT;
        } else if (40 <= chanceRoll && chanceRoll <= 60) {
            return TIERS_40_60_AMOUNT;
        } else if (65 <= chanceRoll && chanceRoll <= 79) {
            return TIERS_65_79_AMOUNT;
        } else if (80 <= chanceRoll && chanceRoll <= 94) {
            return TIERS_80_94_AMOUNT;
        } else {
            // This covers the range from 95 to 100.
            return TIERS_95_100_AMOUNT;
        }
    }
}

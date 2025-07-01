package game.dice;

import java.util.Random;

/**
 * The Dice class simulates rolling a dice with a variable number of sides.
 * It provides methods to generate random number for different game mechanics.
 */
public class Dice {
    private static Random random = new Random();

//    /**
//     * Constructs a new Dice object.
//     * Initializes the random number generator.
//     */
//    public Dice() {
//        random = new Random();
//    }


    /**
     * Rolls a 2-sided dice for player movement.
     * The result is a random integer between 1 and 2, inclusive.
     * @return A random integer (1 or 2).
     */
    public static int rollTwoSidedDice() {
        return random.nextInt(1,3);
    }

    /**
     * Rolls a 10-sided dice.
     * This is used to determine if a Chance card is positive or negative, or for trap-related logic.
     * The result is a random integer between 1 and 10, inclusive.
     * @return A random integer (1-10).
     */
    public static int rollTenSidedDice() {
        return random.nextInt(1,11);
    }

    /**
     * Rolls a 100-sided dice.
     * This is used to select a specific Chance card from an interval.
     * The result is a random integer between 1 and 100, inclusive.
     * @return A random integer (1-100).
     */
    public static int rollHundredSidedDice() {
        return random.nextInt(1,101);
    }
}

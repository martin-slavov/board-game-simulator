package game.traps;

import game.players.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class responsible for initializing and providing a list of all predefined trap types
 * from the game's rules.
 */
public class TrapInitializer {

    /**
     * Creates and returns a list of all available Trap objects based on the game's specification.
     *
     * @return A list containing all predefined Trap objects.
     */
    public static List<Trap> initializeTraps(Player player) {
        List<Trap> traps = new ArrayList<>();

        traps.add(new Trap("Tax Audit", 10, "You lose 10 percent of all your income.", player));
        traps.add(new Trap("Cat Divorce", 20, "The player rolls a 10-sided die; if the value is 2 or 8, they do not gain or lose profit for 3 turns.", player));
        traps.add(new Trap("Propaganda", 100, "You cannot set more traps within the current cycle.", player));
        traps.add(new Trap("Seeing the Light", 50, "Upon landing on a Steal square, the player loses the right to execute an evil plan.", player));
        traps.add(new Trap("Gambling Boss", 100, "The next Chance square brings you only negative consequences.", player));

        return traps;
    }
}
package game.board.squares;

import game.Game;
import game.players.Player;

/**
 * Represents the "Steal" square on the board.
 * Landing on this square gives a player the opportunity to steal a portion of an investment from another player.
 */
public class StealSquare extends Square {
    /**
     * Constants for user input choices
     */
    private static final String YES_CHOICE = "Y";
    private static final String NO_CHOICE = "N";
    private static final String CHANCE_SQUARE_CHOICE = "C";
    private static final String TRAP_SQUARE_CHOICE = "T";
    private static final String STEAL_SQUARE_CHOICE = "S";

    /**
     * Constructs a StealSquare.
     */
    public StealSquare() {
        super(SquareType.STEAL);
    }

    /**
     * Defines the action to be performed when a player lands on this square.
     * The player can choose to set a steal plan, unless they are under a penalty.
     *
     * @param player The player who landed on the square.
     * @param game The main Game instance, providing access to shared resources like the scanner.
     */
    @Override
    public void performAction(Player player, Game game) {
        System.out.println(player.getName() + " landed on a " + getType() + " square.");

        // First, check if the player is under a penalty that prevents them from using this square.
        if (player.isCanNotUseSteelSquare()) {
            System.out.println("You cannot use this option this turn due to an active penalty.");
            // The penalty is a one-time effect, so it's removed after checking.
            player.setCanNotUseSteelSquare(false);
            return;
        }

        // Check if the player already has an active steal plan.
        if (player.hasStealPlan()) {
            System.out.println("You already have an active steal plan.");
        } else {
            // If the player has no plan, ask if they want to set one.
            askToSetStealPlan(player, game);
        }
    }

    /**
     * Prompts the player to decide whether they want to set a new steal plan.
     * This method handles user input and delegates to the next step.
     *
     * @param player The player who is setting the plan.
     * @param game The Game instance for user input.
     */
    private void askToSetStealPlan(Player player, Game game) {
        System.out.println("""
                \nDo you want to set a steal plan?
                (Y) Yes
                (N) No
                Your choice:"""
        );
        String choice;
        if (player.isBot()) {
            choice = botWantToSteal(game);
        } else {
            choice = game.getScanner().nextLine().trim().toUpperCase();
        }

        switch (choice) {
            case YES_CHOICE -> askForTargetSquare(player, game);
            case NO_CHOICE -> System.out.println("You decided not to set a steal plan.");
            default -> System.out.println("Invalid choice.");
        }
    }

    /**
     * Prompts the player to choose which square type will trigger their steal plan.
     *
     * @param player The player who is setting the plan.
     * @param game The Game instance for user input.
     */
    private void askForTargetSquare(Player player, Game game) {
        System.out.println("""
                \nWhich square type should trigger your steal of 100 money?
                (C) Chance
                (T) Trap
                (S) Steal
                Your choice:"""
        );
        String choice;
        if (player.isBot()) {
            choice = squareTypeToSteal(game);
        } else {
            choice = game.getScanner().nextLine().trim().toUpperCase();
        }

        SquareType targetSquareType = null;
        switch (choice) {
            case CHANCE_SQUARE_CHOICE -> targetSquareType = SquareType.CHANCE;
            case TRAP_SQUARE_CHOICE -> targetSquareType = SquareType.TRAP;
            case STEAL_SQUARE_CHOICE -> targetSquareType = SquareType.STEAL;
            default -> System.out.println("Invalid choice.");
        }

        // If a valid square type was chosen, set the steal plan for the player.
        if (targetSquareType != null) {
            player.setStealPlan(targetSquareType);
            System.out.println("Your steal plan is now active for " + targetSquareType + " squares.");
        }
    }

    private String botWantToSteal(Game game) {
        return switch (game.getRandom().nextInt(2)) {
            case 0 -> "Y";
            case 1 -> "N";
            default -> throw new IllegalArgumentException("Invalid choice");
        };
    }

    private String squareTypeToSteal(Game game) {
        return switch (game.getRandom().nextInt(3)) {
            case 0 -> "C";
            case 1 -> "T";
            case 2 -> "S";
            default -> throw new IllegalArgumentException("Invalid choice");
        };
    }
}
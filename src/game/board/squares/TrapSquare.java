package game.board.squares;

import game.Game;
import game.dice.Dice;
import game.players.Player;
import game.traps.Trap;
import game.traps.TrapInitializer;

/**
 * Represents the "Trap" square on the board, where a player can choose to set a trap
 * for other players at a cost.
 */
public class TrapSquare extends Square {

    /**
     * Constants for trap choices to avoid "magic strings"
     */
    private static final String TAX_AUDIT = "1";
    private static final String CAT_DIVORCE = "2";
    private static final String PROPAGANDA = "3";
    private static final String SEEING_THE_LIGHT = "4";
    private static final String GAMBLING_BOSS = "5";
    private static final String NO_THANKS = "N";

    private Trap activeTrap; // The Trap object that is currently set on this square.
    private Player owner; // The player who set the trap on this square.

    /**
     * Constructs a TrapSquare.
     * The square automatically initializes the list of available traps.
     */
    public TrapSquare() {
        super(SquareType.TRAP);
        owner = null;
    }

    /**
     * Defines the action to be performed when a player lands on this square.
     * The player can choose to set a trap from the available list for an investment cost.
     *
     * @param player The player who landed on the square.
     * @param game   The main Game instance, providing access to shared resources like the scanner and random number generator.
     */
    @Override
    public void performAction(Player player, Game game) {

        if (player.getStealPlan() == SquareType.TRAP) {
            System.out.println("Success! " + player.getName() + " gained 100 money because of your steal plan!");
            player.addMoney(100);
        }

        System.out.println(player.getName() + " landed on a " + getType() + " square.");

        if (owner == null) {
            // Check if the player has enough money to even consider setting a trap.
            if (player.getBalance() <= 0) {
                System.out.println(player.getName() + " does not have enough money to set a trap.");
                return;
            }

            handleSettingTrap(player, game);
        } else {
            handleTrapActivation(player, game);
        }
    }

    /**
     * Handles the logic for a player choosing to set a new trap on this square.
     * This method presents a menu of traps and processes the player's choice.
     *
     * @param player The player attempting to set a trap.
     * @param game   The Game instance for user input and randomness.
     */
    private void handleSettingTrap(Player player, Game game) {
        // Check for a penalty effect from another trap.
        if (player.isCanNotPlaceTrap()) {
            System.out.println("You cannot set a trap due to an effect from another trap.");
            // The penalty is consumed after one use.
            player.setCanNotPlaceTrap(false);
        }

        printMenu(player);

        // Read the player's choice from console or a bot's logic.
        String choice;
        if (player.isBot()) {
            // Bot logic: choose a random option from 1 to 5.
            choice = String.valueOf(game.getRandom().nextInt(5) + 1);
            System.out.println(choice);
        } else {
            // Human player input.
            choice = game.getScanner().nextLine().trim().toUpperCase();
        }

        // Use a switch statement to create the chosen trap object.
        Trap chosenTrap = null;
        switch (choice) {
            case TAX_AUDIT:
                if (player.getBalance() >= 10)
                    chosenTrap = TrapInitializer.initializeTraps(player).get(0);
                break;
            case CAT_DIVORCE:
                if (player.getBalance() >= 20)
                    chosenTrap = TrapInitializer.initializeTraps(player).get(1);
                break;
            case PROPAGANDA:
                if (player.getBalance() >= 100)
                    chosenTrap = TrapInitializer.initializeTraps(player).get(2);
                break;
            case SEEING_THE_LIGHT:
                if (player.getBalance() >= 50)
                    chosenTrap = TrapInitializer.initializeTraps(player).get(3);
                break;
            case GAMBLING_BOSS:
                if (player.getBalance() >= 100)
                    chosenTrap = TrapInitializer.initializeTraps(player).get(4);
                break;
            case NO_THANKS:
                System.out.println("You decided not to set a trap.");
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        // If a trap was chosen and created successfully
        if (chosenTrap != null) {
            // If the player has enough money for the chosen trap's cost.
            if (player.getBalance() >= chosenTrap.getInvestmentCost()) {

                player.deductMoney(chosenTrap.getInvestmentCost()); // Deduct the cost from the player's balance.
                activeTrap = chosenTrap; // Assign the chosen trap to the square's active trap.
                owner = player; // Assign the player as the owner of this square's trap.
                System.out.println(owner.getName() + " successfully set a trap of type: " + chosenTrap.getType());

            } else {
                // If the player does not have enough money for the chosen trap.
                System.out.println(player.getName() + " does not have enough money for this trap.");
            }
        }
    }

    /**
     * Displays the menu of available traps.
     *
     * @param player The player attempting to set a trap.
     */
    private static void printMenu(Player player) {
        System.out.println("\nDo you want to set a trap, " + player.getName() + "?");
        System.out.println("*(1) Tax Audit (10 money, 10% of profit)");
        System.out.println("*(2) Cat Divorce (20 money, dice roll 2 or 8)");
        System.out.println("*(3) Propaganda (100 money, cannot set)");
        System.out.println("*(4) Seeing the Light (50 money, Steal right)");
        System.out.println("*(5) Gambling Boss (100 money, Chance square)");
        System.out.println("*(N) No, thanks, I don't believe in evil");
        System.out.print("Your choice: ");
    }

    /**
     * Handles the logic for activating a trap that is already set on this square.
     *
     * @param player The player who landed on the square.
     * @param game   The Game instance for accessing shared resources.
     */
    private void handleTrapActivation(Player player, Game game) {
        // Check if the player landed on their own trap.
        if (player == owner) {
            System.out.println("You landed on your own trap. No effect applied.");
            return;
        }

        // Check if the player  money-related effects.
        if (player.canNotGainOrLoseMoney()) return;

        // Apply the trap's effect based on its type.
        System.out.println("You landed on an enemy trap.");
        System.out.println(activeTrap.getDescription()); // Prints what the trap does
        switch (activeTrap.getType()) {
            case "Tax Audit" -> player.deductMoney(player.getBalance() * 0.1); // -10% of the player's balance
            case "Cat Divorce" -> {
                System.out.println("Press ENTER to roll a 10-sided die");
                if (!player.isBot()) {
                    game.getScanner().nextLine();
                }

                int getRandomNumber = Dice.rollTenSidedDice();
                if (getRandomNumber == 2 || getRandomNumber == 8) {
                    player.setTurnsInNeutralState(3);
                    System.out.println("You will not gain or lose money for 3 turns");
                } else {
                    System.out.println("You got lucky! Nothing will happen.");
                }
            }
            case "Propaganda" -> {
                System.out.println("The next time you are on a TRAP square, you will not be able to set a trap");
                player.setCanNotPlaceTrap(true);
            }
            case "Seeing the Light" -> {
                System.out.println("You lose the right to execute an evil plan 1 time (Steal)");
                player.setCanNotUseSteelSquare(true);
            }
            case "Gambling Boss" -> {
                System.out.println("The next time you land on a CHANCE square, you will have bad luck");
                player.setUnderBadLuckEffect(true);
            }
            default -> throw new IllegalStateException("Unexpected value: " + activeTrap.getType());
        }
    }
}
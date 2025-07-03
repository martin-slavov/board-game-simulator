package game;

import game.board.Board;
import game.board.squares.Square;
import game.dice.Dice;
import game.investments.Investment;
import game.players.Player;

import java.util.*;

public class Game {
    // --- Constants ---
    public static final long START_PAUSE_MILLISECONDS = 1500; // Pause after welcome message
    public static final long TURN_PAUSE_MILLISECONDS = 700; // Pause after moving to new square

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random(); // For general random needs, not dice rolls
    private final Board board;
    private final List<Player> players = new ArrayList<>(); // To manage multiple players
    private int currentPlayerIndex = 0; // Index in the players list for current turn
    private boolean gameOver;

    /**
     * Constructs a new Game object.
     */
    public Game() {
        gameOver = false;
        board = new Board();
    }

    /**
     * Provides access to the shared Scanner instance for user input.
     *
     * @return The Scanner instance.
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Provides access to the shared Random instance for general random number generation.
     *
     * @return The Random instance.
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Starts the main game loop. Handles player setup, turn progression, and game-ending conditions.
     */
    public void startGame() {
        displayWelcomeMessage();
        setupPlayers();
        determineFirstPlayer();

        while (!gameOver) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println(board);

            displayPlayerStats(currentPlayer);
            handlePlayerTurn(currentPlayer);

            if (currentPlayer.getBalance() <= 0) {
                System.out.println("\n----------------------------------------");
                System.out.println(currentPlayer.getName() + " has run out of money!");
                System.out.println("GAME OVER!");

                int winnerIndex = (currentPlayerIndex + 1) % players.size(); // current player loses, the other wins
                Player winner = players.get(winnerIndex);
                if (winner != null) {
                    System.out.println("The winner is " + winner.getName() + "!");
                }
                System.out.println("----------------------------------------");
                gameOver = true;
            } else {
                // Processing current player's investments after their turn.
                processInvestments(currentPlayer);

                // Move to the next player's turn
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                // Small pause between turns for readability
                pause(TURN_PAUSE_MILLISECONDS);
            }
        }
        scanner.close();
    }

    /**
     * Displays the welcome message for the game.
     */
    private static void displayWelcomeMessage() {
        System.out.println("\nWelcome to 'Evil Geniuses'!");
        pause(START_PAUSE_MILLISECONDS);
    }

    /**
     * Sets up the human player and the bot opponent.
     */
    private void setupPlayers() {
        System.out.println("What is your name, future Evil Genius?");
        System.out.print("Name: ");
        String playerName = scanner.nextLine().trim();
        players.add(new Player(playerName, false)); // Human player

        System.out.println("Your opponent will be a bot.");
        players.add(new Player("Bot", true)); // Bot player
    }

    /**
     * Determines which player starts the game by a random dice roll.
     * Rearranges the players list so the starting player is at index 0.
     */
    private void determineFirstPlayer() {
        System.out.println("\n--- Determining First Player ---");
        Player firstPlayer;

        // Simplified for two players: roll a 2-sided dice.
        int roll = Dice.rollTwoSidedDice();
        if (roll == 1) {
            firstPlayer = players.get(0); // human is players.get(0)
        } else {
            firstPlayer = players.get(1); // bot is players.get(1)
        }

        // If the determined first player is not already at index 0, rotate the list.
        if (players.get(0) != firstPlayer) {
            Collections.swap(players, 0, 1); // Swap if bot needs to go first
        }

        System.out.println("The first player is: " + firstPlayer.getName() + "!");
        pause(START_PAUSE_MILLISECONDS);
    }

    /**
     * Displays the current stats for the given player.
     *
     * @param player The player whose stats are to be displayed.
     */
    private void displayPlayerStats(Player player) {
        System.out.println("\n===== " + player.getName() + "'s Turn =====");
        System.out.printf("Balance: %.2f%n", player.getBalance());
        System.out.printf("Current position: %d (%s)%n", (player.getCurrentPositionIndex() + 1), board.getBoard().get(player.getCurrentPositionIndex()).getType());
        System.out.println("Steal Plan: " + (player.getStealPlan() != null ? "Active (" + player.getStealPlan() + ")" : "None")); // Check if steal plan exists
        System.out.println("Investments (" + player.getInvestments().size() + "): ");
        if (player.getInvestments().isEmpty()) {
            System.out.println("\t- None");
        } else {
            for (Investment investment : player.getInvestments()) {
                System.out.printf("\t- %s: %.2f invested%n", investment.getCompany().getName(), investment.getAmount());
            }
        }
        System.out.println("Turns in Neutral State: " + player.getTurnsInNeutralState());
        System.out.println("Cannot Use Steal Square: " + player.isCanNotUseSteelSquare());
        System.out.println("Is Under Bad Luck Effect: " + player.isUnderBadLuckEffect());
        System.out.println("Cannot Place Trap: " + player.isCanNotPlaceTrap());
    }

    /**
     * Handles the actions for a single player's turn.
     *
     * @param player The player whose turn it is.
     */
    private void handlePlayerTurn(Player player) {
        if (!player.isBot()) {
            System.out.print("Press Enter to roll the dice...");
            scanner.nextLine();
        } else {
            System.out.println(player.getName() + " is rolling the dice...");
            pause(TURN_PAUSE_MILLISECONDS); // Pause for bot
        }

        player.move();
        System.out.println("(Position : " + (player.getCurrentPositionIndex() + 1) + ")");
        Square currentSquare = board.getBoard().get(player.getCurrentPositionIndex());
        // Perform action specific to the square
        currentSquare.performAction(player, this);
        pause(TURN_PAUSE_MILLISECONDS); // Small pause after square action
    }

    /**
     * Processes all active investments for a given player.
     * Calculates profits/losses and updates the player's balance.
     *
     * @param player The player whose investments are to be processed.
     */
    private void processInvestments(Player player) {
        if (player.getInvestments().isEmpty()) {
            return; // No investments, nothing to do
        }

        System.out.println("\n--- Processing Investments for " + player.getName() + " ---");
        for (Investment investment : new ArrayList<>(player.getInvestments())) {
            double investedAmount = investment.getAmount();

            double outcomeChange = investment.calculateInvestmentOutcome(this);
            player.addMoney(outcomeChange); // Deducts if outcomeChange is negative

            String outcomeMessage = outcomeChange >= 0 ? "gained" : "lost";
            System.out.printf("  Investment in %s (invested %.2f): You %s %.2f. New balance: %.2f%n",
                    investment.getCompany().getName(), investedAmount, outcomeMessage, Math.abs(outcomeChange), player.getBalance());
        }
        System.out.println("--- Investments Processed ---");
    }

    /**
     * Pauses the game execution for a specified duration.
     *
     * @param milliseconds The duration of the pause in milliseconds.
     */
    private static void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Game pause interrupted.");
        }
    }
}
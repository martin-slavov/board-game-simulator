package game.players;

import game.board.squares.SquareType;
import game.dice.Dice;
import game.investments.Investment;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a player in the game.
 * Each player has a name, a balance, a position on the board, and other attributes.
 */
public class Player {

    /**
     * The starting balance for every player.
     * Defined as a constant to ensure consistency.
     */
    public static final double INITIAL_BALANCE = 1000.0;
    public static final int BOARD_SIZE = 20;

    private final String name; // of the player
    private double balance;
    private int currentPosition;
    private boolean hasStealPlan;
    private final List<Investment> investments;
    private int trapsActivated;
    private final boolean isBot; // true for the bot, false for the human player
    private int turnsInNeutralState;
    private boolean canNotUseSteelSquare;
    private boolean isUnderBadLuckEffect;
    private boolean canNotPlaceTrap;
    private SquareType stealPlan;

    /**
     * Constructs a new Player object.
     */
    public Player(String name, boolean isBot) {
        this.name = name;
        this.balance = INITIAL_BALANCE; // All players start with a fixed amount
        this.isBot = isBot;
        this.currentPosition = 0; // All players start at position 0 (Start Square)
        this.hasStealPlan = false;
        this.investments = new ArrayList<>();
        this.trapsActivated = 0;
        this.turnsInNeutralState = 0;
        canNotUseSteelSquare = false;
        isUnderBadLuckEffect = false;
        canNotPlaceTrap = false;
    }

    public void move() {
        int steps = Dice.rollTwoSidedDice();
        int newPosition = currentPosition + steps;
        currentPosition = newPosition % 20;

        System.out.println(this.name + " се предвижи " + steps + " стъпки напред.");

//        // Сигурно ще се премести в startGame метода
//        Square currentSquare = board.getBoard().get(currentPosition);
//        currentSquare.performAction(player, game);
    }

    public SquareType getStealPlan() {
        return stealPlan;
    }

    public void setStealPlan(SquareType stealPlan) {
        this.stealPlan = stealPlan;
    }

    public boolean isCanNotPlaceTrap() {
        return canNotPlaceTrap;
    }

    public void setCanNotPlaceTrap(boolean canNotPlaceTrap) {
        this.canNotPlaceTrap = canNotPlaceTrap;
    }

    public boolean isUnderBadLuckEffect() {
        return isUnderBadLuckEffect;
    }

    public void setUnderBadLuckEffect(boolean underBadLuckEffect) {
        isUnderBadLuckEffect = underBadLuckEffect;
    }

    public boolean isCanNotUseSteelSquare() {
        return canNotUseSteelSquare;
    }

    public void setCanNotUseSteelSquare(boolean canNotUseSteelSquare) {
        this.canNotUseSteelSquare = canNotUseSteelSquare;
    }

    /**
     * Gets the player's name.
     *
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's current balance.
     *
     * @return The current balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Adds a specified amount of money to the player's balance.
     *
     * @param amount The amount to add.
     */
    public void addMoney(double amount) {
        this.balance += amount;
    }

    /**
     * Deducts a specified amount of money from the player's balance.
     * The balance can become negative.
     *
     * @param amount The amount to deduct.
     */
    public void deductMoney(double amount) {
        this.balance -= amount;
    }

    /**
     * Gets the player's current position on the board.
     *
     * @return The current board position (0-indexed).
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Updates the player's position on the board.
     * Uses the modulo operator to handle wrapping around the board.
     *
     * @param newPosition The new raw position (can be greater than board size).
     */
//    public void setPosition(int newPosition) {
//        this.currentSquare = newPosition % BOARD_SIZE;
//    }

    /**
     * Checks if the player has an active "Steal" plan.
     *
     * @return True if a steal plan is active, false otherwise.
     */
    public boolean hasStealPlan() {
        return hasStealPlan;
    }

    /**
     * Sets the status of the player's "Steal" plan.
     *
     * @param hasStealPlan True to activate a steal plan, false to deactivate.
     */
    public void setHasStealPlan(boolean hasStealPlan) {
        this.hasStealPlan = hasStealPlan;
    }

    public int getTurnsInNeutralState() {
        return turnsInNeutralState;
    }

    public boolean canNotGainOrLoseMoney() {
        if (turnsInNeutralState > 0) {
            System.out.println("Не може да печелите и губите пари още " + turnsInNeutralState-- + " пъти.");
            return false;
        }
        return true;
    }

    public void setTurnsInNeutralState(int turnsInNeutralState) {
        this.turnsInNeutralState = turnsInNeutralState;
    }

    /**
     * Checks if the player is a bot.
     *
     * @return True if the player is a bot, false if human.
     */
    public boolean isBot() {
        return isBot;
    }

    /**
     * Gets the list of investments made by the player.
     *
     * @return A list of Investment objects.
     */
    public List<Investment> getInvestments() {
        return investments;
    }

    /**
     * Adds a new investment to the player's portfolio.
     *
     * @param investment The Investment object to add.
     */
    public void addInvestment(Investment investment) {
        this.investments.add(investment);
    }

    /**
     * Increments the count of traps activated by the player.
     */
    public void incrementTrapsActivated() {
        this.trapsActivated++;
    }

    /**
     * Resets the count of activated traps to zero, for the new cycle.
     */
    public void resetTrapsActivated() {
        this.trapsActivated = 0;
    }

    /**
     * Gets the count of traps activated by the player in the current cycle.
     *
     * @return The number of activated traps.
     */
    public int getTrapsActivated() {
        return trapsActivated;
    }

    public boolean isHasStealPlan() {
        return hasStealPlan;
    }
}

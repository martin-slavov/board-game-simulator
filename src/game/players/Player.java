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
     */
    public static final double INITIAL_BALANCE = 1000.0;

    private final String name; // of the player
    private double balance;
    private int currentPositionIndex;
    private final List<Investment> investments;
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
        this.currentPositionIndex = 0; // All players start at position 0 (Start Square)
        this.investments = new ArrayList<>();
        this.turnsInNeutralState = 0;
        canNotUseSteelSquare = false;
        isUnderBadLuckEffect = false;
        canNotPlaceTrap = false;
    }

    /**
     * Moves the player forward on the board by rolling a dice.
     * The player's position wraps around if they go beyond the board size.
     */
    public void move() {
        int steps = Dice.rollTwoSidedDice();
        int newPosition = currentPositionIndex + steps;
        currentPositionIndex = newPosition % 20;

        System.out.println(this.name + " moved " + steps + " steps forward.");
    }

    /**
     * Gets the current steal plan type the player has active.
     *
     * @return The SquareType of the active steal plan, or null if no plan is active.
     */
    public SquareType getStealPlan() {
        return stealPlan;
    }

    /**
     * Sets the player's steal plan to a specific SquareType.
     *
     * @param stealPlan The SquareType for the new steal plan. Set to null to clear the plan.
     */
    public void setStealPlan(SquareType stealPlan) {
        this.stealPlan = stealPlan;
    }

    /**
     * Checks if the player is currently unable to place a trap.
     *
     * @return True if the player cannot place a trap, false otherwise.
     */
    public boolean isCanNotPlaceTrap() {
        return canNotPlaceTrap;
    }

    /**
     * Sets whether the player can place a trap.
     *
     * @param canNotPlaceTrap True to prevent the player from placing traps, false to allow.
     */
    public void setCanNotPlaceTrap(boolean canNotPlaceTrap) {
        this.canNotPlaceTrap = canNotPlaceTrap;
    }

    /**
     * Checks if the player is currently under a bad luck effect.
     *
     * @return True if the player is under a bad luck effect, false otherwise.
     */
    public boolean isUnderBadLuckEffect() {
        return isUnderBadLuckEffect;
    }

    /**
     * Sets whether the player is under a bad luck effect.
     *
     * @param underBadLuckEffect True to put the player under bad luck, false to remove the effect.
     */
    public void setUnderBadLuckEffect(boolean underBadLuckEffect) {
        isUnderBadLuckEffect = underBadLuckEffect;
    }

    /**
     * Checks if the player is currently unable to use the Steal Square's ability.
     *
     * @return True if the player cannot use the Steal Square, false otherwise.
     */
    public boolean isCanNotUseSteelSquare() {
        return canNotUseSteelSquare;
    }

    /**
     * Sets whether the player can use the Steal Square's ability.
     *
     * @param canNotUseSteelSquare True to prevent the player from using the Steal Square, false to allow.
     */
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
    public int getCurrentPositionIndex() {
        return currentPositionIndex;
    }

    /**
     * Gets the number of turns the player remains in a neutral state
     * (cannot gain or lose money).
     *
     * @return The number of turns remaining in neutral state.
     */
    public int getTurnsInNeutralState() {
        return turnsInNeutralState;
    }

    /**
     * Checks if the player is currently in a neutral state, meaning they cannot
     * gain or lose money. If in neutral state, decrements the remaining turns.
     *
     * @return True if the player is in a neutral state and cannot gain/lose money, false otherwise.
     */
    public boolean isInNeutralState() {
        if (turnsInNeutralState > 0) {
            System.out.println("Не може да печелите и губите пари още " + turnsInNeutralState-- + " пъти.");
            return true;
        }
        return false;
    }

    /**
     * Sets the number of turns the player will remain in a neutral state.
     *
     * @param turnsInNeutralState The number of turns to set for the neutral state.
     */
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
}
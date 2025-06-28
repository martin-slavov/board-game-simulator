package game.board.squares.traps;

import game.players.Player;

/**
 * Represents a trap that can be set by a player on a square.
 * Each trap has a name, description, penalty, and investment cost.
 */
public class Trap {
    private final String name;
    private final String description;
    private final String penalty;
    private final double investmentCost;
    private Player owner;
    private int position;

    /**
     * Constructs a Trap object with all its predefined properties.
     *
     * @param name The name of the trap (e.g., "Tax Audit").
     * @param description A brief description of the trap's effect.
     * @param penalty The described consequence for the player who lands on the trap.
     * @param investmentCost The cost to set up this trap.
     */
    public Trap(String name, String description, String penalty, double investmentCost) {
        this.name = name;
        this.description = description;
        this.penalty = penalty;
        this.investmentCost = investmentCost;
        this.owner = null; // Initially, no one owns the trap.
        this.position = -1; // Initially, not placed on the board.
    }

    // --- Getters ---

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPenalty() {
        return penalty;
    }

    public double getInvestmentCost() {
        return investmentCost;
    }

    public Player getOwner() {
        return owner;
    }

    public int getPosition() {
        return position;
    }

    // --- Setters ---

    /**
     * Sets the owner of the trap.
     * @param owner The player who sets the trap.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Sets the position on the board where the trap is placed.
     * @param position The position of the square.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Provides a string representation of the Trap object.
     * @return A formatted string with the trap's details.
     */
    @Override
    public String toString() {
        return "Trap [Name: " + name + ", Cost: " + investmentCost + ", Penalty: " + penalty + ", " +
                "Owner: " + (owner != null ? owner.getName() : "None") + ", " +
                "Position: " + position + "]";
    }
}

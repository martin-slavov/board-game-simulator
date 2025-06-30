package game.traps;

import game.players.Player;

/**
 * Represents a trap that can be set by a player on a square.
 * Each trap has a name, description, penalty, and investment cost.
 */
public class Trap {
    private String type;
    private int investmentCost;
    private String description;
    private Player owner;
    private boolean isActive;


    /**
     * Constructs a Trap object with all its predefined properties.
     * TODO: Write what the parameters do.
     */
    public Trap(String type, int investmentCost, String description, Player owner) {
        this.type = type;
        this.investmentCost = investmentCost;
        this.description = description;
        this.owner = owner;
        this.isActive = true;
    }

    // --- Getters ---


    public String getType() {
        return type;
    }

    public int getInvestmentCost() {
        return investmentCost;
    }

    public String getDescription() {
        return description;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * Provides a string representation of the Trap object.
     *
     * @return A formatted string with the trap's details.
     */
    @Override
    public String toString() {
        return "Trap{" +
                "type='" + type + '\'' +
                ", investmentCost=" + investmentCost +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", isActive=" + isActive +
                '}';
    }
}

package game.board.squares;

import game.Game;
import game.players.Player;

/**
 * An abstract base class representing a square on the game board.
 * All specific types of squares (Trap, Invest,...) inherit this class
 */
public abstract class Square {
    private final SquareType type;

    /**
     * Constructs a new Square object.
     * @param type The type of the square, defined by SquareType enum.
     */
    public Square(SquareType type) {
        this.type = type;
    }

    /**
     * An abstract method that defines the action to be performed when a player lands on this square.
     * Each subclass must provide its own implementation of this method
     * @param player The player who lands on the square.
     */
    public abstract void performAction(Player player, Game game);

    /**
     * Gets the type of the square
     * @return The square's type.
     */
    public SquareType getType() {
        return type;
    }


    /**
     * Provides a string representation of the Square object.
     * @return A formatted string with the square's name, type and position.
     */
    @Override
    public String toString() {
        return "[Тип: %s]".formatted(type);
    }
}

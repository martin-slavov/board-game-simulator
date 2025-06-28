package game.board.squares;

import game.players.Player;

/**
 * An abstract base class representing a square on the game board.
 * All specific types of squares (Trap, Invest,...) inherit this class
 */
public abstract class Square {
    private final String name;
    private final SquareType type;
    private final int position;


    /**
     * Constructs a new Square object.
     * @param name The display name of the square.
     * @param type The type of the square, defined by SquareType enum.
     * @param position The fixed position of the square on the board (indexed from 0).
     */
    public Square(String name, SquareType type, int position) {
        this.name = name;
        this.type = type;
        this.position = position;
    }

    /**
     * An abstract method that defines the action to be performed when a player lands on this square.
     * Each subclass must provide its own implementation of this method
     * @param player The player who lands on the square.
     */
    public abstract void performAction(Player player);

    /**
     * Gets the name of the square
     * @return The square's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the square
     * @return The square's type.
     */
    public SquareType getType() {
        return type;
    }

    /**
     * Gets the position of the square
     * @return The square's position.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Provides a string representation of the Square object.
     * @return A formatted string with the square's name, type and position.
     */
    @Override
    public String toString() {
        return "[Име: %s, Позиция: %d, Тип: %s]".formatted(name, position, type);
    }
}

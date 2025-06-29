package game.board;

import game.board.squares.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the game board, which is a collection of squares.
 * It is responsible for initializing the board and managing player movement.
 */
public class Board {
    public static final int BOARD_SIZE = 20;
    private List<Square> board = new ArrayList<>(BOARD_SIZE);

    private StartSquare start = new StartSquare();

    private TrapSquare trap1 = new TrapSquare();
    private TrapSquare trap2 = new TrapSquare();
    private TrapSquare trap3 = new TrapSquare();
    private TrapSquare trap4 = new TrapSquare();
    private TrapSquare trap5 = new TrapSquare();
    private TrapSquare trap6 = new TrapSquare();
    private TrapSquare trap7 = new TrapSquare();

    private InvestSquare invest1 = new InvestSquare();
    private InvestSquare invest2 = new InvestSquare();
    private InvestSquare invest3 = new InvestSquare();

    private PartyHardSquare partyHard1 = new PartyHardSquare();
    private PartyHardSquare partyHard2 = new PartyHardSquare();
    private PartyHardSquare partyHard3 = new PartyHardSquare();

    private ChanceSquare chance1 = new ChanceSquare();
    private ChanceSquare chance2 = new ChanceSquare();
    private ChanceSquare chance3 = new ChanceSquare();

    private StealSquare steal1 = new StealSquare();
    private StealSquare steal2 = new StealSquare();
    private StealSquare steal3 = new StealSquare();

    /**
     * Constructs a Board object and initializes all the squares.
     */
    public Board() {
        initializeBoard();
        Collections.shuffle(board); // Shuffling the list
    }

    /**
     * Initializes the board by creating all the different types of squares and adding them to the list.
     * This method defines the layout of the game board.
     */
    private void initializeBoard() {
        board.add(trap1);
        board.add(trap2);
        board.add(trap3);
        board.add(trap4);
        board.add(trap5);
        board.add(trap6);
        board.add(trap7);

        board.add(invest1);
        board.add(invest2);
        board.add(invest3);

        board.add(partyHard1);
        board.add(partyHard2);
        board.add(partyHard3);

        board.add(chance1);
        board.add(chance2);
        board.add(chance3);

        board.add(steal1);
        board.add(steal2);
        board.add(steal3);
    }

    /**
     * Provides a string representation of the board's layout.
     *
     * @return A formatted string listing all squares on the board.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Game Board Layout:\n");
        for (Square square : board) {
            sb.append(square).append("\n");
        }
        return sb.toString();
    }

    /**
     * Gets the list of the square
     * @return The square's type.
     */
    public List<Square> getBoard() {
        return board;
    }
}
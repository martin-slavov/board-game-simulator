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
    private List<Square> squares = new ArrayList<>(BOARD_SIZE);

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
        Collections.shuffle(squares); // Shuffling the list
    }

    /**
     * Initializes the board by creating all the different types of squares and adding them to the list.
     * This method defines the layout of the game board.
     */
    private void initializeBoard() {
        squares.add(trap1);
        squares.add(trap2);
        squares.add(trap3);
        squares.add(trap4);
        squares.add(trap5);
        squares.add(trap6);
        squares.add(trap7);

        squares.add(invest1);
        squares.add(invest2);
        squares.add(invest3);

        squares.add(partyHard1);
        squares.add(partyHard2);
        squares.add(partyHard3);

        squares.add(chance1);
        squares.add(chance2);
        squares.add(chance3);

        squares.add(steal1);
        squares.add(steal2);
        squares.add(steal3);
    }

    /**
     * Provides a string representation of the board's layout.
     *
     * @return A formatted string listing all squares on the board.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Game Board Layout:\n");
        for (Square square : squares) {
            sb.append(square).append("\n");
        }
        return sb.toString();
    }
}
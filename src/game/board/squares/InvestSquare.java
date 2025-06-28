package game.board.squares;

import game.investments.InvestmentManager;
import game.players.Player;

public class InvestSquare extends Square {
    private InvestmentManager investmentManager;

    // TODO: How to pass the InvestmentManager instance to this square?

    /**
     * Constructs an InvestSquare.
     *
     * @param position          The position of the square on the board.
     * @param investmentManager The manager responsible for investment logic.
     */
    public InvestSquare(int position, InvestmentManager investmentManager) {
        super("Investment Square", SquareType.INVEST, position);

        this.investmentManager = investmentManager;
    }

    /**
     * Defines the action to be performed when a player lands on this square.
     * The player is presented with a random company and can choose to invest.
     *
     * @param player The player who landed on the square.
     */
    @Override
    public void performAction(Player player) {
        System.out.println(player.getName() + " landed on " + getName() + " square.");

        // TODO: Implement the logic to handle player investment.
    }
}

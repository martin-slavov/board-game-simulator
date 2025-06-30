package game.board.squares;

import game.Game;
import game.investments.InvestmentManager;
import game.players.Player;

/**
 * Represents the "Invest" square on the board.
 * When a player lands on this square, they get an opportunity to invest in a company.
 */
public class InvestSquare extends Square {
    private InvestmentManager investmentManager;

    // TODO: How to pass the InvestmentManager instance to this square?

    /**
     * Constructs an InvestSquare.
     */
    public InvestSquare() {
        super(SquareType.INVEST);
        this.investmentManager = new InvestmentManager();
    }

    /**
     * Defines the action to be performed when a player lands on this square.
     * The player is presented with a random company and can choose to invest.
     *
     * @param player The player who landed on the square.
     */
    @Override
    public void performAction(Player player, Game game) {
        System.out.println(player.getName() + " landed on " + getType() + " square.");

        // TODO: Implement the logic to handle player investment.
    }
}

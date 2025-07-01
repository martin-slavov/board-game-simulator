package game.board.squares;

import game.Game;
import game.investments.Company;
import game.investments.Investment;
import game.investments.InvestmentManager;
import game.players.Player;

import java.util.List;

/**
 * Represents the "Invest" square on the board.
 * When a player lands on this square, they get an opportunity to invest in a company.
 */
public class InvestSquare extends Square {
    /**
     * Constants for user choices
     */
    private static final String CHOICE_COMPANY_ONE = "1";
    private static final String CHOICE_COMPANY_TWO = "2";
    private static final String CHOICE_NO_INVESTMENT = "N";

    private final InvestmentManager investmentManager;

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
     * @param game   The main Game instance, providing access to shared resources like the scanner.
     */
    @Override
    public void performAction(Player player, Game game) {
        System.out.println(player.getName() + " landed on " + getType() + " square.");

        // Ensure the player has enough money to even consider investing
        if (player.getBalance() <= 0) {
            System.out.println(player.getName() + " does not have enough money to invest.");
            return;
        }

        List<Company> optionalCompanies = investmentManager.getTwoRandomCompanies();
        Company comp1 = optionalCompanies.get(0);
        Company comp2 = optionalCompanies.get(1);

        boolean flag = false;
        while (!flag) {
            displayInvestmentOptions(comp1, comp2);
            String choice;
            if (player.isBot()) {
                choice = getBotInvestmentDecision(game);
            } else {
                choice = game.getScanner().nextLine().trim().toUpperCase();
            }

            switch (choice) {
                case CHOICE_COMPANY_ONE -> processInvestmentChoice(player, game, comp1);
                case CHOICE_COMPANY_TWO -> processInvestmentChoice(player, game, comp2);
                case CHOICE_NO_INVESTMENT -> flag = true;
                default -> throw new IllegalArgumentException("Invalid choice");
            }
        }
        System.out.println("Край на инвистирането");
    }

    /**
     * Displays the investment options to the player.
     *
     * @param comp1 The first company option.
     * @param comp2 The second company option.
     */
    private static void displayInvestmentOptions(Company comp1, Company comp2) {
        System.out.printf("""
                        Инвестирайте разумно и изберете компания:
                        (1): %s | min : %f | risk/reward : %f
                        (2): %s | min : %f | risk/reward : %f
                        (N): Не ми се инвестира!
                        Your choice:%n""", comp1.getName(), comp1.getMinInvestment(), comp1.getReturnCoefficient(),
                comp2.getName(), comp2.getMinInvestment(), comp2.getReturnCoefficient());
    }

    /**
     * Processes the player's choice to invest in a specific company.
     * Prompts for the investment amount and handles the investment.
     *
     * @param player  The player making the investment.
     * @param game    The Game instance for user input.
     * @param company The chosen company for investment.
     */
    private void processInvestmentChoice(Player player, Game game, Company company) {
        boolean amountChosen = false;
        while (!amountChosen) {
            System.out.printf("""
                    \nYou chose to invest in "%s". Please enter the amount you wish to invest,
                    or press 'N' to return to the previous menu:
                    Current balance: %.2f
                    Your amount: %n""", company.getName(), player.getBalance());

            String amountChoice;
            if (player.isBot()) {
                amountChoice = String.valueOf(botInvestmentAmount(player, game, company));
            } else {
                amountChoice = game.getScanner().nextLine().trim().toUpperCase();
            }

            if (amountChoice.equals(CHOICE_NO_INVESTMENT)) {
                System.out.println("Returning to main investment menu.");
                amountChosen = true; // Exit this inner loop, but `performAction` loop continues if needed.
            } else {
                try {
                    double amountToInvest = Double.parseDouble(amountChoice);

                    // Basic validation: check if amount is positive and player has enough money
                    if (amountToInvest <= 0) {
                        System.out.println("Investment amount must be positive. Please try again.");
                    } else if (player.getBalance() < amountToInvest) {
                        System.out.println("You do not have enough money for this investment. Your balance: " + player.getBalance());
                    } else if (amountToInvest < company.getMinInvestment()) {
                        System.out.printf("The minimum investment for %s is %.0f. Please enter a higher amount.%n", company.getName(), company.getMinInvestment());
                    } else {
                        // All checks passed, create and add the investment
                        player.deductMoney(amountToInvest); // Deduct before adding investment
                        player.addInvestment(new Investment(company, amountToInvest));
                        System.out.printf("Successfully invested %f in %s!%n", amountToInvest, company.getName());
                        amountChosen = true; // Investment successful, exit loop.
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number or 'N'.");
                    // Loop continues for another attempt.
                }
            }
        }
    }

    private String getBotInvestmentDecision(Game game) {
        return switch (game.getRandom().nextInt(3)) {
            case 0 -> "1";
            case 1 -> "2";
            case 2 -> "N";
            default -> throw new IllegalArgumentException("Invalid choice");
        };
    }

    private double botInvestmentAmount(Player player, Game game, Company company) {
        return game.getRandom().nextDouble(company.getMinInvestment(), player.getBalance());
    }
}

package game.investments;

import java.util.ArrayList;
import java.util.List;

public class InvestmentManager {
    /**
     * A list to store all available companies in the game.
     */
    private final List<Company> allCompanies;

    /**
     * Constructs the InvestmentManager, initializing the list of companies.
     */
    public InvestmentManager() {
        this.allCompanies = new ArrayList<>();

        allCompanies.add(new Company("Evel Co", 500, 0.2, -5, 100));
        allCompanies.add(new Company("Bombs Away", 400, 0.5, -10, 50));
        allCompanies.add(new Company("Clock Work Orange", 300, 1.5, -15, 35));
        allCompanies.add(new Company("Maroders unated", 200, 2, -18, 50));
        allCompanies.add(new Company("Fatcat incorporated", 100, 2.5, -25, 100));
        allCompanies.add(new Company("Macrosoft", 50, 5, -20, 10));
    }

    /**
     * TODO: Implement a method to get a random company for investment.
     * This method will be used when a player lands on an 'Invest' square.
     * @return A random Company object from the list.
     */
    // public Company getRandomCompany() { ... }

    /**
     * TODO: Implement a method to calculate the profit or loss from an investment.
     * The method should use the company's return coefficient and risk interval to calculate the result.
     * @param investment The Investment object to calculate the result for.
     * @return The calculated profit or loss amount.
     */
    // public double calculateInvestmentResult(Investment investment) { ... }
}

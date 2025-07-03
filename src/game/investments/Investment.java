package game.investments;

import game.Game;

public class Investment {
    private final Company company;      // The company the investment is made in
    private final double amount; // The amount of money invested


    /**
     * Constructs a new Investment object.
     */
    public Investment(Company company, double investedAmount) {
        this.company = company;
        this.amount = investedAmount;
    }

    /**
     * Gets the company the investment is made in
     *
     * @return The Company object.
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Gets the amount of money that was invested.
     *
     * @return The invested amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * The method should use the company's return coefficient and risk interval to calculate the result.
     *
     * @return The calculated profit or loss amount.
     */
    public double calculateInvestmentOutcome(Game game) {
        int randomFactor = game.getRandom().nextInt(company.getRiskMin(), company.getRiskMax() + 1);
        return amount * ((double) randomFactor / 100);

        // TODO: Refactor and improve investment outcome calculation logic.
    }

    @Override
    public String toString() {
        return "Investment{" +
                "company=" + company +
                ", investedAmount=" + amount +
                '}';
    }
}

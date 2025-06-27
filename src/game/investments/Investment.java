package game.investments;

public class Investment {
    private final Company company;      // The company the investment is made in
    private final double investedAmount; // The amount of money invested
    private double profitLoss;         // The calculated profit or loss from the investment
    private boolean isCalculated;       // Flag to track if the result has been calculated

    /**
     * Constructs a new Investment object.
     */
    public Investment(Company company, double investedAmount, double profitLoss, boolean isCalculated) {
        this.company = company;
        this.investedAmount = investedAmount;
        this.profitLoss = 0.0;
        this.isCalculated = false;
    }

    /**
     * Gets the company the investment is made in
     * @return The Company object.
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Gets the amount of money that was invested.
     * @return The invested amount.
     */
    public double getInvestedAmount() {
        return investedAmount;
    }

    /**
     * Gets the calculated profit or loss for this investment.
     * @return The profit/loss amount.
     */
    public double getProfitLoss() {
        return profitLoss;
    }

    /**
     * Sets the calculated profit or loss for this investment.
     * @param profitLoss The calculated profit or loss amount.
     */
    public void setProfitLoss(double profitLoss) {
        this.profitLoss = profitLoss;
        this.isCalculated = true; // Mark as calculated once the value is set
    }

    /**
     * Checks if the investment result has been calculated yet.
     * @return True if the result is calculated, false otherwise.
     */
    public boolean isCalculated() {
        return isCalculated;
    }
}

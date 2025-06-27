package game.investments;

public class Company {
    private final String name;
    private final double minInvestment;
    private final double returnCoefficient;
    private final int riskMin;
    private final int riskMax;

    /**
     * Constructs a new Company object with all its specific attributes.
     *
     * @param name The name of the company.
     * @param minInvestment The minimum amount required to invest.
     * @param returnCoefficient The coefficient used to calculate profit/loss.
     * @param riskMin The minimum value of the risk interval.
     * @param riskMax The maximum value of the risk interval.
     */

    public Company(String name, double minInvestment, double returnCoefficient, int riskMin, int riskMax) {
        this.name = name;
        this.minInvestment = minInvestment;
        this.returnCoefficient = returnCoefficient;
        this.riskMin = riskMin;
        this.riskMax = riskMax;
    }

    /**
     * Gets the name of the company.
     * @return The company's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the minimum required investment amount.
     * @return The minimum investment.
     */
    public double getMinInvestment() {
        return minInvestment;
    }

    /**
     * Gets the return coefficient for the company.
     * @return The return coefficient.
     */
    public double getReturnCoefficient() {
        return returnCoefficient;
    }

    /**
     * Gets the minimum value of the risk interval.
     * @return The minimum risk value.
     */
    public int getRiskMin() {
        return riskMin;
    }

    /**
     * Gets the maximum value of the risk interval.
     * @return The maximum risk value.
     */
    public int getRiskMax() {
        return riskMax;
    }
}

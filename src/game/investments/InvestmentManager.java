package game.investments;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InvestmentManager {
    /**
     * A list to store all available companies in the game.
     */
    private final Company[] allCompanies = {new Company("Evel Co", 500, 0.2, -5, 100),
            new Company("Bombs Away", 400, 0.5, -10, 50),
            new Company("Clock Work Orange", 300, 1.5, -15, 35),
            new Company("Maroders unated", 200, 2, -18, 50),
            new Company("Fatcat incorporated", 100, 2.5, -25, 100),
            new Company("Macrosoft", 50, 5, -20, 10)};

    /**
     * This method will be used when a player lands on an 'Invest' square.
     *
     * @return A random Company object from the list.
     */
    public List<Company> getTwoRandomCompanies() {
        Random random = new Random();

        List<Company> chosenCompanies = new ArrayList<>(2);
        int randomIndex1;
        int randomIndex2;
        do {
            randomIndex1 = random.nextInt(0, 5);
            randomIndex2 = random.nextInt(0, 5);
        } while (randomIndex1 == randomIndex2);

        chosenCompanies.add(allCompanies[randomIndex1]);
        chosenCompanies.add(allCompanies[randomIndex2]);

        return chosenCompanies;
    }
}

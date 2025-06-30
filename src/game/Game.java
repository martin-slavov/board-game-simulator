package game;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    public Scanner getScanner() {
        return scanner;
    }

    public Random getRandom() {
        return random;
    }


}
package game;

import game.board.Board;
import game.board.squares.Square;
import game.dice.Dice;
import game.players.Player;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final Dice dice;
    private Board board;
    private Player currentPlayer;
    private boolean gameOver;

    public Game() {
        gameOver = false;
        dice = new Dice();
        board = new Board();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Random getRandom() {
        return random;
    }

    public void startGame() {
        for (Square o : board.getBoard()) {
            System.out.print(o.getType() + "; ");
        }

        System.out.println("\nДобре дошли в играта 'Злите Гении'!");

        try {
            // Пауза за 2 секунди (2000 милисекунди)
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // Обработка на изключението, ако нишката бъде прекъсната
            Thread.currentThread().interrupt(); // Възстановява статуса на прекъсване
            System.err.println("Изпълнението беше прекъснато.");
        }

        System.out.println("Как искате да се казвате в играта?");
        System.out.print("Име: ");
        String playerName = scanner.nextLine().trim();
        Player player = new Player(playerName, false);

        System.out.println("Вашият опонент ще е бот.");
        Player bot = new Player("Bot", true);

        int howIsFirst = Dice.rollTwoSidedDice();

        if (howIsFirst == 1) {
            currentPlayer = player;
        } else {
            currentPlayer = bot;
        }

        System.out.println("Първи започва " + currentPlayer);

        while (!gameOver) {
            System.out.println("\n===== Ход на " + currentPlayer.getName() + " =====");
            if (!currentPlayer.isBot()) {
                System.out.println("Натиснете Enter ...");
                scanner.nextLine();
            }

            currentPlayer.move();
            System.out.println("Позицията Ви: " + (currentPlayer.getCurrentPosition() + 1));
            Square currentSquare = board.getBoard().get(currentPlayer.getCurrentPosition());
            currentSquare.performAction(currentPlayer, this);

            if (currentPlayer.isBot()) {
                currentPlayer = player;
            } else {
                currentPlayer = bot;
            }
        }

    }
}
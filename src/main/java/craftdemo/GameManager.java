package craftdemo;

import com.google.common.annotations.VisibleForTesting;
import craftdemo.strategy.MiniMaxGameStrategy;

import java.io.IOException;
import java.util.Scanner;

import static craftdemo.Utils.playerWinsToss;

public class GameManager {
    private static boolean hasGameEnded = false;
    private static boolean isPlayer = true;
    @VisibleForTesting
    public static final Scanner inputScanner = new Scanner(System.in);
    private static Board board = new Board(3);

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Hey there, please enter your name...");
        String playerName = Utils.getUserName();
        System.out.printf("%s will play against Computer%n", playerName);
        System.out.println(board);
        System.out.println("Flipping a coin to decide who will make first move...");
        Thread.sleep(1000);
        isPlayer = playerWinsToss(playerName);

        while (!hasGameEnded) {
            GamePosition gamePosition = null;
            if (isPlayer) {
                System.out.printf("%s 's turn%n", playerName);
                gamePosition = makeMove();
                board = new Board(board, gamePosition, PlayerSign.Cross);
            } else {
                System.out.println("Computer 's turn");
                board = MiniMaxGameStrategy.findBestMove(board);
            }
            isPlayer = !isPlayer;
            System.out.println(board);
            evaluateGame();
        }
    }

    private static void evaluateGame() {
        GameState gameState = board.getGameState();
        hasGameEnded = true;
        switch (gameState) {
            case CrossWin:
                System.out.println("You Won!");
                break;
            case CircleWin:
                System.out.println("Computer Won!");
                break;
            case Draw:
                System.out.println("Draw!");
                break;
            default:
                hasGameEnded = false;
                break;
        }
    }

    public static GamePosition makeMove() {
        GamePosition gamePosition = null;
        while (true) {
            System.out.printf("Pick any number between 1 and %s:", board.getSize()* board.getSize());
            int userInput = getUserInput();
            gamePosition = Utils.fromNumber(userInput, board.getSize());
            if (board.isMarked(gamePosition))
                System.out.println("Already marked!");
            else break;
        }
        return gamePosition;
    }

    public static int getUserInput() {
        int ret = -1;
        while (true) {
            try {
                ret = Integer.parseInt(inputScanner.nextLine());
            } catch (NumberFormatException e) {
            }
            if (ret < 1 | ret > board.getSize()*board.getSize())
                System.out.printf("\nInvalid input. Please pick between 1 - %s " , board.getSize()*board.getSize());
            else break;
        }
        return ret;
    }
}
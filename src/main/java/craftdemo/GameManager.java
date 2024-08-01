package craftdemo;

import com.google.common.annotations.VisibleForTesting;
import craftdemo.strategy.MiniMaxGameStrategy;

import java.util.Scanner;

import static craftdemo.Utils.playerWinsToss;

public class GameManager {
    private static boolean hasGameEnded = false;
    private static boolean isPlayer = true;
    @VisibleForTesting
    public static final Scanner inputScanner = new Scanner(System.in);
    private static Board board = new Board(3);

    public static void main(String[] args) throws InterruptedException {
        System.out.println(board);
        System.out.println("Flipping a coin to decide who will make first move...");
        Thread.sleep(1000);
        isPlayer = playerWinsToss();
        while (!hasGameEnded) {
            GamePosition gamePosition = null;
            if (isPlayer) {
                gamePosition = makeMove();
                board = new Board(board, gamePosition, PlayerSign.Cross);
            } else {
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
            System.out.printf("Pick any number between 0 and %s for column:", board.getSize()-1);
            int column = getColOrRow();
            System.out.printf("Pick any number between 0 and %s for row:", board.getSize()-1);
            int row = getColOrRow();
            gamePosition = new GamePosition(column, row);
            if (board.isMarked(gamePosition))
                System.out.println("Already marked!");
            else break;
        }
        return gamePosition;
    }

    public static int getColOrRow() {
        int ret = -1;
        while (true) {
            try {
                ret = Integer.parseInt(inputScanner.nextLine());
            } catch (NumberFormatException e) {
            }
            if (ret < 0 | ret > 2)
                System.out.print("\nInvalid input. Please pick 0, 1 or 2: ");
            else break;
        }
        return ret;
    }
}
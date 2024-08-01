package craftdemo;

import com.google.common.annotations.VisibleForTesting;
import craftdemo.gamestatus.GameStatusManager;
import craftdemo.input.InputService;
import craftdemo.strategy.MiniMaxGameStrategy;

import java.io.IOException;
import java.util.Scanner;

import static craftdemo.Utils.playerWinsToss;

public class GameManager {
    private static boolean hasGameEnded = false;
    private static Board board = new Board(3);

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Hey there, please enter your name...");
        String playerName = Utils.getUserName();
        System.out.printf("%s will play against Computer%n", playerName);
        System.out.println(board);
        System.out.println("Flipping a coin to decide who will make first move...");
        Thread.sleep(1000);
        boolean isPlayer = playerWinsToss(playerName);

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
            hasGameEnded = GameStatusManager.evaluateGame(playerName, board);
        }
    }

    public static GamePosition makeMove() {
        GamePosition gamePosition = null;
        while (true) {
            System.out.printf("Pick any number between 1 and %s:", board.getSize()* board.getSize());
            int userInput = InputService.getUserInput(board.getSize());
            gamePosition = Utils.fromNumber(userInput, board.getSize());
            if (board.isMarked(gamePosition))
                System.out.println("Already marked!");
            else break;
        }
        return gamePosition;
    }
}
package craftdemo.gamestatus;

import craftdemo.Board;
import craftdemo.GameState;

public class GameStatusManager {
    public static boolean evaluateGame(String playerName, Board board) {
        GameState gameState = board.getGameState();
        switch (gameState) {
            case CrossWin:
                System.out.printf("Player %s Won!", playerName);
                return true;
            case CircleWin:
                System.out.println("Computer Won!");
                return true;
            case Draw:
                System.out.println("Draw!");
                return true;
            default:
                return false;
        }
    }
}

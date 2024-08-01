package craftdemo.strategy;

import craftdemo.Board;
import craftdemo.GamePosition;
import craftdemo.GameState;
import craftdemo.PlayerSign;

import java.util.ArrayList;

public class MiniMaxGameStrategy extends GameStrategy {
    public static Board findBestMove(Board board) {
        ArrayList<GamePosition> gamePositions = board.getFreePositions();
        Board bestChild = null;
        int previous = Integer.MIN_VALUE;
        for (GamePosition p : gamePositions) {
            Board child = new Board(board, p, PlayerSign.Circle);
            int current = min(child);
            //System.out.println("Child Score: " + current);
            if (current > previous) {
                bestChild = child;
                previous = current;
            }
        }
        return bestChild;
    }

    private static int max(Board board) {
        GameState gameState = board.getGameState();
        if (gameState == GameState.CircleWin)
            return 1;
        else if (gameState == GameState.CrossWin)
            return -1;
        else if (gameState == GameState.Draw)
            return 0;
        ArrayList<GamePosition> gamePositions = board.getFreePositions();
        int best = Integer.MIN_VALUE;
        for (GamePosition p : gamePositions) {
            Board b = new Board(board, p, PlayerSign.Circle);
            int move = min(b);
            if (move > best)
                best = move;
        }
        return best;
    }

    private static int min(Board board) {
        GameState gameState = board.getGameState();
        if (gameState == GameState.CircleWin)
            return 1;
        else if (gameState == GameState.CrossWin)
            return -1;
        else if (gameState == GameState.Draw)
            return 0;
        ArrayList<GamePosition> gamePositions = board.getFreePositions();
        int best = Integer.MAX_VALUE;
        for (GamePosition p : gamePositions) {
            Board b = new Board(board, p, PlayerSign.Cross);
            int move = max(b);
            if (move < best)
                best = move;
        }
        return best;
    }
}

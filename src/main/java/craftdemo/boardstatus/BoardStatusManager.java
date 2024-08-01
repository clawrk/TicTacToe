package craftdemo.boardstatus;

import craftdemo.GamePosition;
import craftdemo.GameState;

import java.util.ArrayList;

public class BoardStatusManager {
    public static GameState getGameState(ArrayList<GamePosition> gamePositions, char[][] board, int size) {
        if (hasWon('x', board, size))
            return GameState.CrossWin;
        else if (hasWon('o', board, size))
            return GameState.CircleWin;
        else if (gamePositions.isEmpty())
            return GameState.Draw;
        else return GameState.Incomplete;
    }

    public static boolean hasWon(char sign, char[][] board, int size) {
        int x, y;

        //Check diagonals
        boolean diagonal1 = true;
        boolean diagonal2 = true;
        for (int d = 0; d < size; d++) {
            if (board[d][d] != sign) {
                diagonal1 = false;
            }
            if (board[d][size - 1 - d] != sign) {
                diagonal2 = false;
            }
        }
        if (diagonal1 || diagonal2)
            return true;

        //Check row
        for (x = 0; x < size; x++) {
            for (y = 0; y < size; y++)
                if (board[x][y] != sign)
                    break;
            if (y == size)
                return true;
        }

        //Check col
        for (x = 0; x < size; x++) {
            for (y = 0; y < size; y++)
                if (board[y][x] != sign)
                    break;
            if (y == size)
                return true;
        }
        return false;
    }
}

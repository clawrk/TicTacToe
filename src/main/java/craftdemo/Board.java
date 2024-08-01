package craftdemo;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;

public class Board {
    private final char[][] board; //e = empty, x = cross, o = circle.
    private final int size;
    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                board[x][y] = 'e'; //Board initially empty
    }

    public Board(Board from, GamePosition gamePosition, PlayerSign sign) {
        this.size = from.getSize();
        board = new char[size][size];
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                board[x][y] = from.board[x][y];
        board[gamePosition.getColumn()][gamePosition.getRow()] = sign == PlayerSign.Cross ? 'x' : 'o';
    }

    public ArrayList<GamePosition> getFreePositions() {
        ArrayList<GamePosition> retArr = new ArrayList<GamePosition>();
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                if (board[x][y] == 'e')
                    retArr.add(new GamePosition(x, y));
        return retArr;
    }

    public GameState getGameState() {
        if (hasWon('x'))
            return GameState.CrossWin;
        else if (hasWon('o'))
            return GameState.CircleWin;
        else if (getFreePositions().size() == 0)
            return GameState.Draw;
        else return GameState.Incomplete;
    }

    @VisibleForTesting
    public boolean hasWon(char sign) {
        int x, y;

        //Check diagonals
        boolean diagonal1 = true;
        boolean diagonal2 = true;
        for (int d = 0; d < size; d++) {
            if (board[d][d] != sign) {
                diagonal1 = false;
            }
            if(board[d][size-1-d] != sign) {
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

    public boolean isMarked(GamePosition gamePosition) {
        if (board[gamePosition.getColumn()][gamePosition.getRow()] != 'e')
            return true;
        return false;
    }

    public String toString() {
        String retString = "\n";
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (board[x][y] == 'x' || board[x][y] == 'o')
                    retString += "[" + board[x][y] + "]";
                else
                    retString += "[ ]";
            }
            retString += "\n";
        }
        return retString;
    }

    public int getSize() {
        return size;
    }

    @VisibleForTesting
    public char getCellValue(int X, int Y) {
        assert X < size && Y < size;
        return board[X][Y];
    }
}

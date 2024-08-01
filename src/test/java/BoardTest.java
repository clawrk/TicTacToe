import craftdemo.Board;
import craftdemo.GamePosition;
import craftdemo.PlayerSign;
import craftdemo.boardstatus.BoardStatusManager;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    public void testMarkedCheckingFunction() {
        Board initialState = new Board(3);
        GamePosition gamePosition = new GamePosition(0, 0);
        Board nextState = new Board(initialState, gamePosition, PlayerSign.Cross);
        boolean isMarkedCorrectly = nextState
                .getCellValue(gamePosition.getRow(), gamePosition.getColumn()) == 'x';
        Assertions.assertEquals(nextState.isMarked(gamePosition), isMarkedCorrectly);
    }

    @Test
    public void verifyIfBoardIsUpdatedCorrectly() {
        Board initialState = new Board(3);
        GamePosition gamePosition = new GamePosition(0, 0);
        Board nextState = new Board(initialState, gamePosition, PlayerSign.Cross);
        Assertions.assertTrue(nextState.isMarked(gamePosition));
    }

    @Test
    public void testBoardWonWhenDiagonalIsPopulated() {
        Board initialState = new Board(3);
        GamePosition gamePosition = new GamePosition(0, 0);
        GamePosition gamePosition1 = new GamePosition(1, 1);
        GamePosition gamePosition2 = new GamePosition(2, 2);


        Board nextState = new Board(initialState, gamePosition, PlayerSign.Cross);
        nextState = new Board(nextState, gamePosition1, PlayerSign.Cross);
        nextState = new Board(nextState, gamePosition2, PlayerSign.Cross);

        Assertions.assertTrue(BoardStatusManager.hasWon('x', nextState.getBoard(), nextState.getSize()));
    }

    @Test
    public void testBoardWonWhenSecondDiagonalIsPopulated() {
        Board initialState = new Board(3);
        GamePosition gamePosition = new GamePosition(0, 2);
        GamePosition gamePosition1 = new GamePosition(1, 1);
        GamePosition gamePosition2 = new GamePosition(2, 0);


        Board nextState = new Board(initialState, gamePosition, PlayerSign.Cross);
        nextState = new Board(nextState, gamePosition1, PlayerSign.Cross);
        nextState = new Board(nextState, gamePosition2, PlayerSign.Cross);

        Assertions.assertTrue(BoardStatusManager.hasWon('x', nextState.getBoard(), nextState.getSize()));
    }

    @Test
    public void testBoardWonWhenAnyRowIsPopulated() {
        Board initialState = new Board(3);
        GamePosition gamePosition = new GamePosition(0, 2);
        GamePosition gamePosition1 = new GamePosition(1, 2);
        GamePosition gamePosition2 = new GamePosition(2, 2);


        Board nextState = new Board(initialState, gamePosition, PlayerSign.Cross);
        nextState = new Board(nextState, gamePosition1, PlayerSign.Cross);
        nextState = new Board(nextState, gamePosition2, PlayerSign.Cross);

        Assertions.assertTrue(BoardStatusManager.hasWon('x', nextState.getBoard(), nextState.getSize()));
    }

    @Test
    public void testBoardWonWhenAnyColumnIsPopulated() {
        Board initialState = new Board(3);
        GamePosition gamePosition = new GamePosition(0, 0);
        GamePosition gamePosition1 = new GamePosition(0, 1);
        GamePosition gamePosition2 = new GamePosition(0, 2);


        Board nextState = new Board(initialState, gamePosition, PlayerSign.Cross);
        nextState = new Board(nextState, gamePosition1, PlayerSign.Cross);
        nextState = new Board(nextState, gamePosition2, PlayerSign.Cross);

        Assertions.assertTrue(BoardStatusManager.hasWon('x', nextState.getBoard(), nextState.getSize()));
    }

    @Test
    public void testBoardWonWhenAnyColumnIsNotPopulated() {
        Board initialState = new Board(3);
        GamePosition gamePosition = new GamePosition(0, 0);
        GamePosition gamePosition1 = new GamePosition(0, 1);


        Board nextState = new Board(initialState, gamePosition, PlayerSign.Cross);
        nextState = new Board(nextState, gamePosition1, PlayerSign.Cross);

        Assertions.assertFalse(BoardStatusManager.hasWon('x', nextState.getBoard(), nextState.getSize()));
    }
}

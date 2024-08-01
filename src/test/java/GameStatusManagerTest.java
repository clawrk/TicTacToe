import craftdemo.Board;
import craftdemo.GameState;
import craftdemo.gamestatus.GameStatusManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class GameStatusManagerTest {
    @Mock
    Board board;

    @Test
    public void testGameStatusWhenBoardReturnsSomeoneWins() {
        Mockito.when(board.getGameState()).thenReturn(GameState.CircleWin);
        Assertions.assertTrue(GameStatusManager.evaluateGame("abc", board) );
    }

    @Test
    public void testGameStatusWhenBoardReturnsDraw() {
        when(board.getGameState()).thenReturn(GameState.Incomplete);
        Assertions.assertFalse(GameStatusManager.evaluateGame("abc", board));
    }
}

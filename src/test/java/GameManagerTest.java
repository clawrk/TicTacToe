import craftdemo.GameManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

public class GameManagerTest {
    private GameManager gameManager;

    @BeforeEach
    void setup() {
        gameManager = new GameManager();
    }

}

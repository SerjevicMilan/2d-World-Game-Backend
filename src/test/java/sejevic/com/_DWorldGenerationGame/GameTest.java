package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import sejevic.com._DWorldGenerationGame.core.game.Game;
import sejevic.com._DWorldGenerationGame.core.game.GameState;
import sejevic.com._DWorldGenerationGame.core.game.GameStatus;

import static com.google.common.truth.Truth.assertThat;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(properties = {
        "game.width=20",
        "game.height=20",
        "game.seed=1",
        "game.density=1.0"
})
public class GameTest {
    @Autowired
    private  Game game;

    @BeforeEach
    public void setGameRuning() {
        game.setRunning(true);
    }


    @Test
    void gameShouldStartRunning() {
        GameState state = game.getCurrentGameState();
        assertThat(state.status).isEqualTo(GameStatus.RUNNING);
    }

    @Test
    void playerShouldMoveRight() {
        GameState before = game.getCurrentGameState();
        game.updatePlayer('D');
        GameState after = game.getCurrentGameState();

        // x should have increased (assuming valid floor tile)
        assertThat(after.player.x).isAtLeast(before.player.x);
    }

    @Test
    void playerShouldMoveRightWithD() {
        GameState before = game.getCurrentGameState();
        game.updatePlayer('D');
        GameState after = game.getCurrentGameState();

        assertThat(after.player.x).isAtLeast(before.player.x);
    }

    @Test
    void playerShouldMoveLeftWithA() {
        GameState before = game.getCurrentGameState();
        game.updatePlayer('A');
        GameState after = game.getCurrentGameState();

        assertThat(after.player.x).isAtMost(before.player.x);
    }

    @Test
    void playerShouldMoveUpWithW() {
        GameState before = game.getCurrentGameState();
        game.updatePlayer('S');
        GameState after = game.getCurrentGameState();

        assertThat(after.player.y).isGreaterThan(before.player.y);
    }

    @Test
    void playerShouldMoveDownWithS() {
        GameState before = game.getCurrentGameState();
        game.updatePlayer('W');
        GameState after = game.getCurrentGameState();

        assertThat(after.player.y).isLessThan(before.player.y);
    }

}


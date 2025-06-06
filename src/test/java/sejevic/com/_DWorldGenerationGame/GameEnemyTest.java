package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sejevic.com._DWorldGenerationGame.core.game.Game;
import sejevic.com._DWorldGenerationGame.core.game.GameStatus;

import static com.google.common.truth.Truth.assertThat;

@SpringBootTest
public class GameEnemyTest {

    @Autowired
    Game game;

    @Test
    void enemyShouldEventuallyCatchPlayerIfNoMovement() throws InterruptedException {
        game.setRunning(true);
        GameStatus status = game.getCurrentGameState().status;
        int maxWaitMillis = 10000; // maximum time to wait for the enemy to catch the player
        int waited = 0;

        while (status == GameStatus.RUNNING && waited < maxWaitMillis) {
            Thread.sleep(200); // give some time for enemy to move
            status = game.getCurrentGameState().status;
            waited += 200;
        }

        // Check that the game eventually ends with LOST
        assertThat(status).isEqualTo(GameStatus.LOST);
    }

}

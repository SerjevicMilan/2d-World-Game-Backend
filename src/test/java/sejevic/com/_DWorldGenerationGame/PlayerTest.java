package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.Test;
import sejevic.com._DWorldGenerationGame.core.player.Player;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.google.common.truth.Truth.assertThat;

public class PlayerTest {
    @Test
    public void playerBasicTest() {
        List<Coordinate> validCordinates = new ArrayList<>();

        for (int x = 0; x < 80; x++) {
            for (int y = 0; y < 50; y++) {
                validCordinates.add(new Coordinate(x,y));
            }
        }

        Player player = new Player( new Random(1), validCordinates);

        assertThat(validCordinates.contains(player.spawnPlayer())).isTrue();
    }
}

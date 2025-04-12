package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.Test;
import sejevic.com._DWorldGenerationGame.core.enemy.Enemy;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.google.common.truth.Truth.assertThat;

public class EnemyTest {
    @Test
    public void enemyBasicTest() {
        List<Coordinate> validCordinates = new ArrayList<>();

        for (int x = 0; x < 80; x++) {
            for (int y = 0; y < 50; y++) {
                validCordinates.add(new Coordinate(x,y));
            }
        }

        Enemy enemy = new Enemy( new Random(1), validCordinates);

        assertThat(validCordinates.contains(enemy.spawn())).isTrue();
    }
}

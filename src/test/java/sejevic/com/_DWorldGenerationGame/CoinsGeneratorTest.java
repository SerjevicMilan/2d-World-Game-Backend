package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.Test;
import sejevic.com._DWorldGenerationGame.core.utils.CoinGenerator;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.google.common.truth.Truth.assertThat;

public class CoinsGeneratorTest {
    @Test
    public void coinsGeneratorBasicTest() {
        List<Coordinate> floorTiles = new ArrayList<>();
        floorTiles.add(new Coordinate(2,2));
        floorTiles.add(new Coordinate(3,2));
        floorTiles.add(new Coordinate(4,2));
        floorTiles.add(new Coordinate(5,2));

        Random random1 = new Random(1);
        Random random2 = new Random(2);

        List<Coordinate> coins1 = new CoinGenerator().generateCoins(2, random1, floorTiles);
        List<Coordinate> coins2 = new CoinGenerator().generateCoins(2, random2, floorTiles);


        assertThat(coins1.size()).isEqualTo(2);
        assertThat(coins2.size()).isEqualTo(2);
        assertThat(floorTiles.contains(coins1.get(0))).isTrue();
        assertThat(floorTiles.contains(coins2.get(0))).isTrue();
        assertThat(coins1.get(0)).isNotEqualTo(coins2.get(0));
        assertThat(coins1).isNotEqualTo(coins2);
    }
}

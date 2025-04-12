package sejevic.com._DWorldGenerationGame;

import sejevic.com._DWorldGenerationGame.core.enemy.EnemyPathGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;
import sejevic.com._DWorldGenerationGame.core.utils.Graph;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;


public class EnemyPathGeneratorTest {
    private List<Coordinate> floorTiles;
    EnemyPathGenerator path = new EnemyPathGenerator();
    Graph<Coordinate> graph;

    @BeforeEach
    void setUp() {
        floorTiles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                floorTiles.add(new Coordinate(i, j));
            }
        }
        graph = path.generateGraph(floorTiles);
    }

    @Test
    public void basicGenerateGraphTest() {
        assertThat(graph.containsNode(new Coordinate(2,2))).isTrue();

        List<Coordinate> expected = new ArrayList<>();
        expected.add(new Coordinate(1,2));
        expected.add(new Coordinate(3,2));
        expected.add(new Coordinate(2,1));
        expected.add(new Coordinate(2,3));

        assertThat(graph.getAdjacent(new Coordinate(2, 2))).isEqualTo(expected);
    }

    @Test
    public void basicGeneratePathTest() {
        List<Coordinate> expected = new ArrayList<>();
        expected.add(new Coordinate(1,0));
        expected.add(new Coordinate(1,1));
        expected.add(new Coordinate(1,2));
        expected.add(new Coordinate(1,3));

        assertThat(path.generatePath(new Coordinate(1, 0), new Coordinate(1, 3))).isEqualTo(expected);
    }


}

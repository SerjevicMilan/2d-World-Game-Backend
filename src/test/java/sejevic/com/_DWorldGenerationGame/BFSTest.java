package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.Test;
import sejevic.com._DWorldGenerationGame.core.utils.BFS;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;
import sejevic.com._DWorldGenerationGame.core.utils.Graph;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class BFSTest {
    @Test
    public void basicBFSTest() {
        Graph<Coordinate> graph = new Graph<>();
        List<Coordinate> allNodes = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Coordinate pos = new Coordinate(i, j);
                graph.addNode(pos);
                allNodes.add(pos);
            }
        }
        graph.addEdge(allNodes.get(0), allNodes.get(1), 0.0);
        graph.addEdge(allNodes.get(0), allNodes.get(3), 0.0);

        graph.addEdge(allNodes.get(1), allNodes.get(0), 0.0);
        graph.addEdge(allNodes.get(1), allNodes.get(2), 0.0);
        graph.addEdge(allNodes.get(1), allNodes.get(4), 0.0);

        graph.addEdge(allNodes.get(2), allNodes.get(1), 0.0);
        graph.addEdge(allNodes.get(2), allNodes.get(5), 0.0);

        graph.addEdge(allNodes.get(3), allNodes.get(0), 0.0);
        graph.addEdge(allNodes.get(3), allNodes.get(4), 0.0);
        graph.addEdge(allNodes.get(3), allNodes.get(6), 0.0);

        graph.addEdge(allNodes.get(4), allNodes.get(1), 0.0);
        graph.addEdge(allNodes.get(4), allNodes.get(3), 0.0);
        graph.addEdge(allNodes.get(4), allNodes.get(5), 0.0);
        graph.addEdge(allNodes.get(4), allNodes.get(7), 0.0);

        graph.addEdge(allNodes.get(5), allNodes.get(2), 0.0);
        graph.addEdge(allNodes.get(5), allNodes.get(4), 0.0);
        graph.addEdge(allNodes.get(5), allNodes.get(8), 0.0);

        graph.addEdge(allNodes.get(6), allNodes.get(3), 0.0);
        graph.addEdge(allNodes.get(6), allNodes.get(7), 0.0);

        graph.addEdge(allNodes.get(7), allNodes.get(6), 0.0);
        graph.addEdge(allNodes.get(7), allNodes.get(8), 0.0);
        graph.addEdge(allNodes.get(7), allNodes.get(4), 0.0);

        graph.addEdge(allNodes.get(8), allNodes.get(7), 0.0);
        graph.addEdge(allNodes.get(8), allNodes.get(5), 0.0);

        BFS<Coordinate> bfs = new BFS<>(graph);

        List<Coordinate> expectedPath = new ArrayList<>();
        expectedPath.add(allNodes.get(1));
        expectedPath.add(allNodes.get(4));
        expectedPath.add(allNodes.get(7));

        assertThat(bfs.findPath(allNodes.get(1), allNodes.get(7))).isEqualTo(expectedPath);

        List<Coordinate> expectedPath1 = new ArrayList<>();
        expectedPath1.add(allNodes.get(0));
        expectedPath1.add(allNodes.get(1));
        expectedPath1.add(allNodes.get(2));

        assertThat(bfs.findPath(allNodes.get(0), allNodes.get(2))).isEqualTo(expectedPath1);
    }
}

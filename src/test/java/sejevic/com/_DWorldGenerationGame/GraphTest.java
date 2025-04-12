package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.Test;
import sejevic.com._DWorldGenerationGame.core.room.Room;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;
import sejevic.com._DWorldGenerationGame.core.utils.Graph;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class GraphTest {

    @Test
    public void basicGraphTest() {
        Graph<Room> graph = new Graph<>();
        Room r1 = new Room(new Coordinate(5, 5), 2, 2);
        Room r2 = new Room(new Coordinate(25, 15), 2, 2);
        Room r3 = new Room(new Coordinate(10, 10), 2, 2);
        Room r4 = new Room(new Coordinate(20, 35), 2, 2);

        graph.addNode(r1);
        graph.addNode(r2);
        graph.addNode(r3);
        graph.addNode(r4);

        graph.addEdge(r1, r2, r1.distanceTo(r2));
        graph.addEdge(r1, r3, r1.distanceTo(r3));
        graph.addEdge(r1, r4, r1.distanceTo(r4));

        List<Room> expected = new ArrayList<>();
        expected.add(r2);
        expected.add(r3);
        expected.add(r4);

        assertThat(graph.getAdjacent(r1)).isEqualTo(expected);
        assertThat(graph.getWeight(r1, r3)).isEqualTo(7.0710678118654755);
        assertThat(graph.getWeight(r1, new Room(new Coordinate(7,7), 2, 2))).isEqualTo(-1);

        expected.add(r1);
        assertThat(graph.getAllVertices()).containsExactlyElementsIn(expected);
    }
}

package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.Test;
import sejevic.com._DWorldGenerationGame.core.room.Room;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;
import sejevic.com._DWorldGenerationGame.core.utils.Edge;
import sejevic.com._DWorldGenerationGame.core.utils.Graph;
import sejevic.com._DWorldGenerationGame.core.utils.Kruskals;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

    public class KruskalsTest {
        @Test
        public void basicKruskalsTest() {
            Graph<Room> graph = new Graph<>();
            Room r1 = new Room(new Coordinate(5, 5), 2, 2);
            Room r2 = new Room(new Coordinate(25, 15), 2, 2);
            Room r3 = new Room(new Coordinate(10, 10), 2, 2);
            Room r4 = new Room(new Coordinate(20, 35), 2, 2);

            graph.addNode(r1);
            graph.addNode(r2);
            graph.addNode(r3);
            graph.addNode(r4);

            for (Room room1 : graph.getAllVertices() ) {
                for (Room room2 : graph.getAllVertices() ) {
                    if (room1 != room2) {
                        graph.addEdge(room1, room2, room1.distanceTo(room2));
                    }
                }
            }

            Kruskals<Room> MST = new Kruskals<>(graph);

            List<Edge<Room>> expected = new ArrayList<>();
            expected.add(new Edge<>(r1, r3, r1.distanceTo(r3)));
            expected.add(new Edge<>(r3, r2, r3.distanceTo(r2)));
            expected.add(new Edge<>(r2, r4, r2.distanceTo(r4)));

            assertThat(MST.getMST()).isEqualTo(expected);
        }
    }

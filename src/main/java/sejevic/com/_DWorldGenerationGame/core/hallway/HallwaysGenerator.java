package sejevic.com._DWorldGenerationGame.core.hallway;

import sejevic.com._DWorldGenerationGame.core.room.Room;
import sejevic.com._DWorldGenerationGame.core.utils.Edge;
import sejevic.com._DWorldGenerationGame.core.utils.Graph;
import sejevic.com._DWorldGenerationGame.core.utils.Kruskals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//finds closest rooms and build hallway between them,
//we find all connections by finding MST from graph of rooms
public class HallwaysGenerator {
    List<Room> rooms;
    Graph<Room> graph = new Graph<>();
    List<Hallway> hallways = new ArrayList<>();
    Random random;

    //take list of rooms and build a graph from them
    public HallwaysGenerator(List<Room> rooms, Random random) {
        this.rooms = rooms;
        this.random = random;
        buildGraph();
    }

    public HallwaysGenerator(List<Room> rooms) {
        this.rooms = rooms;
        buildGraph();
    }

    //finds MST and generates hallways from all edges
    public List<Hallway> generateHallways() {
        Kruskals<Room> MST = new Kruskals<>(graph);
        List<Edge<Room>> edges = MST.getMST();
        generate(edges);

        return hallways;
    }

    //for each edge create a hallway and add it to the hallways list
    private void generate(List<Edge<Room>> edges) {
        for (Edge<Room> edge: edges) {
            hallways.add(new Hallway(edge.getBase().getCenter(), edge.getNeighbor().getCenter(), random, rooms, hallways));
        }
    }

    //builds a graph from rooms and connects them
    public void buildGraph() {
        addAllRooms();
        connectAllEdges();
    }

    //Add rooms to graph
    private void addAllRooms() {
        for (Room room: rooms) {
            graph.addNode(room);
        }
    }

    //go through all rooms and connect them to all the rest
    private void connectAllEdges() {
        for (Room room1 : graph.getAllVertices() ) {
            for (Room room2 : graph.getAllVertices() ) {
                if (room1 != room2) {
                    graph.addEdge(room1, room2, room1.distanceTo(room2));
                }
            }
        }
    }

}

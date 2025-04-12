package sejevic.com._DWorldGenerationGame.core.enemy;

import sejevic.com._DWorldGenerationGame.core.utils.BFS;
import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;
import sejevic.com._DWorldGenerationGame.core.utils.Graph;

import java.util.List;

/**
 * Generate a path between the enemy and player using BFS on a graph of floor tiles.
 */
public class EnemyPathGenerator {
    Coordinate player;
    Coordinate enemy;
    List<Coordinate> floorTiles;
    Graph<Coordinate> floorGraph;
    List<Coordinate> path;
    BFS<Coordinate> bfs;

    /**
     *
     * Build graph by connecting all neighboring floor tiles.
     */
    public Graph<Coordinate> generateGraph(List<Coordinate> floorTiles) {
        this.floorTiles = floorTiles;
        floorGraph = new Graph<>();
        addFloorNodes();
        addNeighburs();
        bfs = new BFS<>(floorGraph);
        return floorGraph;
    }

    /**
     * Use BFS to find the shortest path between from (enemy) and to (player)
     * @param from path starting position
     * @param to path ending position
     * @return list of coordinates representing path
     */
    public List<Coordinate> generatePath(Coordinate from, Coordinate to) {
        return bfs.findPath(from, to);
    }

    /**
     *Returns the last generated path.
     */
    public List<Coordinate> getPath() {
        return null;
    }

    /**
     * fill graph with floor tiles
     */
    private void addFloorNodes() {
        for (Coordinate floorTile : floorTiles) {
            floorGraph.addNode(floorTile);
        }
    }

    /**
     * make edges from  valid neighbours for all vertices
     */
    private void addNeighburs() {
        for (Coordinate base : floorGraph.getAllVertices()) {
            addSuroundingTiles(base);
        }
    }

    /**
     * check one up,left,down and right from base tile,
     * if tile is valid add it base edge
     * @param base tile in floor tile grid
     */
    private void addSuroundingTiles(Coordinate base) {
        addIfValid(base, new Coordinate(base.x - 1, base.y ));//one tile left
        addIfValid(base, new Coordinate(base.x + 1, base.y ));//one tile right
        addIfValid(base, new Coordinate(base.x, base.y - 1 ));//one tile down
        addIfValid(base, new Coordinate(base.x, base.y + 1 ));//one tile up
    }

    //if neighbur is valid floor tile add it as edge
    private void addIfValid(Coordinate base, Coordinate neighbur) {
        if (floorTiles.contains(neighbur)) {
            floorGraph.addEdge(base, neighbur, 0.0);
        }
    }
}

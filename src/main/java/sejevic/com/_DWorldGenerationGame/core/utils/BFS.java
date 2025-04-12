package sejevic.com._DWorldGenerationGame.core.utils;

import java.util.*;

/**
 * The BFS class is responsible for performing Breadth-First Search
 * on a graph built from floor tiles in a 2D world.
 * It finds the shortest path between a start and end coordinate
 * — used for enemy movement toward the player.
 */
public class BFS<T> {
    private Graph<T> graph;
    List<T> path = new ArrayList<>();
    private Map<T, T> edgeTo = new HashMap<>();
    //edge-to-parent mapping

    public BFS(Graph<T> graph) {
        this.graph = graph;
    }

    /**
     * 	Runs BFS from start to end
     * 	Returns the path as a list of coordinates
     *  Returns an empty list if no path is found
     * @param start from this node start search
     * @param end   node to find
     * @return list of coordinate representing path
     */
    public List<T> findPath(T start, T end) {
        path = new ArrayList<>();
        edgeTo = new HashMap<>();
        HashSet<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        T parent = start;
        queue.add(start);
        visited.add(parent);

        while (!queue.isEmpty()) {
            parent = queue.poll();
            if (parent.equals(end)) {
                buildPath(end);
                return path;
            }

            for(T node : graph.getAdjacent(parent)) {
                if (visited.contains(node)) { continue; }
                queue.add(node);
                visited.add(node);
                edgeTo.put(node, parent);
            }

        }


        return path;
    }

    /**
     *  Reconstructs the shortest path using the edge-to-parent mapping
     * @param end last coordinate in path
     */
    private void buildPath(T end) {
        if (!edgeTo.containsKey(end)) {
            path.add(end);
            return;
        }
        buildPath(edgeTo.get(end));
        path.add(end);
    }

    /**
     * 	Returns true if a path was successfully found
     */
    public boolean hasPath() {
        return !path.isEmpty();
    }

    /**
     *   Returns the stored path from the last search
     */
    public List<T> getPath() {
        return path;
    }
}

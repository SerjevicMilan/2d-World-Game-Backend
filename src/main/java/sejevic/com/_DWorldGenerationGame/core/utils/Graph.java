package sejevic.com._DWorldGenerationGame.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Graph<T> {
    //hashMap for storing graph
    private HashMap<T , List<Edge<T>>> Graph;
    private int size = 0;

    //init graph
    public Graph() {
        Graph = new HashMap<>();
    }

    /*adding value as new hash key and empty edge list
    @param value new vortices
     */
    public  void addNode(T value) {
        Graph.put(value, new ArrayList<>());
        size += 1;
    }

    /*
    get node from hashmap with base as a key and add new edge to the node
    @param base key for node in hashMap
    @param neighbor adjacent  node to a base node
    @param weight of that edge
     */
    public void addEdge(T base, T neighbor, double weight) {
        if (!Graph.containsKey(base) || !Graph.containsKey(neighbor))
            throw new IllegalArgumentException("nodes are not in graph");
        if (weight < 0) {
            throw new IllegalArgumentException("no negative weight allowed");
        }

        Graph.get(base).add(new Edge<T>(base, neighbor, weight));
    }


    /*
    get neighboring vortices using v as a key for the HashMap
    and copying array by passing it in constructor
    @param v
    key for Graph(HashMap)
    @return
    List of T representing vortices
     */
    public List<T> getAdjacent(T v) {
        List<T> list = new ArrayList<>();
        if (!Graph.containsKey(v))
            return list;

        for(Edge<T> edge : Graph.get(v)) {
            list.add(edge.neighbor);
        }
        return list;
    }

    /*
    Use base to find edges list. Check all edges for matching neighbor.
    If its found return weight, if not return -1
    @param base key for node in hashMap
    @param neighbor adjacent  node to a base node
    @return weight of that edge
     */
    public double getWeight(T base, T neighbor) {
        if (!Graph.containsKey(base))
            return -1;

        for (Edge<T> edge : Graph.get(base)) {//check all edges
            if (edge.neighbor == neighbor) {
                return edge.weight;
            }
        }
        return -1;
    }

    /*
    check if v1 node has v2 for a neighbor
    @param v1
    key for graph(hashmap)
    @param v2
    key for graph as well
    @return
    if connected return true otherwise false
     */
    public boolean isConnected(T v1, T v2) {
        if (!Graph.containsKey(v1) || !Graph.containsKey(v2))//check for bad input
            return false;

        for(Edge<T> edge : Graph.get(v1)) {//check all edges
            if (edge.neighbor == v2) {
                return true;
            }
        }
        return false;
    }

    /*
    gets all keys from HashMap(Graph)
    and add them to a list
    @return
    List of T
     */

    //return size of a graph
    public int size() {
        return size;
    }

    public boolean containsNode(T node) {
        return Graph.containsKey(node);
    }


    public List<Edge<T>> getAllEdges() {
        List<Edge<T>> list = new ArrayList<>();
        for (List<Edge<T>> edges : Graph.values()) {
            list.addAll(edges);
        }
        return list;
    }

    /*
  gets all keys from HashMap(Vertices from Graph)
  and add them to a list
  @return
  List of T
   */
    public List<T> getAllVertices() {
        List<T> list = new ArrayList<>();
        for (T vertice : Graph.keySet()) {
            list.add(vertice);
        }
        return list;
    }
}

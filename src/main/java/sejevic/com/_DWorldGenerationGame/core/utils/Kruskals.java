    package sejevic.com._DWorldGenerationGame.core.utils;

    import java.util.ArrayList;
    import java.util.List;

    //uses Kruskal's algorithm to find minimum spanning tree
    public class Kruskals<T> {
        Graph<T> graph;

        public Kruskals (Graph<T> graph) {
            this.graph = graph;
        }

        //sort all edges and starts adding to list from the smallest edge
        //escapes cyclic connections by using union(skips edges that would make it cyclic)
        public List<Edge<T>> getMST() {
            List<Edge<T>> MST = new ArrayList<>();
            UnionFind<T> uf = new UnionFind<>();
            List<Edge<T>> edges = graph.getAllEdges();

            if (graph == null || graph.getAllVertices().isEmpty()) {
                return new ArrayList<>();
            }

            //adds all vertices to unionFind
            for (T vertice : graph.getAllVertices()) {
                uf.addItem(vertice);
            }

            edges.sort(null);
            for(Edge<T> edge : edges) {
                if (MST.size() == graph.size() - 1) {//when Edges == Vertices - 1 MST is found
                    break;
                }
                if (uf.union(edge.base, edge.neighbor)) {// makes sure it's not making tree cyclic
                    MST.add(edge);
                }
            }
            return MST;
        }
    }

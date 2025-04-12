package sejevic.com._DWorldGenerationGame.core.utils;

public class Edge<T> implements Comparable<Edge<T>> {
    T base;
    T neighbor;
    double weight;

    public Edge(T base, T neighbor, double weight) {
        this.base = base;
        this.neighbor = neighbor;
        this.weight = weight;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) { return true ; }
        if (!(obj instanceof Edge<?> o)) { return false; }

        //edges are equal in both direction
        return (this.base.equals(o.base) && this.neighbor.equals(o.neighbor)//check for one direct
                || this.neighbor.equals(o.base) && this.base.equals(o.neighbor))//check for second
                && this.weight == o.weight;
    }

    @Override
    public int hashCode() {
        return base.hashCode() + (int) weight + neighbor.hashCode();
    }

    @Override
    public int compareTo(Edge<T> other) {
        return (int) Math.round(weight - other.weight);
    }

    public T getBase() {
        return base;
    }

    public T getNeighbor() {
        return neighbor;
    }

}

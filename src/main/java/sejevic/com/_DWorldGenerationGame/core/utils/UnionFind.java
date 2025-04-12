package sejevic.com._DWorldGenerationGame.core.utils;

import java.util.HashMap;

public class UnionFind<T> {
    //key is a child and value is parent in a tree
    HashMap<T, T> parent = new HashMap<>();
    //key is a node in a tree, value is tree depth
    HashMap<T, Integer> depth = new HashMap<>();

    //At beginning every  item its own parent(no uni=ons made)
    public void addItem(T item) {
        this.parent.put(item, item);
        depth.put(item, 0);
    }

    //finds first parent of union (root of a tree)
    public T find(T item) {
        if (item == parent.get(item)) {//root is its own parent
            return item;
        }
        else {
            return find(parent.get(item));
        }
    }

    public boolean union(T item1, T item2) {
        T parent1 = find(item1);//finds item1 parent
        T parent2 = find(item2);//finds item2 parent

        if (parent1 == parent2) {//same parent means same union
            return false;
        }

        int depth1 = depth.get(parent1);//depth of first tree
        int depth2 = depth.get(parent2);//depth of second tree

        //tree with bigger depth becomes parent
        if (depth1 > depth2) {
            parent.put(parent2, parent1);
        }
        else if (depth1 < depth2) {
            parent.put(parent1, parent2);
        }
        else {//if equal depth root of tree 2 becomes parent of root of tree 1
            parent.put(parent1, parent2);
            depth.put(parent2, depth2 + 1);//increases depth by one
        }
        return true;
    }
}

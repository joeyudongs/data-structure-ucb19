public class UnionFind {

    // TODO - Add instance variables?
    private int[] parent;   // parent[i] = parent of i
    private int[] size;      // size[i] = number of sites in subtree rooted at i
    //private int count;      // number of components

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        //count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        int n = parent.length;
        if (vertex < 0 || vertex >= n) {
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        return size[v1];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
       int rootv1 = find(v1);
       int rootv2 = find(v2);
       if (rootv1 == rootv2) return;
       // make smaller root point to larger one
       if (sizeOf(rootv1) < sizeOf(rootv2)) {
           parent[rootv1] = rootv2;
           size[rootv2] += size[rootv1];
       } else {
           parent[rootv2] = rootv1;
           size[rootv1] += size[rootv2];
       }
       //count--;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
       validate(vertex);
       while (vertex != parent[vertex]) {
           vertex = parent[vertex];
       }
       return vertex;
    }
}

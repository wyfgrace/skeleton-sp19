import java.util.Random;

public class UnionFind {

    private int[] disjointSet;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        disjointSet = new int[n];
        for(int i=0; i<disjointSet.length; i++){
            disjointSet[i]=-1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex > disjointSet.length-1 || vertex < 0){
            throw new IllegalArgumentException("the index is not a valid index " + vertex);
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return -disjointSet[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return disjointSet[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        int i = find(v1);
        int j = find(v2);
        return i == j;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int i = find(v1);
        int j = find(v2);
        if (i != j){
            int sizeV1=-disjointSet[i];
            int sizeV2=-disjointSet[j];
            if (sizeV1 <= sizeV2){
                disjointSet[j]-=sizeV1;
                disjointSet[i]=j;
            }else{
                disjointSet[i]-=sizeV2;
                disjointSet[j]=i;
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        while(disjointSet[vertex] >=0){
            vertex = disjointSet[vertex];
        }
        return vertex;
    }

    public int findSteps(int vertex){
        validate(vertex);
        int steps=0;
        while (disjointSet[vertex] >= 0){
            vertex= disjointSet[vertex];
            steps++;
        }
        return steps;
    }


}

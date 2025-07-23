package graph.mst;

import java.util.ArrayList;

// number of connected components using disjoint set
public class NumberOfProvinces {
    int numOfProvinces(int V, int[][] adj) {
        DisjointSet ds = new DisjointSet(V);
        // adjacency matrix
        for (int i = 0; i <= V; i++) {
            for (int j = 0; j <= V; j++) {
                if (adj[i][j] == 1) {
                    // edge between i and j
                    ds.unionBySize(i, j);
                    // no need to do ds.unionBySize(j,i) as they will be discarded because of {if(ulp_v == ulp_u) return}
                    // as i and j are already connected
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i <= V; i++) {
            if (ds.findUPar(i) == i) {
                // ulp(0) is 0, ulp(6) is 6 and ulp(11) is 11
                cnt++;
            }
//            if(ds.parent.get(i) == i) cnt++;
        }
        return cnt;
    }

    private static int[][] edgesToAdjMatrix(int V, int[][] edges) {
        int[][] adj = new int[V][V];
        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            adj[u][v] = 1;
            adj[v][u] = 1;
        }
        return adj;
    }

    public static void main(String[] args) {
        int V = 13;
        int[][] edges = {{0, 2}, {1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 4}, {4, 5}, {6, 7}
                , {6, 8}, {8, 9}, {8, 10}, {11, 12}};
        int[][] adj = edgesToAdjMatrix(V, edges);
        NumberOfProvinces obj = new NumberOfProvinces();
        int numberOfComponent = obj.numOfProvinces(V-1, adj);
        System.out.println("Number of components: " + numberOfComponent);
    }
}

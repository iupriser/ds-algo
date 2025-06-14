package graph.shortestPathAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ShortestPathDAG {
    public int[] shortestPath(int V, ArrayList<ArrayList<PairWeighted>> adj, int src) {
        // run a DFS topo-sort for all connected components
        int[] vis = new int[V];
        Stack<Integer> stk = new Stack();
        //O(V+E)
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                topoSort(i, vis, adj, stk);
            }
        }
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e9);
        dist[src] = 0;

        // take the nodes out of stack and relax the edges
        //O(V+E)
        while (!stk.isEmpty()) {
            int node = stk.pop();
            for (PairWeighted it : adj.get(node)) {
                int v = it.v;
                int wt = it.wt;
                if (dist[v] > dist[node] + wt) {
                    dist[v] = dist[node] + wt;
                }
            }
        }
        for (int i = 0; i < V; i++) {
            if (dist[i] == 1e9) {
                dist[i] = -1;
            }
        }
        return dist;
    }

    public void topoSort(int node, int[] vis, ArrayList<ArrayList<PairWeighted>> adj, Stack<Integer> stk) {
        vis[node] = 1;
        for (PairWeighted it : adj.get(node)) {
            if (vis[it.v] == 0) {
                topoSort(it.v, vis, adj, stk);
            }
        }
        stk.push(node);
    }

    private static ArrayList<ArrayList<PairWeighted>> createAdjacencyList(int V,
                                                                          int[][] edges) {
        ArrayList<ArrayList<PairWeighted>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get(u).add(new PairWeighted(v, wt));
        }
        return adj;
    }

    public static void main(String[] args) {
        int V = 7;
        int[][] edges = {{0, 4, 2}, {0, 5, 3}, {5, 4, 1}, {4, 6, 3}, {4, 2, 1}, {6, 1, 2}, {2, 3
                , 3}, {1, 3, 1}};
        ArrayList<ArrayList<PairWeighted>> adj = createAdjacencyList(V, edges);
        int src = 0;
        ShortestPathDAG obj = new ShortestPathDAG();
        int[] shortestPath = obj.shortestPath(V, adj, src);
        System.out.println(Arrays.toString(shortestPath));
    }
}

class PairWeighted {
    int v;
    int wt;

    public PairWeighted(int v, int wt) {
        this.v = v;
        this.wt = wt;
    }
}
// use of record
//public record PairWeighted(int wt, int v) {}

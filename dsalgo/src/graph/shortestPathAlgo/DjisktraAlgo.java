package graph.shortestPathAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// TC: O(ElogV)
public class DjisktraAlgo {
    private int[] shortestPath(int V, ArrayList<ArrayList<PairWeighted>> adj, int src) {
        // min-heap
        PriorityQueue<PairWeighted> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a.wt));
        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.add(new PairWeighted(src, 0));

        while (!pq.isEmpty()) {
            int v = pq.peek().v;
            int wt = pq.peek().wt;
            pq.remove();
            for (PairWeighted adjNode : adj.get(v)) {
                if (dist[adjNode.v] > wt + adjNode.wt) {
                    dist[adjNode.v] = wt + adjNode.wt;
                    pq.add(new PairWeighted(adjNode.v, dist[adjNode.v]));
                }
            }
        }
        return dist;
    }

    private static ArrayList<ArrayList<PairWeighted>> createAdjacencyList(int V,
                                                                          int[][] edges) {
        ArrayList<ArrayList<PairWeighted>> adj = new ArrayList<>();
        for (int i = 0; i <=V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get(u).add(new PairWeighted(v, wt));
            adj.get(v).add(new PairWeighted(u, wt));
        }
        return adj;
    }

    public static void main(String[] args) {
        int V = 5;
        int[][] edges = {{0, 1, 4}, {0, 2, 4}, {1, 2, 2}, {2, 3, 3}, {2, 5, 6}, {2, 4,
                1}, {4, 5, 3}};
        ArrayList<ArrayList<PairWeighted>> adj = createAdjacencyList(V, edges);
        int src = 0;
        DjisktraAlgo obj = new DjisktraAlgo();
        int[] shortestPath = obj.shortestPath(V, adj, src);
        System.out.println(Arrays.toString(shortestPath));
    }
}
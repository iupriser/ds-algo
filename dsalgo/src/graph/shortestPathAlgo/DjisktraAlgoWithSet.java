package graph.shortestPathAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

//O((V + E) × logV)
public class DjisktraAlgoWithSet {
    private int[] shortestDist(int V, ArrayList<ArrayList<PairWithWeight>> adj,
                               int src) {
        // TreeSet to store vertices that are being preprocessed.
        // It stores pairs as {distance, vertex} and automatically keeps them sorted.
        TreeSet<PairWithWeight> set =
                new TreeSet<>(Comparator.comparingInt(PairWithWeight::wt)
                        .thenComparing(PairWithWeight::v));

        set.add(new PairWithWeight(src, 0));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        while (!set.isEmpty()) {
            PairWithWeight top = set.pollFirst();
            int u = top.v();

            for (PairWithWeight adjNode : adj.get(u)) {
                int v = adjNode.v();
                int wt = adjNode.wt();
                // If there is shorter path to v through u.
                if (dist[v] > dist[u] + wt) {
                     /* if there exist a path already with larger distance
                     {as we have already checked dist[v] > dist[u]+ wt},
                     remove it from set */
                    if (dist[v] != Integer.MAX_VALUE) {
                        // set.remove takes logarithm
                        set.remove(new PairWithWeight(dist[v], v));
                    }
                    // add the Pair with lesser distance
                    dist[v] = dist[u] + wt;
                    set.add(new PairWithWeight(dist[v], v));
                }
            }
        }
        return dist;
    }

    private static ArrayList<ArrayList<PairWithWeight>> createAdjacencyList(int V,
                                                                            int[][] edges) {
        ArrayList<ArrayList<PairWithWeight>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get(u).add(new PairWithWeight(wt, v));
            adj.get(v).add(new PairWithWeight(wt, u));
        }
        return adj;
    }

    public static void main(String[] args) {
        int V = 6;
        int[][] edges = {{0, 1, 4}, {0, 2, 4}, {1, 2, 2}, {2, 3, 3}, {2, 5, 6}, {2, 4,
                1}, {4, 5, 3}};
        ArrayList<ArrayList<PairWithWeight>> adj = createAdjacencyList(V,
                edges);
        int src = 0;
        DjisktraAlgoWithSet obj = new DjisktraAlgoWithSet();
        int[] shortestPath = obj.shortestDist(V, adj, src);
        System.out.println(Arrays.toString(shortestPath));
    }
}

record PairWithWeight(int wt, int v) {
}

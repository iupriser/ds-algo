package graph.shortestPathAlgo;

import java.util.*;

public class PrintShortestPath {
    private List<Integer> shortestPath(int V,
                                       ArrayList<ArrayList<PairWithWeight>> adj, int src) {
        // find the shortest path using Dijkstra algo
        PriorityQueue<PairWithWeight> pq =
                new PriorityQueue<>(Comparator.comparingInt(PairWithWeight::wt).thenComparing(PairWithWeight::v));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int[] parent = new int[V];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        dist[src] = 0;
        pq.add(new PairWithWeight(0, src));

        while (!pq.isEmpty()) {
            PairWithWeight top = pq.remove();
            int u = top.v();

            for (PairWithWeight adjNode : adj.get(u)) {
                int v = adjNode.v();
                int wt = adjNode.wt();
                if (dist[v] > dist[u] + wt) {
                    dist[v] = dist[u] + wt;
                    pq.add(new PairWithWeight(dist[v], v));
                    parent[v] = u;
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        int dest = V - 1;
        // if path not possible
        if (dist[dest] == Integer.MAX_VALUE) {
            path.add(-1);
            return path;
        }
        path.add(dest);
        // path from src to dest
        while (dest != src) {
            path.add(parent[dest]);
            dest = parent[dest];
        }

        Collections.reverse(path);
        return path;
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
        ArrayList<ArrayList<PairWithWeight>> adj = createAdjacencyList(V, edges);
        int src = 0;
        PrintShortestPath obj = new PrintShortestPath();
        List<Integer> shortestPath = obj.shortestPath(V, adj, src);
        System.out.println(shortestPath);
    }
}

package graph.shortestPathAlgo;

import java.util.Arrays;
import java.util.List;

public class BellmanFordAlgo {
    int[] bellmanFord(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e8);
        dist[src] = 0;
        // do the relaxation of all the edges for V-1 time
        for (int i = 0; i < V - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if (dist[u] != (int) 1e8 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }
//        Additionally, if the graph contains a negative weight cycle, return [-1]
//        to indicate that shortest paths cannot be reliably computed.

        // Nth relaxation to check negative cycle
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if (dist[u] != (int) 1e8 && dist[u] + wt < dist[v]) {
                return new int[]{-1};
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int V = 5, edges[][] = {{1, 3, 2}, {4, 3, -1}, {2, 4, 1}, {1, 2, 1}, {0, 1, 5}}, src = 0;
//        int V = 4, edges[][] = {{0, 1, 4}, {1, 2, -6}, {2, 3, 5}, {3, 1, -2}}, src = 0;
        BellmanFordAlgo obj = new BellmanFordAlgo();
        int[] minDist = obj.bellmanFord(V, edges, src);
        Arrays.stream(minDist).forEach(e -> System.out.print(e + " "));
    }
}

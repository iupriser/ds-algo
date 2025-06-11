package graph.shortestPathAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathUG {
    public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
        int n = adj.size();
        int[] shortestPath = new int[n];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        shortestPath[src] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int it : adj.get(node)) {
                if (shortestPath[it] > (shortestPath[node] + 1)) {
                    shortestPath[it] = shortestPath[node] + 1;
                    q.add(it);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (shortestPath[i] == Integer.MAX_VALUE) {
                shortestPath[i] = -1;
            }
        }
        return shortestPath;
    }

    public static void main(String[] args) {
        int V = 9;
        int[][] edges = {{0, 1}, {0, 3}, {1, 2}, {1, 3}, {3, 4}, {4, 5}, {5, 6}, {2, 6}, {6, 7}, {7, 8}, {6, 8}};
        ArrayList<ArrayList<Integer>> adj = createAdjacencyList(V, edges);
        int src = 0;
        ShortestPathUG obj = new ShortestPathUG();
        int[] shortestPath = obj.shortestPath(adj, src);
        System.out.println(Arrays.toString(shortestPath));
    }

    private static ArrayList<ArrayList<Integer>> createAdjacencyList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return adj;
    }
}
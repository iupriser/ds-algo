package graph.bfs_dfs;

import graph.basics.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// TC: O(N+2E) + O(N)
// SC: O(N) + O(N)
public class DetectCycleUndirectedBFS {
    public boolean isCycle(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = constructAdjList(V, edges);
        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (checkForCycle(i, adj, vis)) return true;
            }
        }
        return false;
    }

    public boolean checkForCycle(int src, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        // {vertex, parentOfVertex}
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(src, -1));
        vis[src] = true;

        while (!q.isEmpty()) {
            int node = q.peek().first;
            int parent = q.peek().second;
            q.remove();

            for (int adjNode : adj.get(node)) {
                if (!vis[adjNode]) {
                    vis[adjNode] = true;
                    q.add(new Pair(adjNode, node));
                }
                // if adjacent node is visited and is not its own parent node
                else if (parent != adjNode) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<ArrayList<Integer>> constructAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        return adj;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {2, 3}};
        int V = 4;
        DetectCycleUndirectedBFS obj = new DetectCycleUndirectedBFS();
        boolean isCyclePresent = obj.isCycle(4, edges);
        System.out.println("is cycle present : " + isCyclePresent);
    }
}

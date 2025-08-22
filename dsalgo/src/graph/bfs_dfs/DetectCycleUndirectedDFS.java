package graph.bfs_dfs;

import java.util.ArrayList;

// TC: O(N+2E) + O(N)
// SC: O(N) + O(N)
public class DetectCycleUndirectedDFS {
    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {1, 4}, {4, 6}, {2, 3}, {2, 5}, {5, 6}};
        int V = 7;
        DetectCycleUndirectedDFS obj = new DetectCycleUndirectedDFS();
        boolean isCyclePresent = obj.isCycle(V, edges);
        System.out.println("is cycle present : " + isCyclePresent);
    }

    public boolean isCycle(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = constructAdjList(V, edges);
        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (checkForCycle(i, -1, adj, vis)) return true;
            }
        }
        return false;
    }

    public boolean checkForCycle(int node, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[node] = true;
        for (int adjNode : adj.get(node)) {
            if (!vis[adjNode]) {
                if (checkForCycle(adjNode, node, adj, vis)) {
                    return true;
                }
            }
            // if adjacent node is visited and is not its own parent node
            else if (adjNode != parent) {
                return true;
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
}

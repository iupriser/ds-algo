package graph.otherAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//https://leetcode.com/problems/critical-connections-in-a-network/
//https://www.geeksforgeeks.org/dsa/bridge-in-a-graph/

/**
 * Bridges in graph - A bridge (or cut-edge) is an edge in a undirected-graph whose removal increases the number of connected components.
 * Tarjan's Algorithm: This algorithm uses a single DFS and a stack.
 * It's generally preferred for its efficiency as it only requires one pass through the graph.
 * It uses discovery times and low-link values for each vertex to identify the roots of
 * the DFS trees, which are the representatives of the SCCs
 */
public class TarjanAlgo {
    private int timer = 1;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> adj) {
        int[] vis = new int[n + 1];
        int[] tin = new int[n + 1];
        int[] low = new int[n + 1];
        List<List<Integer>> bridges = new ArrayList<>();
        dfs(1, -1, vis, adj, tin, low, bridges);
        return bridges;
    }

    private void dfs(int currNode, int parent, int[] vis, List<List<Integer>> adj, int[] tin, int[] low, List<List<Integer>> bridges) {
        vis[currNode] = 1;
        tin[currNode] = low[currNode] = timer;
        timer++;
        for (Integer adjNode : adj.get(currNode)) {
            if (adjNode == parent) continue;
            if (vis[adjNode] == 0) {
                // currNode will be parent for adjNode
                dfs(adjNode, currNode, vis, adj, tin, low, bridges);
                low[currNode] = Math.min(low[currNode], low[adjNode]);
                // can there be a bridge between currNode----adjNode
                if (low[adjNode] > tin[currNode]) {
                    bridges.add(Arrays.asList(adjNode, currNode));
                }
            } else {
                low[currNode] = Math.min(low[currNode], low[adjNode]);
            }
        }
    }

    public static void main(String[] args) {
        int V = 12; // 1-12
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 1}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 6}, {8, 10}, {10, 11}, {10, 12}, {11, 12}};
        List<List<Integer>> adj = adjListFromEdgesUndirected(V, edges);
        TarjanAlgo obj = new TarjanAlgo();
        List<List<Integer>> bridges = obj.criticalConnections(V, adj);
        bridges.forEach(it -> System.out.println(it + " "));
        System.out.println("---------------------");
        TarganAlgoImplV2 obj2 = new TarganAlgoImplV2();
        bridges = obj2.findBridges(V, adj);
        bridges.forEach(it -> System.out.println(it + " "));

    }

    private static List<List<Integer>> adjListFromEdgesUndirected(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }
        // un-directed graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return adj;
    }
}

class TarganAlgoImplV2 {
    private int time;
    private int[] disc;
    private int[] low;
    private List<List<Integer>> bridges;
    private List<List<Integer>> adj;

    public List<List<Integer>> findBridges(int n, List<List<Integer>> adj) {
        this.adj = adj;
        this.time = 0;
        // discovery time, disc[u]=time at which node u is first visited
        this.disc = new int[n+1];// DFS time insertion
        // low-link value, low[u] = the lowest discovery time reachable from u(including u itself)
        // through dfs subtree and at-most one back-edge
        this.low = new int[n+1];// lowest time of insertion of all adj nodes apart from parent
        this.bridges = new ArrayList<>();

        /**
         *          The key condition for an edge (u, v) to be a bridge is: low[v] > disc[u]
         *          This means that the lowest reachable discovery time from v (and its entire subtree) is still greater than
         *          the discovery time of u. Therefore, there is no back-edge from v's subtree that can reach u or any
         *          of its ancestors, making the edge (u, v) a critical link.
         */

        // Initialize discovery time to -1 (unvisited)
        Arrays.fill(disc, -1);
        // Iterate through all nodes to handle disconnected graphs
        for (int i = 0; i <= n; i++) {
            if (disc[i] == -1) {
                dfs(i, -1); // -1 indicates no parent for the root of the DFS tree
            }
        }
        return bridges;
    }

    private void dfs(int u, int parent) {
        disc[u] = low[u] = time++;
        for (int v : adj.get(u)) {
            // Don't traverse back to immediate parent
            if (v == parent) continue;

            if (disc[v] == -1) {
                // v is unvisited, traverse its subtree
                dfs(v, u);
                // On return, update low[u] with low[v]
                // this accounts for back-edges found in v's subtree
                // step that propagates the lowest reachable discovery time from v's subtree up to u.
                low[u] = Math.min(low[u], low[v]);

                // Condition for bridge:
                if (low[v] > disc[u]) {
                    bridges.add(Arrays.asList(u, v));
                }
            } else {
                // v is already visited, it is a back-edge from u to an ancestor v
                // update low[u] with the discovery time of v
                // to account for this back-edge
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
}

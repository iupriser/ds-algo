package graph.otherAlgo;
// used to find Strongly Connected Component(SCC)

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Strongly Connected Components exists for directed graph
 * t's a subgraph where every vertex is reachable from every other vertex within that subgraph.
 * In simpler terms, if you pick any two vertices, u and v, in an SCC,
 * there's always a path from u to v and a path from v to u.
 * <p>
 * Kosaraju's Algorithm: This algorithm uses two depth-first searches (DFS).
 * The first DFS is performed on the original graph to find a finishing order of vertices.
 * The second DFS is then performed on the transpose graph
 * (a graph with all edge directions reversed) in the reverse order of the
 * first DFS's finishing times. Each tree in the second DFS traversal corresponds
 * to a single SCC
 */
public class KosarajuAlgo {

    public List<List<Integer>> findSCCs(int n, List<List<Integer>> adj) {
        // Phase 1: DFS and fill stack
        Stack<Integer> stk = new Stack<>();
        boolean[] vis = new boolean[n];
        // O(V+E)
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs1(i, adj, vis, stk);
            }
        }
        // Phase 2: Create transpose graph, reverse the graph
        List<List<Integer>> adjTranspose = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjTranspose.add(new ArrayList<>());
        }
        // O(V+E)
        for (int i = 0; i < n; i++) {
            for (int neighbour : adj.get(i)) {
                adjTranspose.get(neighbour).add(i);
            }
        }

        // Phase 3: Second DFS on transpose graph
        List<List<Integer>> sccs = new ArrayList<>();
        vis = new boolean[n];
        // O(V+E)
        while (!stk.isEmpty()) {
            int node = stk.pop();
            if (!vis[node]) {
                List<Integer> currentScc = new ArrayList<>();
                dfs2(node, adjTranspose, vis, currentScc);
                sccs.add(currentScc);
            }
        }

        return sccs;
    }

    public void dfs1(int node, List<List<Integer>> adj, boolean[] vis, Stack<Integer> stk) {
        vis[node] = true;
        for (int neighbour : adj.get(node)) {
            if (!vis[neighbour]) {
                dfs1(neighbour, adj, vis, stk);
            }
        }
        stk.push(node);
    }

    private void dfs2(int node, List<List<Integer>> adjTranspose, boolean[] vis, List<Integer> currentScc) {
        vis[node] = true;
        currentScc.add(node);
        for (int neighbour : adjTranspose.get(node)) {
            if (!vis[neighbour]) {
                dfs2(neighbour, adjTranspose, vis, currentScc);
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 5}, {4, 7}, {5, 6}, {6, 4}, {6, 7}};
        int V = 8;
        List<List<Integer>> adj = adjListFromEdgesDirected(V, edges);
        KosarajuAlgo obj = new KosarajuAlgo();
        List<List<Integer>> numOfSCC = obj.findSCCs(V, adj);
        System.out.println("Number of Strongly Connected Components: " + numOfSCC.size());
        System.out.println("Strongly Connected Components: " + numOfSCC);
    }

    private static List<List<Integer>> adjListFromEdgesDirected(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        // directed graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
        }
        return adj;
    }
}
// SCC is found when disc[u] == low[u] for node u.
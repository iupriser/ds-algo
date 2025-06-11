package graph.toposort;

import java.util.ArrayList;
import java.util.Stack;

public class TopologicalSort {
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = createAdjList(V, edges);
        int[] vis = new int[V];
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfs(i, vis, adj, stk);
            }
        }
        ArrayList<Integer> topo = new ArrayList<>();
        while (!stk.isEmpty()) {
            topo.add(stk.pop());
        }
        return topo;
    }

    public void dfs(int node, int[] vis, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stk) {
        vis[node] = 1;
        for (int it : adj.get(node)) {
            if (vis[it] == 0) {
                dfs(it, vis, adj, stk);
            }
        }
        stk.push(node);
    }

    public ArrayList<ArrayList<Integer>> createAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }
        return adj;
    }

    public static void main(String[] args) {
        int edges[][] = {{1, 3}, {2, 3}, {4, 1}, {4, 0}, {5, 0}, {5, 2}};
        int V = 6;
        TopologicalSort obj = new TopologicalSort();
        ArrayList<Integer> topoSort = obj.topoSort(V, edges);
        System.out.println(topoSort);
    }
}

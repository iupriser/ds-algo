package graph;

//https://www.geeksforgeeks.org/problems/connected-components-in-an-undirected-graph/1

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectedComponentTwo {
    public ArrayList<ArrayList<Integer>> getComponents(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = createAdjList(V, edges);
        ArrayList<ArrayList<Integer>> connectedComponent = new ArrayList<>();
        // 0-based vertices
        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                connectedComponent.add(bfs(i, vis, adj));
            }
        }
        return connectedComponent;
    }

    public ArrayList<ArrayList<Integer>> createAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        return adj;
    }

    public ArrayList<Integer> bfs(int srcNode, boolean[] vis,
                                  ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> bfsOfGraph = new ArrayList<>();
        q.add(srcNode);
        vis[srcNode] = true;

        while (!q.isEmpty()) {
            Integer node = q.poll();
            bfsOfGraph.add(node);

            for (Integer it : adj.get(node)) {
                if (!vis[it]) {
                    vis[it] = true;
                    q.add(it);
                }
            }
        }
        return bfsOfGraph;
    }
}

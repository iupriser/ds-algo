package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Traversal {
    // bfs => level order traversal of graph {distance from source node}
    // use of DS : Queue and visited array
    // TC: 0(V+2E), SC: O(3V)
    public static ArrayList<Integer> bfs(int srcNode, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        ArrayList<Integer> bfsOfGraph = new ArrayList<>();
        // 1-based graph
        Queue<Integer> q = new LinkedList<>();
        // initial configuration
        q.add(srcNode);
        vis[srcNode] = true;

        while (!q.isEmpty()) {
            Integer node = q.poll();
            bfsOfGraph.add(node);
            // get all the adjacent vertices of dequed node
            for (Integer it : adj.get(node)) {
                // if neighbour of node is not visited
                if (!vis[it]) {
                    vis[it] = true;
                    q.add(it);
                }
            }
        }

        return bfsOfGraph;
    }

    // dfs => depth traversal of graph {recursion}
    // TC: 0(V+2E), SC: O(3V)
    // TC: O(V+E) for directed graph
    public static ArrayList<Integer> dfs(int srcNode, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        ArrayList<Integer> dfsOfGraph = new ArrayList<>();
        dfsHelper(srcNode, adj, vis, dfsOfGraph);
        return dfsOfGraph;
    }

    private static void dfsHelper(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis, ArrayList<Integer> ds) {
        vis[node] = true;
        ds.add(node);
        for (Integer it : adj.get(node)) {
            if (!vis[it]) {
                dfsHelper(it, adj, vis, ds);
            }
        }
    }
}

package graph.basics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Traversal {
    /**
     * bfs => level order traversal of graph {distance from source node}
     * use of DS : Queue(FIFO) which contains srcNode and visited array
     * we start with srcNode, marked as visited, and pushed to queue ds
     * TC: 0(V+2E), SC: O(3V)
     */

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

    /**
     * idea of recursion and backtracking. DFS goes in-depth, i.e., traverses all nodes by going ahead, and when
     * there are no further nodes to traverse in the current path, then it backtracks on the same path and traverses other unvisited nodes
     * dfs => depth traversal of graph {recursion}
     * TC: 0(V+2E), SC: O(3V)
     * TC: O(V+E) for directed graph
     */
    public static ArrayList<Integer> dfs(int srcNode, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        ArrayList<Integer> dfsOfGraph = new ArrayList<>();
        dfsHelper(srcNode, adj, vis, dfsOfGraph);
        return dfsOfGraph;
    }

    private static void dfsHelper(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis, ArrayList<Integer> ds) {
        vis[node] = true;
        ds.add(node);
        for (Integer it : adj.get(node)) {
            // if adj node is vis or not, if not vis, then only go into depth
            if (!vis[it]) {
                dfsHelper(it, adj, vis, ds);
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {2, 5}, {2, 6}, {1, 3}, {3, 4}, {3, 7}, {7, 8}, {4, 8}};
        int V = 8;
        ArrayList<ArrayList<Integer>> adj = new GraphRepresentation().createAdjListFromEdge(V, edges);
        int srcNode = 1;
        boolean[] vis = new boolean[V + 1];
        ArrayList<Integer> dfsOfGraph = dfs(srcNode, adj, vis);
        dfsOfGraph.forEach(a -> System.out.print(a + " "));
        System.out.println("\n-----------------------------");
        edges= new int[][]{{1, 2}, {1, 5}, {2, 5}, {2, 3}, {3, 4}, {3, 5}, {4, 5}};
        V = 5;
        adj = new GraphRepresentation().createAdjListFromEdge(V, edges);
        srcNode = 1;
        vis = new boolean[V + 1];
        ArrayList<Integer> bfsOfGraph = bfs(srcNode, adj, vis);
        bfsOfGraph.forEach(a -> System.out.print(a + " "));
    }
}

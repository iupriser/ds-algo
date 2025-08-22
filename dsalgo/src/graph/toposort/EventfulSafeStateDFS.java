package graph.toposort;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/find-eventual-safe-states/
public class EventfulSafeStateDFS {
    /**
     * anyone who is a part of cycle or anyone connected to cycle are not
     * safe states
     */
    List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        int[] vis = new int[V];
        int[] pathVis = new int[V];
        int[] check = new int[V];
        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfsHelper(i, adj, vis, pathVis, check);
            }
        }
        for (int i = 0; i < V; i++) {
            if (check[i] == 1) {
                safeNodes.add(i);
            }
        }
        return safeNodes;
    }

    private boolean dfsHelper(int node, List<List<Integer>> adj, int[] vis,
                              int[] pathVis, int[] check) {
        vis[node] = 1;
        pathVis[node] = 1;
        // either here, or need to define separately on line 40 and 46
        check[node] = 0;
        // visit its neighbour
        for (int neighbour : adj.get(node)) {
            if (vis[neighbour] == 0) {
                if (dfsHelper(neighbour, adj, vis, pathVis, check) == true) {
//                    check[node] = 0;
                    return true;
                }
            }
            // if neighbour is already visited on the same path, cycle exists
            else if (pathVis[neighbour] == 1) {
//                check[node] = 0;
                return true;
            }
        }
        // if there exist a cycle, then it will break and return in for loop
        // itself, coming to this point means that there exist no cycle and
        // node is safe state
        check[node] = 1;
        pathVis[node] = 0;
        return false;
    }

    static List<List<Integer>> createAdjList(int V, int[][] graph) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : graph) {
            adj.get(edge[0]).add(edge[1]);
        }
        return adj;
    }

    public static void main(String[] args) {
        int[][] graph = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 6}, {3, 5}, {5, 6}, {6, 7},
                {8, 1}, {8, 9}, {9, 10}, {10, 8}, {11, 9}};
        int V = 12;
        List<List<Integer>> adjList = createAdjList(V, graph);
        EventfulSafeStateDFS obj = new EventfulSafeStateDFS();
        List<Integer> safeNodes = obj.eventualSafeNodes(V, adjList);
        System.out.println(safeNodes);
    }
}

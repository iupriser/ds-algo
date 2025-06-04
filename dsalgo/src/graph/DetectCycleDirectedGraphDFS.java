package graph;

import java.util.ArrayList;

public class DetectCycleDirectedGraphDFS {
    public boolean isCyclic(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = createAdjacencyList(edges, V);
        int[] vis = new int[V];
        int[] pathVis = new int[V];

        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
//                if (dfsCheck(i, adj, vis, pathVis) == true) {
                if (dfsCheckSpaceOptimized(i, adj, vis) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfsCheck(int node, ArrayList<ArrayList<Integer>> adj, int[] vis,
                             int[] pathVis) {
        vis[node] = 1;
        pathVis[node] = 1;
        // visit its neighbours
        for (int neighbour : adj.get(node)) {
            if (vis[neighbour] == 0) {
                if (dfsCheck(neighbour, adj, vis, pathVis) == true) {
                    return true;
                }
            }
            // if the node has been previously visited but it has be visited
            // on the same path
            else if (pathVis[neighbour] == 1) {
                return true;
            }
        }
        // as we are returning, then that means we don't have any cycle
        pathVis[node] = 0;
        return false;
    }

    private boolean dfsCheckSpaceOptimized(int node,
                                           ArrayList<ArrayList<Integer>> adj,
                                           int[] vis) {
        vis[node] = 1;
        // visit its neighbours
        for (int neighbour : adj.get(node)) {
            if (vis[neighbour] == 0) {
                if (dfsCheckSpaceOptimized(neighbour, adj, vis) == true) {
                    return true;
                }
            }
            // if the node has been previously visited but it has be visited
            // on the same path
            else if (vis[neighbour] == 1) {
                return true;
            }
        }
        // as we are returning, then that means we don't have any cycle
        vis[node] = 2;
        return false;
    }

    private ArrayList<ArrayList<Integer>> createAdjacencyList(int[][] edges, int V) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
        }
        return adjList;
    }

    public static void main(String[] args) {
        int V = 4;
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {2, 0}, {2, 3}};
        DetectCycleDirectedGraphDFS obj = new DetectCycleDirectedGraphDFS();
        boolean isCyclic = obj.isCyclic(V, edges);
        System.out.println("Is directed graph is cyclic: " + isCyclic);
    }
}

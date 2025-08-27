package graph.otherAlgo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * articulation point -> nodes on whose removal, the graph breaks
 * into multiple components
 */
public class ArticulationPointInGraph {

    // Standard DFS to mark all reachable nodes
    private void dfs(int node,
                     ArrayList<ArrayList<Integer>> adj, boolean[] visited) {

        visited[node] = true;

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited);
            }
        }
    }

    // Builds adjacency list from edge list
    private ArrayList<ArrayList<Integer>> constructadj(int V, int[][] edges) {
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

    private int timer = 1;

    // Finds articulation points using naive DFS approach - TC: O(V * ( V + E ))
    public ArrayList<Integer> articulationPointsOptimized(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = constructadj(V, edges);
        ArrayList<Integer> ans = new ArrayList<>();

        int[] low = new int[V];
        int[] tin = new int[V];
        int[] vis = new int[V];
        // same node can be articulation point for different path, to avoid printing same node multiple times
        int[] mark = new int[V];
        // Run DFS from each vertex if not already visited (to handle disconnected graphs)
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfs(i, -1, vis, tin, low, mark, adj);
            }
        }
        for (int i = 0; i < V; i++) {
            if (mark[i] == 1) {
                ans.add(i);
            }
        }
        if (ans.isEmpty()) {
            ans.add(-1);
        }
        return ans;
    }

    private void dfs(int currNode, int parent, int[] vis, int[] tin, int[] low, int[] mark, ArrayList<ArrayList<Integer>> adj) {
        vis[currNode] = 1;
        tin[currNode] = low[currNode] = timer;
        timer++;
        int child = 0;
        for (Integer adjNode : adj.get(currNode)) {
            if (adjNode == parent) continue;
            if (vis[adjNode] == 0) {
                dfs(adjNode, currNode, vis, tin, low, mark, adj);
                low[currNode] = Math.min(low[currNode], low[adjNode]);
                // currNode---adjNode
                // if adjNode cannot be reached to currNode and currNode is not the first node
                // as we are done with dfs(currNode), check whether currNode be articulation point ?
                if (low[adjNode] >= tin[currNode] && parent != -1) {
                    mark[currNode] = 1;
                }
                child++;
            } else {
                low[currNode] = Math.min(low[currNode], tin[adjNode]);
            }
        }
        // child is greater than 1, and it is starting point, then definitely an articulation point
        if (child > 1 && parent == -1) {
            mark[currNode] = 1;
        }
    }

    // Finds articulation points using naive DFS approach - TC: O(V * ( V + E ))
    public ArrayList<Integer> articulationPointsBrute(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = constructadj(V, edges);
        ArrayList<Integer> res = new ArrayList<>();

        // Try removing each node one by one
        for (int i = 0; i < V; ++i) {
            boolean[] visited = new boolean[V];
            visited[i] = true;

            // count DFS calls from i's neighbors
            int comp = 0;
            for (int it : adj.get(i)) {

                // early stop if already more than 1 component
                if (comp > 1) break;

                if (!visited[it]) {

                    // explore connected part
                    dfs(it, adj, visited);
                    comp++;
                }
            }

            // if more than one component forms, it's an articulation point
            if (comp > 1)
                res.add(i);
        }

        if (res.isEmpty())
            return new ArrayList<>(Arrays.asList(-1));

        return res;
    }

    public static void main(String[] args) {
        int V = 5;
        int[][] edges = {{0, 1}, {1, 4}, {2, 3}, {2, 4}, {3, 4}};
        ArticulationPointInGraph obj = new ArticulationPointInGraph();
        ArrayList<Integer> ans = obj.articulationPointsBrute(V, edges);
        for (int it : ans) {
            System.out.print(it + " ");
        }

        System.out.println("\n------------");
        ans = obj.articulationPointsOptimized(V, edges);
        for (int it : ans) {
            System.out.print(it + " ");
        }
    }
}

package graph;
//https://takeuforward.org/plus/dsa/problems/connected-components

import java.util.ArrayList;

import static graph.GraphRepresentation.createAdjListFromEdge;

/**
 * A connected component is a subgraph of a graph in which there exists a path
 * between any two vertices, and no vertex of the subgraph shares an edge with
 * a vertex outside of the subgraph.
 */
public class ConnectedComponent {
    public static void main(String[] args) {
        // vertices numbered from 1 to V
        // graph is represented ny adjacency list
        int[][] edges = {{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 4}, {4, 5}, {6, 7}
                , {6, 8}, {8, 9}, {8, 10}, {11, 12}};
        int numberOfComponent = findNumberOfComponent(edges.length, 13, edges);
        System.out.println("Number of components: " + numberOfComponent);
    }

    public static int findNumberOfComponent(int E, int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = createAdjListFromEdge(V, edges);
        int count = 0;
        // 1-based vertices
        boolean[] vis = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            // if node is not visited
            if (!vis[i]) {
                Traversal.bfs(i, adj, vis);
                count++;
            }
        }
        return count;
    }
}
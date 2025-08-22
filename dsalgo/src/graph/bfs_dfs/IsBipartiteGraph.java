package graph.bfs_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/is-graph-bipartite/description/
public class IsBipartiteGraph {
    // extra check for connected component
    public boolean isBipartite(int[][] edges, int V) {
        int[] color = new int[V];
        Arrays.fill(color, -1);
        ArrayList<ArrayList<Integer>> adj = createAdjacencyList(edges, V);
        for (int src = 0; src < V; src++) {
            if (color[src] == -1) {
//                if (checkByBfs(src, adj, color) == false)
                if (checkByDfs(src, adj, color, 0) == false)
                    return false;
            }
        }
        return true;
    }

    public boolean checkByBfs(int src, ArrayList<ArrayList<Integer>> adj,
                              int[] color) {

        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        color[src] = 0; // color '0'

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int neighbour : adj.get(node)) {
                // if adjacent node yet not colored
                if (color[neighbour] == -1) {
                    // if adjacent node has color 0, color of neighbour is 1
                    // and vice versa
                    color[neighbour] = color[node] == 0 ? 1 : 0;
                    q.add(neighbour);
                }
                // if neighbour node is already colored/visited
                else {
                    if (color[neighbour] == color[node]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkByDfs(int node, ArrayList<ArrayList<Integer>> adj,
                              int[] color, int nodeColor) {
        color[node] = nodeColor;
        for (int neighbour : adj.get(node)) {
            // if adjacent node yet not colored
            if (color[neighbour] == -1) {
                int newColor = color[neighbour] == 0 ? 1 : 0;
                if (checkByDfs(neighbour, adj, color, newColor) == false)
                    return false;
            } else {
                if (color[neighbour] == color[node]) {
                    return false;
                }
            }
        }
        return true;
    }

    private ArrayList<ArrayList<Integer>> createAdjacencyList(int[][] edges,
                                                              int V) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

//        changes for Leetcode question
//        for (int i = 0; i < V; i++) {
//            for (int j = 0; j < edges[i].length; j++) {
//                adjList.get(i).add(edges[i][j]);
//            }
//        }
        return adjList;
    }

    public static void main(String[] args) {
//        int[][] graph = {{0, 3}, {1, 2}, {3, 2}, {0, 2}};
        int[][] graph = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        int V = 4; // 0 to 3
        IsBipartiteGraph obj = new IsBipartiteGraph();
        boolean isBipartite = obj.isBipartite(graph, V);
        System.out.println("Is graph is bipartite: " + isBipartite);

    }
}

class BFSSolution {
    public boolean isBipartite(int[][] gr) {
        int n = gr.length;
        int[] colour = new int[n];

        for (int node = 0; node < n; node++) {
            if (colour[node] != 0) {
                continue;
            }

            Queue<Integer> q = new LinkedList<>();
            q.add(node);
            colour[node] = 1;

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int ne : gr[cur]) {
                    if (colour[ne] == 0) {
                        colour[ne] = -colour[cur];
                        q.add(ne);
                    } else if (colour[ne] != -colour[cur]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}

class DFSSolution {
    public boolean validColouring(int[][] gr, int[] colour, int node, int col) {
        if (colour[node] != 0)
            return colour[node] == col;

        colour[node] = col;
        for (int ne : gr[node]) {
            if (!validColouring(gr, colour, ne, -col))
                return false;
        }

        return true;
    }

    public boolean isBipartite(int[][] gr) {
        int n = gr.length;
        int[] colour = new int[n];

        for (int node = 0; node < n; node++) {
            if (colour[node] == 0 && !validColouring(gr, colour, node, 1))
                return false;
        }

        return true;
    }
}

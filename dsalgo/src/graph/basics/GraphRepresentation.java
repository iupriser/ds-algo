package graph.basics;

import java.util.ArrayList;

public class GraphRepresentation {
    public static void main(String[] args) {
        GraphRepresentation obj = new GraphRepresentation();
        obj.graphReprAdjacencyMatrix();
        obj.graphReprAdjacencyList();
//        printAdjacenyMatrix();
//        printAdjacenyList();
    }

    // To add an edge in an undirected graph
    public void addEdge(ArrayList<ArrayList<Integer>> adj, int s, int t) {
        adj.get(s).add(t);
        adj.get(t).add(s);
    }

    public ArrayList<ArrayList<Integer>> createAdjListFromEdge(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        // Initialize 1-based adjacency list
        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges
        for (int[] e : edges) {
            addEdge(adj, e[0], e[1]);
        }

        return adj;
    }

    // edges to adjList
    public ArrayList<ArrayList<Integer>> edgesToAdjList(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }
        return adj;
    }

    public void printAdjacenyList(int n, ArrayList<ArrayList<Integer>> adj) {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < adj.get(i).size(); i++) {
                System.out.print(adj.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }


    public void graphReprAdjacencyMatrix() {
        int n = 3, m = 3;
        int adj[][] = new int[n + 1][m + 1];

        adj[1][2] = 1;
        adj[2][1] = 1;

        adj[2][3] = 1;
        adj[3][2] = 1;

        adj[1][3] = 1;
        adj[3][1] = 1;
    }

    public void graphReprAdjacencyList() {
        int n = 3, m = 3;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        // add n+1 arrayList
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(2);
        adj.get(2).add(1);

        adj.get(2).add(3);
        adj.get(3).add(2);

        adj.get(1).add(3);
        adj.get(3).add(1);
    }

    public void graphReprAdjacencyListWeighted() {
        int n = 3, m = 3;
        // instead of neighbour, store (Neighbour, Weight)
        ArrayList<ArrayList<WeightedPair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(new WeightedPair(2, 5));
        adj.get(2).add(new WeightedPair(1, 5));

        adj.get(2).add(new WeightedPair(3, 6));
        adj.get(3).add(new WeightedPair(2, 6));

        adj.get(1).add(new WeightedPair(3, 7));
        adj.get(3).add(new WeightedPair(1, 7));

    }
}

class WeightedPair {
    int neighbour;
    int weight;

    public WeightedPair(int neighbour, int weight) {
        this.neighbour = neighbour;
        this.weight = weight;
    }
}

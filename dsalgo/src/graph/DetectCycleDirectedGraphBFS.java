package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// usuage of Kahn algo
public class DetectCycleDirectedGraphBFS {
    public boolean isCyclic(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = createAdjacencyList(edges, V);
        //build graph with indegree
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int neighbour : adj.get(i)) {
                indegree[neighbour]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        int index = 0;
        // O(V+E)
        while (!q.isEmpty()) {
            int node = q.poll();
            index++;
            for (int neighbour : adj.get(node)) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) {
                    q.add(neighbour);
                }
            }
        }
        // contains cycle
        if (index != V) {
            return true;
        }
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
        DetectCycleDirectedGraphBFS obj = new DetectCycleDirectedGraphBFS();
        boolean isCyclic = obj.isCyclic(V, edges);
        System.out.println("Is directed graph is cyclic: " + isCyclic);
    }
}

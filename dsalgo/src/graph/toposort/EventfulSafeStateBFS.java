package graph.toposort;

import java.util.*;

//https://leetcode.com/problems/find-eventual-safe-states/
public class EventfulSafeStateBFS {
    /**
     * anyone who is a part of cycle or anyone connected to cycle are not
     * safe states
     */
    List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        // need to reverse the graph
        List<List<Integer>> adjRev = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjRev.add(new ArrayList<>());
        }
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            // edge between i->it
            for (int it : adj.get(i)) {
                // convert it to it->i
                adjRev.get(it).add(i);
                indegree[i]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        // figure out terminal nodes, and add it to queue
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        List<Integer> safeNodes = new ArrayList<>();

        while (!q.isEmpty()) {
            int node = q.poll();
            safeNodes.add(node);
            for (int it : adjRev.get(node)) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.add(it);
                }
            }
        }

        Collections.sort(safeNodes);
        return safeNodes;
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
        EventfulSafeStateBFS obj = new EventfulSafeStateBFS();
        List<Integer> safeNodes = obj.eventualSafeNodes(V, adjList);
        System.out.println(safeNodes);
    }
}

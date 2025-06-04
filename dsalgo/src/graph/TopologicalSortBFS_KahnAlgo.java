package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class TopologicalSortBFS_KahnAlgo {
    public ArrayList<Integer> topoSortKahnAlgo(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = createAdjList(V, edges);

        // calculate the indegree
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }
        int[] topo = new int[V];
        int index = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            topo[index++] = node;
            // node is in topo sort array, remove it from indegree
            for (int it : adj.get(node)) {
                inDegree[it]--;
                if (inDegree[it] == 0) {
                    q.add(it);
                }
            }
        }
        // Check for cycle
        if (index != V) {
            System.out.println("Graph contains a cycle!");
            return new ArrayList<>();
        }
        return (ArrayList<Integer>) Arrays.stream(topo).boxed().collect(Collectors.toList());
    }

    public ArrayList<ArrayList<Integer>> createAdjList(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }
        return adj;
    }

    public static void main(String[] args) {
        int edges[][] = {{5, 0}, {5, 2}, {4, 1}, {4, 0}, {2, 3}, {3, 1}};
        int V = 6;
        TopologicalSortBFS_KahnAlgo obj = new TopologicalSortBFS_KahnAlgo();
        ArrayList<Integer> topoSort = obj.topoSortKahnAlgo(V, edges);
        System.out.println(topoSort);
    }
}

package graph;

import java.util.ArrayList;

import static graph.Traversal.bfs;

public class NumberOfProvinces {
    public static void main(String[] args) {
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        NumberOfProvinces obj = new NumberOfProvinces();
        int num = obj.findCircleNum(isConnected);
        System.out.println("Number of provinces: " + num);
    }

    public int findCircleNum(int[][] isConnected) {
        int V = isConnected.length;
        // create a adjacency list from isConnected edge matrix
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j && isConnected[i][j] == 1) {
                    adj.get(i).add(j);
                }
            }
        }
        boolean[] isVis = new boolean[V];
        int numOfProvinces = 0;
        for (int i = 0; i < V; i++) {
            if (!isVis[i]) {
                // traversal, i-> starting node
                numOfProvinces++;
                bfs(i, adj, isVis);
            }
        }

        return numOfProvinces;
    }
}

package graph.shortestPathAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/problems/network-delay-time/
public class NetworkDelayTime {
    public int findDelayTime(int V, int[][] edges, int src) {
        ArrayList<ArrayList<PairNetwork>> adj = new ArrayList<>();
        // 1-based indexing
        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int time = edge[2];
            adj.get(u).add(new PairNetwork(v, time));
        }

        PriorityQueue<PairNetwork> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a.time));
        int[] minTime = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            minTime[i] = (int) (1e9);
        }
        minTime[src] = 0;
        pq.add(new PairNetwork(src, 0));

        while (!pq.isEmpty()) {
            PairNetwork top = pq.poll();
            int node = top.node;
            int time = top.time;

            for (PairNetwork nbr : adj.get(node)) {
                int nbrNode = nbr.node;
                int nbrTime = nbr.time;
                if (minTime[nbrNode] > time + nbrTime) {
                    minTime[nbrNode] = time + nbrTime;
                    pq.add(new PairNetwork(nbrNode, minTime[nbrNode]));
                }
            }
        }
        int maxDist = Arrays.stream(minTime).max().getAsInt();
        return maxDist == (1e9) ? -1 : maxDist;
    }

    public static void main(String[] args) {
        int V = 4, src = 2;
        int[][] edges = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        NetworkDelayTime obj = new NetworkDelayTime();
        int delayTime = obj.findDelayTime(V, edges, src);
        System.out.println("the minimum time it takes for all the nodes to " +
                "receive the signal: " + delayTime);
    }
}

class PairNetwork {
    int node;
    int time;

    public PairNetwork(int node, int time) {
        this.node = node;
        this.time = time;
    }
}
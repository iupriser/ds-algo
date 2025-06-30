package graph.shortestPathAlgo;

import java.util.*;

//https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/
public class NumberOfWaysToArriveDest {
    int countPaths(int n, int[][] roads) {
        ArrayList<ArrayList<PairForPathWay>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        // number of edges
        int m = roads.length;
        for (int i = 0; i < m; i++) {
            int u = roads[i][0];
            int v = roads[i][1];
            int time = roads[i][2];

            adj.get(u).add(new PairForPathWay(v, time));
            adj.get(v).add(new PairForPathWay(u, time));
        }

        PriorityQueue<PairForPathWay> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.first));
        long[] dist = new long[n];
        int[] ways = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Long.MAX_VALUE;
            ways[i] = 0;
        }
        int src = 0;
        int dest = n - 1;
        int mod = (int) (1e9 + 7);
        dist[src] = 0;
        ways[src] = 1;
        pq.add(new PairForPathWay(0, src));

        while (!pq.isEmpty()) {
            long dis = pq.peek().first;
            int node = pq.peek().second;
            pq.remove();

            for (PairForPathWay it : adj.get(node)) {
                int adjNode = (int) it.first;
                int edW = it.second;
                // this is the first time iam coming with this short distance
                if (dis + edW < dist[adjNode]) {
                    dist[adjNode] = dis + edW;
                    pq.add(new PairForPathWay(dis + edW, adjNode));
                    ways[adjNode] = ways[node];
                } else if (dis + edW == dist[adjNode]) {
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod;
                }
            }
        }
        return ways[dest] % mod;
    }

    public static void main(String[] args) {
        int n = 9;
        int[][] roads = {{0, 1, 1}, {0, 2, 2}, {0, 3, 1}, {0, 4, 2}, {1, 5, 2}, {2, 5, 1}, {3, 5, 2}, {3, 7, 3}, {3, 6, 2}, {4, 6, 1}, {5, 8, 1}, {7, 8, 1}, {6, 8, 1}};
        NumberOfWaysToArriveDest obj = new NumberOfWaysToArriveDest();
        int countPaths = obj.countPaths(n, roads);
        System.out.println("number of ways to arrive destination: " + countPaths);
    }
}

class PairForPathWay {
    // first==time or dis, second ==node
    long first;
    int second;

    public PairForPathWay(long first, int second) {
        this.first = first;
        this.second = second;
    }
}


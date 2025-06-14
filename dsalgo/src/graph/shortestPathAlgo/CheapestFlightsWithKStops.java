package graph.shortestPathAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CheapestFlightsWithKStops {
    public int cheapestFlight(int n, int flights[][], int src, int dst, int k) {
        ArrayList<ArrayList<PairFlight>> adj = createAdjList(n, flights);
        // don't require PQ, queue wil do the job, as stops are increasing by
        // 1 and not like dist were increasing by abrupt numbers
        Queue<TupleFlight> q = new LinkedList<>();
        // {stops, node, costs}
        int[] costs = new int[n];
        Arrays.fill(costs, (int) (1e9));
        costs[src] = 0;
        q.add(new TupleFlight(0, src, 0));
        // E => flights.size
        while (!q.isEmpty()) {
            TupleFlight top = q.poll();
            int u = top.node;
            int stop = top.stop;
            int cost = top.cost;

            if (stop > k) continue;

            for (PairFlight nbr : adj.get(u)) {
                int v = nbr.v;
                int nbrCost = nbr.cost;
                if (stop <= k && costs[v] > cost + nbrCost) {
                    costs[v] = nbrCost + cost;
                    q.add(new TupleFlight(stop + 1, v, costs[v]));
                }
            }
        }
        if (costs[dst] == (int) (1e9)) return -1;
        return costs[dst];
    }

    private ArrayList<ArrayList<PairFlight>> createAdjList(int V, int[][] flights) {
        ArrayList<ArrayList<PairFlight>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList());
        }
        for (int[] flight : flights) {
            int u = flight[0];
            int v = flight[1];
            int cost = flight[2];
            adj.get(u).add(new PairFlight(v, cost));
        }
        return adj;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        int src = 0, dst = 3, k = 1;
        CheapestFlightsWithKStops obj = new CheapestFlightsWithKStops();
        int cheapestFlight = obj.cheapestFlight(n, flights, src, dst, k);
        System.out.println("cheapest flight: " + cheapestFlight);
    }
}

class TupleFlight {
    int stop;
    int node;
    int cost;

    public TupleFlight(int stop, int node, int cost) {
        this.stop = stop;
        this.node = node;
        this.cost = cost;
    }
}

class PairFlight {
    int v;
    int cost;

    public PairFlight(int v, int cost) {
        this.v = v;
        this.cost = cost;
    }
}
package graph.mst;

import java.util.*;

public class PrimsAlgo {
    int spanningTree(int V, int E, List<List<int[]>> adj) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.distance));
        int[] vis = new int[V];
        Arrays.fill(vis, 0);
        pq.add(new Pair(0, 0));
        int sum = 0;

        while (!pq.isEmpty()) {
            int wt = pq.peek().distance;
            int node = pq.peek().node;
            pq.poll();
            // visited
            if (vis[node] == 1) continue;
            // add it to mst
            vis[node] = 1;
            sum += wt;

            for (int i = 0; i < adj.get(node).size(); i++) {
                int edW = adj.get(node).get(i)[1];
                int adjNode = adj.get(node).get(i)[0];
                // check if adjacent node is already in mst or not
                if (vis[adjNode] == 0) {
                    pq.add(new Pair(edW, adjNode));
                }
            }
        }

        return sum;
    }

    List<int[]> spanningTreePath(int V, int E, List<List<int[]>> adj) {
        PriorityQueue<Tuple> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.p.distance));
        int[] vis = new int[V];
        Arrays.fill(vis, 0);
        pq.add(new Tuple(new Pair(0, 0), -1));
        int sum = 0;
        List<int[]> mst = new ArrayList<>();

        while (!pq.isEmpty()) {
            int wt = pq.peek().p.distance;
            int node = pq.peek().p.node;
            int parent = pq.peek().parent;
            pq.poll();
            // visited
            if (vis[node] == 1) continue;
            // add it to mst
            vis[node] = 1;
            sum += wt;
            if (parent != -1)
                mst.add(new int[]{node, parent});

            for (int i = 0; i < adj.get(node).size(); i++) {
                int edW = adj.get(node).get(i)[1];
                int adjNode = adj.get(node).get(i)[0];
                if (vis[adjNode] == 0) {
                    pq.add(new Tuple(new Pair(edW, adjNode), node));
                }
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        int V = 5, E = 6;
        int[][] edges = {{0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 4, 2}, {2, 3, 2}, {4, 3, 1}};
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            adj.get(u).add(new int[]{v, wt});
            adj.get(v).add(new int[]{u, wt});
        }

        PrimsAlgo obj = new PrimsAlgo();
        int minEdW = obj.spanningTree(V, E, adj);
        System.out.println("Minimum spanning tree weight: " + minEdW);
        List<int[]> mstPath = obj.spanningTreePath(V, E, adj);
        mstPath.forEach(path -> System.out.println(Arrays.toString(path) + " "));
    }
}

class Pair {
    int distance;
    int node;

    public Pair(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}

class Tuple {
    Pair p;
    int parent;

    public Tuple(Pair p, int parent) {
        this.p = p;
        this.parent = parent;
    }
}

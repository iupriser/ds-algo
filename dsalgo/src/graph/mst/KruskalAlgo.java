package graph.mst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * sort the tuple based on wt {wt, u, v}
 */
public class KruskalAlgo {
    int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            // run for each edge emanating from i
            for (int j = 0; j < adj.get(i).size(); j++) {
                int u = i;
                int v = adj.get(i).get(j).get(0);
                int wt = adj.get(i).get(j).get(1);
                Edge edge = new Edge(u, v, wt);
                edges.add(edge);
            }
        }
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(V);
        int mstWt = 0;

        for (int i = 0; i < edges.size(); i++) {
            int wt = edges.get(i).wt;
            int u = edges.get(i).u;
            int v = edges.get(i).v;

            if (ds.findUPar(u) != ds.findUPar(v)) {
                mstWt += wt;
                ds.unionBySize(u, v);
            }
        }
        return mstWt;
    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        // undirected
        int[][] edges = {{0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}};

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            ArrayList<Integer> tmp1 = new ArrayList<>();
            ArrayList<Integer> tmp2 = new ArrayList<>();
            tmp1.add(v);
            tmp1.add(w);

            tmp2.add(u);
            tmp2.add(w);

            adj.get(u).add(tmp1);
            adj.get(v).add(tmp2);
        }

        KruskalAlgo obj = new KruskalAlgo();
        int mstWt = obj.spanningTree(V, adj);
        System.out.println("The sum of all the edge weights: " + mstWt);

    }
}

class Edge implements Comparable<Edge> {
    int u, v, wt;

    public Edge(int u, int v, int wt) {
        this.u = u;
        this.v = v;
        this.wt = wt;
    }

    // Comparator function used for
    // sorting edges_based on their weight
    @Override
    public int compareTo(Edge comparedEdge) {
        return this.wt - comparedEdge.wt;
    }
}
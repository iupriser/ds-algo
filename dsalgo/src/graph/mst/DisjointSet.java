package graph.mst;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet {
    List<Integer> rank = new ArrayList<>();
    List<Integer> size = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();

    public DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            rank.add(0);
            size.add(1);
            parent.add(i);
        }
    }

    public int findUPar(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }

    public void unionByRank(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        // u and v belongs to same component
        if (ulp_u == ulp_v) return;
        // smaller one get attached to larger always
        // to keep overall height of tree small
        if (rank.get(ulp_u) < rank.get(ulp_v)) {
            // parent of smaller will get changed to parent of larger one
            parent.set(ulp_u, ulp_v);
        } else if (rank.get(ulp_u) > rank.get(ulp_v)) {
            parent.set(ulp_v, ulp_u);
        }
        // If ranks are the same, pick one as the new root and increment its rank
        else {
            // attaching tree with upl as ulp_v to tree with ulp = ulp_u
            parent.set(ulp_v, ulp_u);
            int rankU = rank.get(ulp_u);
            rank.set(ulp_u, rankU + 1);
        }
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        // u and v belongs to same component
        if (ulp_u == ulp_v) return;
        // smaller one get attached to larger always
        int size_ulp_u = size.get(ulp_u);
        int size_ulp_v = size.get(ulp_v);
        // path compression=> During a find operation, after finding the root,
        // make every node on the path from the original element to the root point directly to the root.
        if (size_ulp_u < size_ulp_v) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size_ulp_v + size_ulp_u);
        } else if (size_ulp_u > size_ulp_v) {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size_ulp_v + size_ulp_u);
        }
        // if they have same size
        else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size_ulp_v + size_ulp_u);
        }
    }
}

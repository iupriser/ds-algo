package graph.mst;

public class DisjointSet_UnionByRank {

    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(7);
        ds.unionByRank(1, 2);
        ds.unionByRank(2, 3);
        ds.unionByRank(4, 5);
        ds.unionByRank(6, 7);
        ds.unionByRank(5, 6);

        // if 3 and 7 are in same component or not
        if (ds.findUPar(3) == ds.findUPar(7)) {
            System.out.println("Same component");
        } else {
            System.out.println("Different component");
        }

        ds.unionByRank(3, 7);

        // if 3 and 7 are in same component or not
        if (ds.findUPar(3) == ds.findUPar(7)) {
            System.out.println("Same component");
        } else {
            System.out.println("Different component");
        }
    }
}

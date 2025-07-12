package graph.mst;

public class DisjointSet_UnionBySize {

    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(7);
        ds.unionBySize(1, 2);
        ds.unionBySize(2, 3);
        ds.unionBySize(4, 5);
        ds.unionBySize(6, 7);
        ds.unionBySize(5, 6);

        // if 3 and 7 are in same component or not
        if (ds.findUPar(3) == ds.findUPar(7)) {
            System.out.println("Same component");
        } else {
            System.out.println("Different component");
        }

        ds.unionBySize(3, 7);

        // if 3 and 7 are in same component or not
        if (ds.findUPar(3) == ds.findUPar(7)) {
            System.out.println("Same component");
        } else {
            System.out.println("Different component");
        }
    }
}

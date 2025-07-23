package graph.mst;

public class MinOperationToMakeGraphConnected {
    /**
     * for n components, min number of edges required to connect them is n-1
     * 1. figure out number of components {using disjointSet {union-find}}
     * 2. count the extra edge{component after removing that edge still remains connected}
     * 3. ans= n-1
     * if(extraEdges >= ans) return ans;
     * or return -1; // not possible to connect
     *
     * @return
     */
    int minOperation(int n, int[][] edges) {
        DisjointSet ds = new DisjointSet(n);
        int cntExtra = 0;
        int m = edges.length;
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (ds.findUPar(u) == ds.findUPar(v)) {
                cntExtra++;
            } else {
                ds.unionBySize(u, v);
            }
        }
        int cntComponent = 0;
        for(int i=0; i<=n; i++){
            if(ds.parent.get(i) == i) cntComponent++;
        }
        int ans = cntComponent-1;
        if(cntExtra == ans) return ans;
        return -1;
    }
    public static void main(String[] args) {
        int V = 9;
        int[][] edges = {{0, 1}, {0, 3}, {0, 2}, {1, 2}, {2, 3}, {4, 5}, {5, 6}, {7, 8}};
        MinOperationToMakeGraphConnected obj = new MinOperationToMakeGraphConnected();
        int numOperation = obj.minOperation(V - 1, edges);
        System.out.println("Minimum number of operation required to make graph connected: " + numOperation);
    }
}

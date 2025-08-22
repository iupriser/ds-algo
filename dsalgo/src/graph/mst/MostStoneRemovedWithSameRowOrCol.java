package graph.mst;

import java.util.HashMap;
import java.util.Map;

public class MostStoneRemovedWithSameRowOrCol {
    // thinks stones as nodes
    // connect all the nodes that are in same rows or col to form different connected components
    // stones[][]- position of stones in a matrix
    public int maxStonesRemoved(int[][] stones, int n) {
        // to get the idea about the size of matrix;
        int maxRow = 0; // 4
        int maxCol = 0; // 3
        for (int i = 0; i < n; i++) {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }
        // rows are nodes of disjoint set, i.e.  0,1,2,3,4
        // columns are nodes of disjoint set, i.e. (maxRow+0th Col+1) == 5, oth col is represented by 5, similarly (maxRow+1th Col+1) == 6
        DisjointSet ds = new DisjointSet(maxRow + maxCol + 1); // new DisjointSet(5+4+1) = new DisjointSet(10)
        // 0,1,2...8

        //We just need the nodes in the Disjoint Set that are involved in having a stone.
        HashMap<Integer, Integer> stoneNodes = new HashMap<>();
        // iterate over each stone present in the matrix
        for (int i = 0; i < n; i++) {
            // stones[0][0] = 0, stones[0][1] = 0
            int nodeRow = stones[i][0];
            // nodeRow = 0, nodeCol = 0+5+1 = 6
            int nodeCol = stones[i][1] + maxRow + 1;
            // for stone at [0][0] pos => 0th row and 0th Col(5th node of DS) gets combined
            // for stone at [0][2] pos=>  0th row and 2nd Col(7th node of DS) gets combined
            ds.unionBySize(nodeRow, nodeCol);
            stoneNodes.put(nodeRow, 1);
            stoneNodes.put(nodeCol, 1);
        }

        int cnt = 0;
        // counting number of ultimate parent to get number of components
        for (Map.Entry<Integer, Integer> it : stoneNodes.entrySet()) {
            if (ds.findUPar(it.getKey()) == it.getKey()) {
                cnt++;
            }
        }
        // totalNumberOfStones - numOfComponents
        return n - cnt;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] stones = {{0, 0}, {0, 2}, {1, 3}, {3, 1}, {3, 2}, {4, 3}};
        MostStoneRemovedWithSameRowOrCol obj = new MostStoneRemovedWithSameRowOrCol();
        int maxStonesRemoved = obj.maxStonesRemoved(stones, n);
        System.out.println("maximum number of stones that can be removed: " + maxStonesRemoved);
    }
}

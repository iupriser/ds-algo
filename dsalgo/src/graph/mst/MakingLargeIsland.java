package graph.mst;

import java.util.HashSet;

public class MakingLargeIsland {
    public int maxConnection(int[][] grid) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n * n);
        // step 1: fill disjoint set
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 0) continue;
                int[] dr = {-1, 0, 1, 0};
                int[] dc = {0, 1, 0, -1};
                for (int ind = 0; ind < 4; ind++) {
                    int adjr = row + dr[ind];
                    int adjc = col + dc[ind];
                    // is adjacent node is 1, then only add into DS
                    if (isValid(adjr, adjc, n) && grid[adjr][adjc] == 1) {
                        int currNodeNum = row * n + col;
                        int adjNodeNum = adjr * n + adjc;
                        if (ds.findUPar(currNodeNum) != ds.findUPar(adjNodeNum))
                            ds.unionBySize(currNodeNum, adjNodeNum);
                    }
                }
            }
        }
        // step 2
        int mx = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) continue;
                int[] dr = {-1, 0, 1, 0};
                int[] dc = {0, 1, 0, -1};
                HashSet<Integer> components = new HashSet<>();
                for (int ind = 0; ind < 4; ind++) {
                    int adjr = row + dr[ind];
                    int adjc = col + dc[ind];
                    // is adjacent node is 1, then only add into DS
                    if (isValid(adjr, adjc, n)) {
                        if (grid[adjr][adjc] == 1) {
                            components.add(ds.findUPar(adjr * n + adjc));
                        }
                    }
                }
                int sizeTotal = 0;
                for (Integer parent : components) {
                    sizeTotal += ds.size.get(parent);
                }
                mx = Math.max(mx, sizeTotal + 1);
            }
        }
        mx = Math.max(mx, ds.size.get(ds.findUPar(0)));
//         edge case: if all the cells are 1
//        for (int cellNo = 0; cellNo < n * n; cellNo++) {
//            mx = Math.max(mx, ds.size.get(ds.findUPar(cellNo)));
//        }
        return mx;
    }

    public boolean isValid(int adjr, int adjc, int n) {
        return adjr >= 0 && adjr < n && adjc >= 0 && adjc < n;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 0, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        MakingLargeIsland obj = new MakingLargeIsland();
        int maxConnection = obj.maxConnection(grid);
        System.out.println("Max nodes in a island: " + maxConnection);

    }
}
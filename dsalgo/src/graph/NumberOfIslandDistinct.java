package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NumberOfIslandDistinct {
    int countDistinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];
        Set<ArrayList<String>> distinctIsland = new HashSet<>();

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (vis[row][col] == 0 && grid[row][col] == 1) {
                    ArrayList<String> vec = new ArrayList<>();
                    // n*m*4 => overall
                    dfs(row, col, vis, grid, vec, row, col);
                    distinctIsland.add(vec);
                }
            }
        }
        // n*m + n*m*4
        return distinctIsland.size();
    }

    private void dfs(int row, int col, int[][] vis,
                     int[][] grid,
                     ArrayList<String> vec, int row0,
                     int col0) {
        vis[row][col] = 1;
        vec.add((row - row0) + "_" + (col - col0));
        int n = grid.length;
        int m = grid[0].length;
        int[] delrow = {-1, 0, 1, 0};
        int[] delcol = {0, 1, 0, -1};
        // visit all its neighbour
        for (int i = 0; i < 4; i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];

            if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m
                    && vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {
                dfs(nrow, ncol, vis, grid, vec, row0, col0);
            }
        }
    }

    private void dfsUsingEqualsInPair(int row, int col, int[][] vis,
                                      int[][] grid,
                                      ArrayList<Pair> vec, int row0, int col0) {
        vis[row][col] = 1;
        vec.add(new Pair(row - row0, col - col0));
        int n = grid.length;
        int m = grid[0].length;
        int[] delrow = {-1, 0, 1, 0};
        int[] delcol = {0, 1, 0, -1};
        // visit all its neighbour
        for (int i = 0; i < 4; i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];

            if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m
                    && vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {
                dfsUsingEqualsInPair(nrow, ncol, vis, grid, vec, row0, col0);
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}};
        NumberOfIslandDistinct obj = new NumberOfIslandDistinct();
        int numIsland = obj.countDistinctIslands(grid);
        System.out.println("Number of distinct island: " + numIsland);
    }
}

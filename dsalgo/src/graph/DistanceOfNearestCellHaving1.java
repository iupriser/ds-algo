package graph;

import graph.basics.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class DistanceOfNearestCellHaving1 {
    public int[][] nearest(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] ans = new int[n][m];
        boolean[][] vis = new boolean[n][m];
        Queue<Triplets> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j] && grid[i][j] == 1) {
                    vis[i][j] = true;
                    q.add(new Triplets(i, j, 0));
                    ans[i][j] = 0;
                }
            }
        }
        int[] xdir = {1, -1, 0, 0};
        int[] ydir = {0, 0, -1, 1};
        while (!q.isEmpty()) {
            int row = q.peek().first;
            int col = q.peek().second;
            int dist = q.peek().distance;
            q.remove();

            for (int i = 0; i < 4; i++) {
                int nrow = row + xdir[i];
                int ncol = col + ydir[i];

                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
                        !vis[nrow][ncol] && grid[nrow][ncol] == 0) {
                    vis[nrow][ncol] = true;
                    q.add(new Triplets(nrow, ncol, dist + 1));
                    ans[nrow][ncol] = dist + 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 1, 1, 0}, {1, 1, 0, 0}, {0, 0, 1, 1}};
        DistanceOfNearestCellHaving1 obj = new DistanceOfNearestCellHaving1();
        int[][] nearest = obj.nearest(grid);
        for (int[] row : nearest) {
            for (int node : row) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }

}

class Triplets extends Pair {
    int distance;

    public Triplets(int first, int second, int distance) {
        super(first, second);
        this.distance = distance;
    }
}
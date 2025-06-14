package graph.shortestPathAlgo;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PathWithMinEffort {
    public static int MinimumEffort(int rows, int columns, int[][] heights) {
        PriorityQueue<TuplePath> pq =
                new PriorityQueue<>(Comparator.comparingInt((TuplePath a) -> a.maxDiff));
        int[][] minEffort = new int[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                minEffort[r][c] = (int) (1e9);
            }
        }
        minEffort[0][0] = 0;
        pq.add(new TuplePath(0, 0, 0));
        int[] xdir = {-1, 0, 1, 0};
        int[] ydir = {0, 1, 0, -1};
        // O(ElogV)
        // n*m*4*log(n*m)
        while (!pq.isEmpty()) {
            TuplePath top = pq.poll();
            int row = top.x;
            int col = top.y;
            int diff = top.maxDiff;
            if (row == rows - 1 && col == columns - 1)
                return diff;

            for (int i = 0; i < 4; i++) {
                int nrow = row + xdir[i];
                int ncol = col + ydir[i];
                // efforts == max of abs diff between any two adjacent nodes
                if (nrow >= 0 && nrow < rows && ncol >= 0 && ncol < columns) {
                    int newEffort = Math.max(
                            diff,
                            Math.abs(heights[nrow][ncol] - heights[row][col]));
                    if (newEffort < minEffort[nrow][ncol]) {
                        minEffort[nrow][ncol] = newEffort;
                        pq.add(new TuplePath(minEffort[nrow][ncol], nrow, ncol));
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int rows = 3;
        int columns = 3;
        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        int minimumEffort = MinimumEffort(rows, columns, heights);
        System.out.println("Min efforts: " + minimumEffort);
    }
}

class TuplePath {
    int maxDiff;
    int x;
    int y;

    public TuplePath(int maxDiff, int x, int y) {
        this.maxDiff = maxDiff;
        this.x = x;
        this.y = y;
    }
}

package graph.shortestPathAlgo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ShortestDistInBinaryMaze {
    private int shortestPathWithPQ(int[][] grid, int[] source,
                                   int[] destination) {
        if (source[0] == destination[0] && source[1] == destination[0])
            return 0;
        PriorityQueue<Tuple> pq =
                new PriorityQueue<>(Comparator.comparingInt((Tuple a) -> a.minDist));
        int n = grid.length;
        int m = grid[0].length;
        int[][] dist = new int[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                dist[r][c] = (int) (1e9);
            }
        }
        dist[source[0]][source[1]] = 0;
        pq.add(new Tuple(0, source[0], source[1]));

        while (!pq.isEmpty()) {
            Tuple top = pq.poll();
            int row = top.x;
            int col = top.y;
            int wt = top.minDist;

            int[] xdir = {-1, 0, 1, 0};
            int[] ydir = {0, 1, 0, -1};

            for (int i = 0; i < 4; i++) {
                int nrow = row + xdir[i];
                int ncol = col + ydir[i];
                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m
                        && grid[nrow][ncol] == 1 && dist[nrow][ncol] > 1 + wt) {
                    dist[nrow][ncol] = 1 + wt;
                    if (nrow == destination[0] && ncol == destination[1])
                        return dist[nrow][ncol];
                    pq.add(new Tuple(dist[nrow][ncol], nrow, ncol));
                }
            }
        }

        return dist[destination[0]][destination[1]] == 1e9 ? -1 :
                dist[destination[0]][destination[1]];
    }

    private int shortestPathWithQueue(int[][] grid, int[] source,
                                      int[] destination) {
        if (source[0] == destination[0] && source[1] == destination[0])
            return 0;
        Queue<Tuple> q = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        int[][] dist = new int[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                dist[r][c] = (int) (1e9);
            }
        }
        dist[source[0]][source[1]] = 0;
        q.add(new Tuple(0, source[0], source[1]));

        while (!q.isEmpty()) {
            Tuple top = q.poll();
            int row = top.x;
            int col = top.y;
            int wt = top.minDist;

            int[] xdir = {-1, 0, 1, 0};
            int[] ydir = {0, 1, 0, -1};

            for (int i = 0; i < 4; i++) {
                int nrow = row + xdir[i];
                int ncol = col + ydir[i];
                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m
                        && grid[nrow][ncol] == 1 && dist[nrow][ncol] > 1 + wt) {
                    dist[nrow][ncol] = 1 + wt;
                    if (nrow == destination[0] && ncol == destination[1])
                        return dist[nrow][ncol];
                    q.add(new Tuple(dist[nrow][ncol], nrow, ncol));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {1, 0, 0, 1}};
        int[] source = {0, 1};
        int[] destination = {2, 2};
        ShortestDistInBinaryMaze obj = new ShortestDistInBinaryMaze();
        int shortestDistance = obj.shortestPathWithPQ(grid, source, destination);
        System.out.println("Shortest distance between src and dest using PQ: " + shortestDistance);
        // same can be solved using Queue instead of PQ, as here, we are
        // having unit distance of every movement.
        shortestDistance = obj.shortestPathWithQueue(grid, source, destination);
        System.out.println("Shortest distance between src and dest using " +
                "queue: " + shortestDistance);
    }
}

class Tuple {
    int minDist;
    int x;
    int y;

    public Tuple(int minDist, int x, int y) {
        this.minDist = minDist;
        this.x = x;
        this.y = y;
    }
}
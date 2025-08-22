package graph.bfs_dfs;

import graph.basics.Pair;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/rotting-oranges/
public class RottenOranges {
    public static void main(String[] args) {
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        RottenOranges obj = new RottenOranges();
        int minTimeTakenToRot = obj.orangesRotting(grid);
        System.out.println("min time to rot all oranges: " + minTimeTakenToRot);
    }

    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<Triplet> q = new LinkedList<>();
        int[][] vis = new int[n][m];
        int cntFresh = 0;
        // as there can be multiple starting points depending upon number of
        // rotten oranges, we will put all the nodes which are having rotten
        // oranges in queue as initial setup

        // find the rotten orange
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                // rotten orange
                if (grid[row][col] == 2) {
                    q.add(new Triplet(row, col, 0));
                    vis[row][col] = 2;
                } else if (grid[row][col] == 1) {
                    cntFresh++;
                } else {
                    vis[row][col] = 0;
                }
            }
        }

        int minTime = 0;
        int[] xdir = {1, -1, 0, 0};
        int[] ydir = {0, 0, -1, 1};
        int cnt = 0;
        while (!q.isEmpty()) {
            int row = q.peek().first;
            int col = q.peek().second;
            int time = q.peek().time;
            minTime = Math.max(minTime, time);
            q.remove();

            for (int i = 0; i < 4; i++) {
                int nRow = row + xdir[i];
                int nCol = col + ydir[i];

                if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m
                        && vis[nRow][nCol] == 0 && grid[nRow][nCol] == 1) {
                    q.add(new Triplet(nRow, nCol, time + 1));
                    vis[nRow][nCol] = 2;
                    cnt++;
                }
            }
        }
        if (cnt != cntFresh) return -1;
        return minTime;
    }
}

class Triplet extends Pair {
    int time;

    public Triplet(int first, int second, int time) {
        super(first, second);
        this.time = time;
    }
}
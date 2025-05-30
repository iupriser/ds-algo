package graph;
//https://leetcode.com/problems/number-of-islands/description/

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class NumberOfIsland {
    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}
                , {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '1'}};
        NumberOfIsland obj = new NumberOfIsland();
        int numberOfIsland = obj.numIsland(grid);
        System.out.println("Number of islands: " + numberOfIsland);
    }

    public int numIsland(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];
        int cnt = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (vis[row][col] == 0 && grid[row][col] == '1') {
                    cnt++;
                    bfs(row, col, vis, grid);
                }
            }
        }
        return cnt;
    }

    private void bfs(int r, int c, int[][] vis, char[][] grid) {
        Queue<Pair> q = new LinkedList<>();
        vis[r][c] = 1;
        q.add(new Pair(r, c));
        int n = grid.length;
        int m = grid[0].length;

        while (!q.isEmpty()) {
            int row = q.peek().first;
            int col = q.peek().second;
            q.remove();
            // traverse in the neighbours and mark them if not visited
            for (int delRow = -1; delRow <= 1; delRow++) {
                for (int delCol = -1; delCol <= 1; delCol++) {
                    int nRow = row + delRow;
                    int nCol = col + delCol;
                    // neighbour might not exist, as they may be crossing
                    // boundary, and also check if neighbours are land/water
                    // and are not visited
                    if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && grid[nRow][nCol] == '1' && vis[nRow][nCol] == 0) {
                        vis[nRow][nCol] = 1;
                        q.add(new Pair(nRow, nCol));
                    }
                }
            }

        }
    }
}

class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair pair = (Pair) o;
        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}

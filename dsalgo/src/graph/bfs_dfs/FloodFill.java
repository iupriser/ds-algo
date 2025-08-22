package graph.bfs_dfs;

import graph.basics.Pair;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/flood-fill/
public class FloodFill {
    public static void main(String[] args) {
        int[][] image = {{1, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        int sr = 1, sc = 2, newColor = 2;
        FloodFill obj = new FloodFill();
        int[][] floodFill = obj.floodFill(image, sr, sc, newColor);
        for (int[] ints : floodFill) {
            for (int num : ints) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length;
        int m = image[0].length;
        int[][] vis = new int[n][m];
        bfs(sr, sc, vis, image, color);
        return image;
    }

    private void bfs(int sr, int sc, int[][] vis, int[][] image, int color) {
        Queue<Pair> q = new LinkedList<>();
        vis[sr][sc] = 1;
        int originalCol = image[sr][sc];
        q.add(new Pair(sr, sc));
        image[sr][sc] = color;
        int n = image.length;
        int m = image[0].length;

        while (!q.isEmpty()) {
            int row = q.peek().first;
            int col = q.peek().second;
            q.remove();
            // traverse neighbouring nodes
            int[] xdir = {1, -1, 0, 0};
            int[] ydir = {0, 0, -1, 1};
            for (int i = 0; i < 4; i++) {
                int nRow = row + xdir[i];
                int nCol = col + ydir[i];

                if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && image[nRow][nCol] == originalCol && vis[nRow][nCol] == 0) {
                    vis[nRow][nCol] = 1;
                    image[nRow][nCol] = color;
                    q.add(new Pair(nRow, nCol));
                }
            }
        }
    }
}

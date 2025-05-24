package graph;

// https://leetcode.com/problems/surrounded-regions/description/
// https://www.geeksforgeeks.org/problems/replace-os-with-xs0052/1
public class SurroundingRegion {
    static char[][] fill(char mat[][]) {
        int n = mat.length;
        int m = mat[0].length;
        int[] delrow = {1, -1, 0, 0};
        int[] delcol = {0, 0, -1, 1};
        int[][] vis = new int[n][m];
        // traverse first row and last row
        for (int j = 0; j < m; j++) {
            // first row
            if (vis[0][j] == 0 && mat[0][j] == 'O') {
                dfs(0, j, vis, mat, delrow, delcol);
            }
            // last row
            if (vis[n - 1][j] == 0 && mat[n - 1][j] == 'O') {
                dfs(n - 1, j, vis, mat, delrow, delcol);
            }
        }
        // traverse first col and last col
        for (int i = 0; i < n; i++) {
            // first col
            if (vis[i][0] == 0 && mat[i][0] == 'O') {
                dfs(i, 0, vis, mat, delrow, delcol);
            }
            // last col
            if (vis[i][m - 1] == 0 && mat[i][m - 1] == 'O') {
                dfs(i, m - 1, vis, mat, delrow, delcol);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == 0 && mat[i][j] == '0') {
                    mat[i][j] = 'X';
                }
            }
        }
        return mat;
    }

    public static void dfs(int row, int col, int[][] vis, char[][] mat, int[] delrow,
                           int[] delcol) {
        vis[row][col] = 1;
        int n = mat.length;
        int m = mat[0].length;
        // check for top, right, bottom, left
        for (int i = 0; i < 4; i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];
            if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && vis[nrow][ncol] == 0 && mat[nrow][ncol] == '0') {
                dfs(nrow, ncol, vis, mat, delrow, delcol);
            }
        }
    }

    public static void main(String[] args) {
        char[][] mat = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'O', 'X', 'X'}, {'X', 'X', 'O', 'O'}};
        char[][] updateMatrix = fill(mat);
        for (char[] chars : updateMatrix) {
            for (char ch : chars) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }
}
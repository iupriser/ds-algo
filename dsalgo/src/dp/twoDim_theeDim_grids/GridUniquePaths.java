package dp.twoDim_theeDim_grids;

import java.util.Arrays;

public class GridUniquePaths {
    int[][] dp;

    public GridUniquePaths(int m, int n) {
        dp = new int[m][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
    }

    // TC: O(2^(m*n)) , as for each cell we have two option
    // SC: O(path length), (m-1)+ (n-1)
    public int countUniquePathsRec(int i, int j) {
        // base case
        if (i == 0 && j == 0) {
            return 1;
        }
        // exceeds the boundary
        if (i < 0 || j < 0) {
            return 0;
        }
        // as we're moving from bottom, so the direction will be opposite
        // right == left, down==up
        int left = countUniquePathsRec(i, j - 1);
        int up = countUniquePathsRec(i - 1, j);
        return left + up;
    }

    //
    public int countUniquePathsMemoization(int i, int j) {
        // base case
        if (i == 0 && j == 0) {
            return 1;
        }
        // exceeds the boundary (out of bound)
        if (i < 0 || j < 0) {
            return 0;
        }
        if (dp[i][j] != -1) return dp[i][j];
        // as we're moving from bottom, so the direction will be opposite
        // right == left, down==up
        int left = countUniquePathsMemoization(i, j - 1);
        int up = countUniquePathsMemoization(i - 1, j);
        return dp[i][j] = left + up;
    }

    public int countUniquePathsTabulation(int m, int n) {
//        dp[0][0] = 1; - base case
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = 1;
                } else {
                    int left = 0, up = 0;
                    if (i > 0) {
                        up = dp[i - 1][j];
                    }
                    if (j > 0) {
                        left = dp[i][j - 1];
                    }
                    dp[i][j] = left + up;
                }
            }
        }
        return dp[m][n];
    }

    // dp[i][j] = left + up = dp[i][j-1] + dp[i-1][j]
    public int countUniquePathsSpaceOptimization(int m, int n) {
        int[] prev = new int[n + 1];
        prev[0] = 1;
        for (int i = 0; i <= m; i++) {
            int[] temp = new int[n + 1];
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) temp[j] = 1;
                else {
                    int left = 0, up = 0;
                    if (i > 0) {
                        up = prev[j];
                    }
                    if (j > 0) {
                        left = temp[j - 1];
                    }
                    temp[j] = left + up;
                }
            }
            prev = temp;
        }
        return prev[n];
    }

    public static void main(String[] args) {
        int m = 3, n = 7;
        GridUniquePaths obj = new GridUniquePaths(m, n);
        int countUniquePathsRec = obj.countUniquePathsRec(m - 1, n - 1);
        System.out.println("number of unique ways using recursion: " + countUniquePathsRec);
        int countUniquePathsMemoization = obj.countUniquePathsMemoization(m - 1, n - 1);
        System.out.println("number of unique ways using memoization: " + countUniquePathsMemoization);
        obj = new GridUniquePaths(m, n);
        int countUniquePathsTabulation = obj.countUniquePathsTabulation(m - 1, n - 1);
        System.out.println("number of unique ways using tabulation: " + countUniquePathsTabulation);
        int countUniquePathsSpaceOptimization = obj.countUniquePathsSpaceOptimization(m - 1, n - 1);
        System.out.println("number of unique ways using space optimization: " + countUniquePathsSpaceOptimization);
    }
}

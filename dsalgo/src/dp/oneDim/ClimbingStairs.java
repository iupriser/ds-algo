package dp.oneDim;

import java.util.Arrays;

/**
 * Given a number of stairs. Starting from the 0th stair we need to climb to the “Nth” stair.
 * At a time we can climb either one or two steps.
 * We need to return the total number of distinct ways to reach from 0th to Nth stair.
 */
public class ClimbingStairs {
    private int[] dp;

    public ClimbingStairs(int n) {
        this.dp = new int[n + 1];
        Arrays.fill(dp, -1);
    }

    private int distinctWaysViaMemoization(int n) {
        if (n == 0 || n == 1) return 1;
        if (dp[n] != -1) return dp[n];
        return dp[n] = distinctWaysViaMemoization(n - 1) + distinctWaysViaMemoization(n - 2);
    }

    private int distinctWaysViaTabulation(int n) {
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 5;
        ClimbingStairs obj = new ClimbingStairs(n);
        int distinctWaysViaMemoization = obj.distinctWaysViaMemoization(n);
        System.out.println("number of distinct ways to climb stairs using memoization: " + distinctWaysViaMemoization);
        int distinctWaysViaTabulation = obj.distinctWaysViaTabulation(n);
        System.out.println("number of distinct ways to climb stairs using tabulation: " + distinctWaysViaTabulation);
    }
}

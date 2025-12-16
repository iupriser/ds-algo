package dp.oneDim;

import java.util.Arrays;

/**
 * A frog wants to climb a staircase with n steps.
 * Given an integer array heights, where heights[i] contains the height of the ith step, and an integer k.
 * To jump from the ith step to the jth step, the frog requires abs(heights[i] - heights[j]) energy,
 * where abs() denotes the absolute difference. The frog can jump from the ith step to any step
 * in the range [i + 1, i + k], provided it exists.
 * Return the minimum amount of energy required by the frog to go from the 0th step to the (n-1)th step.
 */
public class FrogJumpWithKDist {
    private int[] dp;

    public FrogJumpWithKDist(int n) {
        this.dp = new int[n];// not n+1
        Arrays.fill(dp, -1);
    }

    private int frogJumpRec(int n, int[] heights, int k) {
        // min energy required to reach 0th stair is 0
        if (n == 0) return 0;
        int minSteps = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (n - j >= 0) {
                int currJumpSteps =
                        frogJumpRec(n - j, heights, k) + Math.abs(heights[n] - heights[n - j]);
                minSteps = Math.min(minSteps, currJumpSteps);
            }
        }
        return minSteps;
    }

    private int frogJumpViaMemo(int n, int[] heights, int k) {
        if (n == 0) return 0;
        if (dp[n] != -1) return dp[n];
        int minSteps = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (n - j >= 0) {
                int currJumpSteps =
                        frogJumpRec(n - j, heights, k) + Math.abs(heights[n] - heights[n - j]);
                minSteps = Math.min(minSteps, currJumpSteps);
            }
        }
        return dp[n] = minSteps;
    }

    private int frogJumpViaTabulation(int n, int[] heights, int k) {
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            int minSteps = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int currJumpSteps = dp[i - j] + Math.abs(heights[i] - heights[i - j]);
                    minSteps = Math.min(minSteps, currJumpSteps);
                }
            }
            dp[i] = minSteps;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 3, 5, 4};
        int n = heights.length;
        int k = 1;
        FrogJumpWithKDist obj = new FrogJumpWithKDist(n);
        int frogJumpRec = obj.frogJumpRec(n - 1, heights, k);
        System.out.println("Min energy consumed to reach (n-1)th stair via recursion: " + frogJumpRec);
        int frogJumpMemo = obj.frogJumpViaMemo(n - 1, heights, k);
        System.out.println("Min energy consumed to reach (n-1)th stair via memoization: " + frogJumpMemo);
        int frogJumpTabulation = obj.frogJumpViaTabulation(n, heights, k);
        System.out.println("Min energy consumed to reach (n-1)th stair via tabulation: " + frogJumpTabulation);
    }
}

package dp.oneDim;

import java.util.Arrays;

/**
 * Given a number of stairs and a frog, the frog wants to climb from the 0th stair to the (N-1)th stair.
 * At a time the frog can climb either one or two steps. A height[N] array is also given.
 * Whenever the frog jumps from a stair i to stair j, the energy consumed in the jump is abs(height[i]- height[j]),
 * where abs() means the absolute difference.
 * We need to return the minimum energy that can be used by the frog to jump from stair 0 to stair N-1..
 */
public class FrogJump {
    private int[] dp;

    public FrogJump(int n) {
        this.dp = new int[n];// not n+1
        Arrays.fill(dp, -1);
    }

    private int frogJumpRec(int n, int[] heights) {
        // min energy required to reach 0th stair is 0
        if (n == 0) return 0;
        int jumpTwo = Integer.MAX_VALUE;
        int jumpOne = frogJumpRec(n - 1, heights) + Math.abs(heights[n] - heights[n - 1]);
        if (n > 1) {
            jumpTwo = frogJumpRec(n - 2, heights) + Math.abs(heights[n] - heights[n - 2]);
        }
        return Math.min(jumpOne, jumpTwo);
    }

    private int frogJumpViaMemo(int n, int[] heights) {
        if (n == 0) return 0;
        if (dp[n] != -1) return dp[n];
        int jumpTwo = Integer.MAX_VALUE;
        int jumpOne = frogJumpRec(n - 1, heights) + Math.abs(heights[n] - heights[n - 1]);
        if (n > 1) {
            jumpTwo = frogJumpRec(n - 2, heights) + Math.abs(heights[n] - heights[n - 2]);
        }
        return dp[n] = Math.min(jumpOne, jumpTwo);
    }

    private int frogJumpViaTabulation(int n, int[] heights) {
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            int jumpTwo = Integer.MAX_VALUE;
            int jumpOne = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
            if (i > 1) {
                jumpTwo = dp[i - 2] + Math.abs(heights[i] - heights[i - 2]);
            }
            dp[i] = Math.min(jumpOne, jumpTwo);
        }
        return dp[n - 1];
    }

    private int frogJumpViaSpaceOptimization(int n, int[] heights) {
        int prev1 = 0, prev2 = 0;
        for (int i = 1; i < n; i++) {
            int jumpTwo = Integer.MAX_VALUE;
            int jumpOne = prev1 + Math.abs(heights[i] - heights[i - 1]);
            if (i > 1) {
                jumpTwo = prev2 + Math.abs(heights[i] - heights[i - 2]);
            }
            int curri = Math.min(jumpOne, jumpTwo);
            prev2 = prev1;
            prev1 = curri;
        }
        return prev1;
    }


    public static void main(String[] args) {
        int[] heights = {2, 1, 3, 5, 4};
        int n = heights.length;
        FrogJump obj = new FrogJump(n);
        int frogJumpRec = obj.frogJumpRec(n - 1, heights);
        System.out.println("Min energy consumed to reach (n-1)th stair via recursion: " + frogJumpRec);
        int frogJumpMemo = obj.frogJumpViaMemo(n - 1, heights);
        System.out.println("Min energy consumed to reach (n-1)th stair via memoization: " + frogJumpMemo);
        int frogJumpTabulation = obj.frogJumpViaTabulation(n, heights);
        System.out.println("Min energy consumed to reach (n-1)th stair via tabulation: " + frogJumpTabulation);
        int frogJumpSpaceOptimization = obj.frogJumpViaSpaceOptimization(n, heights);
        System.out.println("Min energy consumed to reach (n-1)th stair via space optimization: " + frogJumpSpaceOptimization);
    }
}

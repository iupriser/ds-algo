package greedy.medium_hard;

//https://leetcode.com/problems/jump-game-ii/

import java.util.Arrays;

/**
 * Return the minimum number of jumps to reach index n - 1.
 * The test cases are generated such that you can reach index n - 1.
 * 0 <= j <= nums[i] and i + j < n
 */
public class JumpGame_2 {
    // try all possible ways and figure out the one with minimum jumps
    public int jumpBrute(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        dp = new int[n][n];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, -1));

        return jumpHelperMemo(0, 0, nums);
    }

    /**
     * trying out all possible ways
     * TC:O(N^N), SC:O(N)
     */
    private int jumpHelperBrute(int index, int jump, int[] nums) {
        if (index >= nums.length - 1) return jump;
        // jumpGreedyOptimized can be 1,2,3..nums[index]
        int miniJumps = Integer.MAX_VALUE;
        // trying all possible jumps for that index
        for (int i = 1; i <= nums[index]; i++) {
            miniJumps = Math.min(miniJumps, jumpHelperBrute(index + i, jump + 1, nums));
        }
        return miniJumps;
    }

    /**
     * 2 variables are changing (index, numOfJump), so we can have dp[N][N]
     * TC:O(N*N), SC:O(N*N)
     */
    int[][] dp;// all initialized to -1

    private int jumpHelperMemo(int index, int jump, int[] nums) {
        if (index >= nums.length - 1) return jump;
        if (dp[index][jump] != -1) {
            return dp[index][jump];
        }
        // jumpGreedyOptimized can be 1,2,3..nums[index]
        int miniJumps = Integer.MAX_VALUE;
        // trying all possible jumps for that index
        for (int i = 1; i <= nums[index]; i++) {
            miniJumps = Math.min(miniJumps, jumpHelperMemo(index + i, jump + 1, nums));
        }
        return dp[index][jump] = miniJumps;
    }

    /**
     * greedy approach
     */
    public int jumpGreedyOptimized(int[] nums) {
        int n = nums.length;
        int jumps = 0;
        int l = 0, r = 0;
        // second last element
        while (r < n - 1) {
            // farthest that i can jump from current index
            int farthest = 0;
            // iterate over range [l,r] to get the farthest I can jump
            for (int ind = l; ind <= r; ind++) {
                farthest = Math.max(farthest, ind + nums[ind]);
            }
            jumps += 1;
            l = r + 1;
            r = farthest;
        }
        return jumps;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 0, 1, 4};
        JumpGame_2 obj = new JumpGame_2();
        System.out.println("-----Brute force way to get min jumps to reach end--------");
        System.out.println(obj.jumpBrute(nums));
        System.out.println("-----Greedy way to get min jumps to reach end--------");
        System.out.println(obj.jumpGreedyOptimized(nums));
    }
}

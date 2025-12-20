package dp.oneDim;

import java.util.Arrays;

/**
 * Given an array of N positive integers, we need to return the maximum sum of the subsequence such
 * that no two elements of the subsequence are adjacent elements in the array.
 */
public class HouseRobber {
    private int[] dp;

    public HouseRobber(int n) {
        this.dp = new int[n];
        Arrays.fill(dp, -1);
    }

    private int houseRobRec(int ind, int[] house) {
        // base cases
        // we have not picked element(house) at index 1
        if (ind == 0) {
            return house[ind];
        } else if (ind < 0) {
            return 0;
        }
        int pick = house[ind] + houseRobRec(ind - 2, house);
        int notPick = 0 + houseRobRec(ind - 1, house);

        return Math.max(pick, notPick);
    }

    // top to bottom
    private int houseRobMemoization(int ind, int[] house) {
        // base cases
        // we have not picked element(house) at index 1
        if (ind == 0) {
            return house[ind];
        } else if (ind < 0) {
            return 0;
        }
        if (dp[ind] != -1) return dp[ind];
        int pick = house[ind] + houseRobMemoization(ind - 2, house);
        int notPick = houseRobMemoization(ind - 1, house);

        return dp[ind] = Math.max(pick, notPick);
    }

    private int houseRobTabulation(int ind, int[] house) {
        // base cases
        dp[0] = house[0];
        for (int i = 1; i <= ind; i++) {
            // if i==1, then pick = house[i] + dp[1-2] {dp[-1], negative index =>0}, pick = house[i]
            int pick = house[i];
            if (i > 1) {
                pick = pick + dp[i - 2];
            }
            int notPick = dp[i - 1];
            dp[i] = Math.max(pick, notPick);
        }
        return dp[ind];
    }

    private int houseRobSpaceOptimization(int ind, int[] house) {
        int prev2 = 0, prev1 = house[0];
        for (int i = 0; i <= ind; i++) {
            int take = house[i];
            if (i > 1) {
                take = take + prev2;
            }
            int notTake = prev1;
            int curri = Math.max(take, notTake);
            prev2 = prev1;
            prev1 = curri;
        }
        return prev1;
    }

    public static void main(String[] args) {
        int[] house = {5, 5, 10, 100, 10, 5};
        int numOfHouse = house.length;
        HouseRobber obj = new HouseRobber(numOfHouse);
        // converting it to zero based indexing
        int maxSumViaRec = obj.houseRobRec(numOfHouse - 1, house);
        System.out.println("Maximum sum robbed from house using Recursion: " + maxSumViaRec);
        int maxSumViaMemoization = obj.houseRobMemoization(numOfHouse - 1, house);
        System.out.println("Maximum sum robbed from house using Memoization(top-down): " + maxSumViaMemoization);
        obj = new HouseRobber(numOfHouse);
        int maxSumViaTabulation = obj.houseRobTabulation(numOfHouse - 1, house);
        System.out.println("Maximum sum robbed from house using Tabulation(bottom-up): " + maxSumViaTabulation);
        int maxSumViaSpaceOptimization = obj.houseRobSpaceOptimization(numOfHouse - 1, house);
        System.out.println("Maximum sum robbed from house using space-optimizationß: " + maxSumViaSpaceOptimization);

    }
}

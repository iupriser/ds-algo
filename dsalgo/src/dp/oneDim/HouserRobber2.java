package dp.oneDim;

import java.util.Arrays;

/**
 * A thief needs to rob money in a street.
 * The houses in the street are arranged in a circular manner.
 * Therefore, the first and the last house are adjacent to each other.
 * The security system in the street is such that if adjacent houses are robbed, the police will get notified.
 * Given an array of integers “Arr" which represents money at each house, we need to return the
 * maximum amount of money that the thief can rob without alerting the police.
 */
public class HouserRobber2 {
    private int[] dp;

    public HouserRobber2(int n) {
        this.dp = new int[n];
        Arrays.fill(dp, -1);
    }

    private int houseRobSpaceOptimization(int[] houses) {
        int n = houses.length;
        int prev2 = 0, prev1 = houses[0];
        for (int i = 1; i < n; i++) {
            int take = houses[i];
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

    private int houseRobber(int[] houses) {
        int n = houses.length;
        int[] arr1 = new int[n - 1];
        int[] arr2 = new int[n - 1];

        if(n==1){return houses[0];}

        for (int i = 0; i < n; i++) {
            if (i != 0) {
                arr1[i - 1] = houses[i];
            }
            if (i != n - 1) {
                arr2[i] = houses[i];
            }
        }

        int ans1 = houseRobSpaceOptimization(arr1);
        int ans2 = houseRobSpaceOptimization(arr2);

        return Math.max(ans1, ans2);

//        List<Integer> temp1 = new ArrayList<>();
//        List<Integer> temp2 = new ArrayList<>();
//        int n = houses.length;
//        for (int i = 0; i < n; i++) {
//            if (i != 0) temp1.add(houses[i]);
//            if (i != n - 1) temp2.add(houses[i]);
//        }
//        return Math.max(houseRobSpaceOptimization(temp1.stream().mapToInt(i -> i).toArray()),
//                houseRobSpaceOptimization(temp2.stream().mapToInt(i -> i).toArray()));
    }

    public static void main(String[] args) {
        int[] house = {5, 5, 10, 100, 10, 5};
        int numOfHouse = house.length;
        HouserRobber2 obj = new HouserRobber2(numOfHouse);
        // converting it to zero based indexing
        int maxSumViaSpaceOptimization = obj.houseRobber(house);
        System.out.println("Maximum sum robbed from house using space-optimization: " + maxSumViaSpaceOptimization);
    }
}

package arrays.medium;

public class MaximumSumSubarray {

    public static void main(String[] args) {
        int[] nums = {-2, -3, 4, -1, -2, 1, 5, -3};
        int n = nums.length;
        int res = 0;
        res = maximumSumSubarrayBrute(nums, n);
        System.out.println(res);
        res = maximumSumSubarrayBetter(nums, n);
        System.out.println(res);
        res = maximumSumSubarrayOptimized_KADANE(nums, n);
        System.out.println(res);
        printMaximumSumSubarray(nums, n);
    }

    private static void printMaximumSumSubarray(int[] nums, int n) {
        int maxi = Integer.MIN_VALUE;
        int startIndex = 0;
        int endIndex = 0;
        int sum = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (sum == 0) {
                start = i;
            }
            sum += nums[i];
            if (sum > maxi) {
                maxi = sum;
                startIndex = start;
                endIndex = i;
            }

            if (sum < 0) {
                sum = 0;
            }
        }
        // print the maximum sum subarray
        for (int i = startIndex; i <= endIndex; i++) {
            System.out.print(nums[i] + " ");
        }
    }


    private static int maximumSumSubarrayOptimized_KADANE(int[] nums, int n) {
        int maxi = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            maxi = Math.max(maxi, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxi;
    }

    private static int maximumSumSubarrayBetter(int[] nums, int n) {
        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int currSum = 0;
            for (int j = i; j < n; j++) {
                currSum += nums[j];
                maxi = Math.max(maxi, currSum);
            }
        }
        return maxi;
    }

    private static int maximumSumSubarrayBrute(int[] nums, int n) {
        // generate all subarary and choose the one with maximum sum
        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // subarray --> [i...j]
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                    maxi = Math.max(maxi, sum);
                }
            }
        }
        return maxi;
    }


}

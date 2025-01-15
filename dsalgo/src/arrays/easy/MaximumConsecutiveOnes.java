package arrays.easy;

public class MaximumConsecutiveOnes {

    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 1, 1, 1, 0, 1, 1};
        int n = nums.length;
        int maxCount = count(nums, n);
        System.out.println("Maximum number of consecutive one: " + maxCount);
    }

    private static int count(int[] nums, int n) {
        int count = 0;
        int maxCount = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                count = 0;
            }
        }
        return maxCount;
    }

}

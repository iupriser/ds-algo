package arrays.hard;

public class MaximumProductSubarray {

    public static void main(String[] args) {
        int[] arr = {3, 2, -1, 4, -6, 3, -2, 6};
        System.out.println(maximumProductSubarrayOptimized(arr));

    }

    private static int maximumProductSubarrayOptimized(int[] arr) {
        int n = arr.length;
        int prefix = 1, suffix = 1;
        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (prefix == 0) {
                prefix = 1;
            }
            if (suffix == 0) {
                suffix = 1;
            }
            prefix *= arr[i];
            suffix *= arr[n - i - 1];

            maxi = Math.max(maxi, Math.max(prefix, suffix));
        }
        return maxi;
    }

}

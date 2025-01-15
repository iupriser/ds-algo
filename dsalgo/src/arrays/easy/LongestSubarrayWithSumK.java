package arrays.easy;

import java.util.HashMap;

public class LongestSubarrayWithSumK {

    public static void main(String[] args) {
//		int[] arr = { 10, 5, 2, 7, 1, 9 };
//		int k = 15;
//		int k = 10, arr[] = { 2, 3, 5, 1, 9 };
//		int k = 3, arr[] = { 2, 0, 0, 3 };
        int k = 2, arr[] = {-1, 2, -1, 3};
        int longestSubarraySize = longestSubarrayWithSumK_Brute(arr, k);
        System.out.println("maximum length of consecutive array: " + longestSubarraySize);

        longestSubarraySize = longestSubarrayWithSumK_Better(arr, k);
        System.out.println("maximum length of consecutive array: " + longestSubarraySize);

        longestSubarraySize = longestSubarrayWithSumK_Optimal(arr, k);
        System.out.println("maximum length of consecutive array: " + longestSubarraySize);
    }

    // brute force method
    private static int longestSubarrayWithSumK_Brute(int[] arr, int k) {
        int n = arr.length;
        int count = 0;
        int maxi = 0;
        for (int start = 0; start < n; start++) {
            int currentSum = 0;
            for (int end = start; end < n; end++) {
                currentSum += arr[end];
                if (currentSum == k) {
                    count = end - start + 1;
                    break;
                }
            }
            maxi = Math.max(count, maxi);
        }

        return maxi;
    }

    // Better using Hashing
    private static int longestSubarrayWithSumK_Better(int[] arr, int k) {
        int n = arr.length;
        int maxi = 0;
        // prefixSum, index
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        int prefixSum = 0;

        for (int i = 0; i < n; i++) {
            // calculate the prefix sum till index i:
            prefixSum += arr[i];

            // if the sum = k, update the maxLen:
            if (prefixSum == k) {
                maxi = Math.max(maxi, i + 1);
            }

            // calculate the sum of remaining part i.e. x-k:
            int rem = prefixSum - k;

            // Calculate the length and update maxLen:
            if (prefixSumMap.containsKey(rem)) {
                int len = i - prefixSumMap.get(rem);

                maxi = Math.max(maxi, len);
            }

            // Finally, update the map checking the conditions:
            if (!prefixSumMap.containsKey(prefixSum)) {
                prefixSumMap.put(prefixSum, i);
            }

        }

        return maxi;
    }

    // Optimal
    private static int longestSubarrayWithSumK_Optimal(int[] arr, int k) {
        int n = arr.length;
        int left = 0, right = 0;
        int currSum = 0;
        int maxi = 0;
        while (right < n) {
            currSum += arr[right];
            if (currSum < k) {
                right++;
            } else if (currSum == k) {
                maxi = Math.max(maxi, right - left + 1);
                right++;
            } else {
                while (left <= right && currSum > k) {
                    currSum -= arr[left];
                    left++;
                }

                if (currSum == k) {
                    maxi = Math.max(maxi, right - left + 1);
                }
                right++;
            }
        }

        return maxi;
    }

}

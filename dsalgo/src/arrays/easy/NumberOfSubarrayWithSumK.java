package arrays.easy;

import java.util.HashMap;

public class NumberOfSubarrayWithSumK {

    public static void main(String[] args) {
//		int arr[] = { 1, 1, 1 }, k = 2;
        int arr[] = {0, 0, 0}, k = 0;
        int numberOfSubarray = numberOfSubarayWithSumK_Brute(arr, k);
        System.out.println("Number of subarray with sum " + k + " is " + numberOfSubarray);

        numberOfSubarray = numberOfSubarayWithSumK_Better(arr, k);
        System.out.println("Number of subarray with sum " + k + " is " + numberOfSubarray);
    }

    private static int numberOfSubarayWithSumK_Better(int[] arr, int k) {
        int n = arr.length;
        HashMap<Integer, Integer> psumMap = new HashMap<>();
        int prefixSum = 0;
        int count = 0;
        psumMap.put(0, 1);
        for (int i = 0; i < n; i++) {

            prefixSum += arr[i];

            if (prefixSum == k) {
                count++;
            }

            int rem = prefixSum - k;

            if (psumMap.containsKey(rem)) {
                // found a subarray with sum = k
                count++;
            }
            if (!psumMap.containsKey(prefixSum)) {
                psumMap.put(prefixSum, i);
            }
        }
        return count;
    }

    private static int numberOfSubarayWithSumK_Brute(int[] arr, int k) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int currSum = 0;
            for (int j = i; j < n; j++) {
                currSum += arr[j];
                if (currSum == k) {
                    count++;
                }
            }
        }
        return count;
    }

}

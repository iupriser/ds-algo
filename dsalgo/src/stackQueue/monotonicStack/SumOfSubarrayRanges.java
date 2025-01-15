package stackQueue.monotonicStack;

import java.util.Arrays;
import java.util.Stack;

public class SumOfSubarrayRanges {
    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 2};
        long numOfSubarrayRange = subArrayRangesBrute(arr);
        System.out.println(numOfSubarrayRange);
        numOfSubarrayRange = subArrayRangesOptimal(arr);
        System.out.println(numOfSubarrayRange);
    }

    private static long subArrayRangesBrute(int[] arr) {
        int n = arr.length;
        long totalSubarrayRange = 0L;
        for (int i = 0; i < n; i++) {
            int largest = arr[i], smallest = arr[i];
            // j=i+1, as number itself is not contributing to answer
            for (int j = i + 1; j < n; j++) {
                // subarray - [i...j]
                largest = Math.max(largest, arr[j]);
                smallest = Math.min(smallest, arr[j]);
                totalSubarrayRange += (largest - smallest);
            }
        }
        return totalSubarrayRange;
    }

    // summation(largest) - summation(smallest)
    private static long subArrayRangesOptimal(int[] arr) {
        int n = arr.length;
        long largest = findSubarraySumMaximum(arr);
        long smallest = findSubarraySumMinimum(arr);
        return largest - smallest;
    }

    private static long findSubarraySumMaximum(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Stack<Integer> stk = new Stack<>();

        // calculate previous greater element index
        for (int i = 0; i < n; i++) {
            while (!stk.empty() && arr[stk.peek()] < arr[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        // calculate next greater element index
        for (int i = n - 1; i >= 0; i--) {
            while (!stk.empty() && arr[stk.peek()] <= arr[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        long largest = 0L;
        for (int i = 0; i < n; i++) {
            largest += (long) (i - left[i]) * (right[i] - i) * arr[i];
        }
        return largest;
    }

    private static long findSubarraySumMinimum(int[] arr) {
        int n = arr.length;
        // store the index of element that are smaller than the current element to the left
        int[] left = new int[n];
        // store the index of element that are smaller and equal than the current element to the
        // right
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Stack<Integer> stk = new Stack<>();

        // calculate pse(previous smallest element) index for each element
        for (int i = 0; i < n; i++) {
            while (!stk.empty() && arr[stk.peek()] > arr[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        // calculate nse(next smallest element) index for each element
        for (int i = n - 1; i >= 0; i--) {
            while (!stk.empty() && arr[stk.peek()] >= arr[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        long smallest = 0L;
        for (int i = 0; i < n; i++) {
            smallest += (long) (i - left[i]) * (right[i] - i) * arr[i];
        }
        return smallest;
    }
}

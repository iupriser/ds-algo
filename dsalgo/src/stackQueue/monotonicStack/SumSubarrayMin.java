package stackQueue.monotonicStack;

import java.util.Arrays;
import java.util.Stack;

public class SumSubarrayMin {
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
//        System.out.println("sum of subarray minimum: " + sumSubarrayMinsBrute(arr));
        System.out.println("sum of subarray minimum: " + sumSubarrayMinsOptimal(arr));
    }

    private static int sumSubarrayMinsBrute(int[] arr) {
        int n = arr.length;
        int sum = 0;
//        double mod = Math.pow(10, 9) + 7;
        double mod = 1e10 + 7;
        for (int i = 0; i < n; i++) {
            int currentMin = arr[i];
            for (int j = i; j < n; j++) {
                // sum the minimum of current subArray [i..j]
                currentMin = Math.min(currentMin, arr[j]);
                sum = (int) ((sum + currentMin) % mod);
            }
        }
        return sum;
    }

    private static int sumSubarrayMinsOptimal(int[] arr) {
        int n = arr.length;
        int mod = (int) (1e9 + 7);
        long total = 0;
        int[] pse = findPreviousSmallerElementIndex(arr);
        int[] nse = findNextSmallerElementIndex(arr);
        for (int i = 0; i < n; i++) {
            int left = i - pse[i];
            int right = nse[i] - i;
            total += (long) (i - pse[i]) * (nse[i] - i) % mod * arr[i] % mod;
            total %= mod;
        }
        return (int) total;
    }

    private static int[] findPreviousSmallerElementIndex(int[] arr) {
        Stack<Integer> stk = new Stack<>();
        int n = arr.length;
        int[] pse = new int[n];
        Arrays.fill(pse, -1);
        for (int i = 0; i < n; i++) {
            // Popping all elements which are greater than the current element
            while (!stk.empty() && arr[i] < arr[stk.peek()]) {
                stk.pop();
            }
            if (!stk.empty()) {
                pse[i] = stk.peek();
            }
            stk.push(arr[i]);
        }
        return pse;
    }

    private static int[] findNextSmallerElementIndex(int[] arr) {
        int n = arr.length;
        int[] nse = new int[n];
        Arrays.fill(nse, n);
        Stack<Integer> stk = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stk.empty() && arr[i] <= arr[stk.peek()]) {
                stk.pop();
            }
            if (stk.empty()) {
                nse[i] = n;
            } else {
                nse[i] = stk.peek();
            }
            stk.push(i);
        }
        return nse;
    }

}

package stackQueue.monotonicStack;

import java.util.Arrays;
import java.util.Stack;

public class Sample {
    /**
     * Counts the number of next greater elements (NGEs) to the right for each element in the array.
     *
     * @param arr The input array of integers
     * @return An array of integers representing the count of NGEs for each element
     */
    public static int[] countNGEsToRight(int[] arr) {
        int n = arr.length;
        int[] result = new int[n]; // Result array to store the counts
        Stack<Integer> stack = new Stack<>(); // Stack to store indices

        // Traverse the array from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements from the stack that are not greater
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }

            // Count of NGEs is the size of the stack
            result[i] = stack.size();

            // Push the current index onto the stack
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        // Example input
        int[] arr = {3, 4, 2, 7, 5, 8, 10, 6};

        // Count NGEs to the right
        int[] result = countNGEsToRight(arr);

        // Output the results
        System.out.println("Input Array: " + Arrays.toString(arr));
        System.out.println("NGEs to Right: " + Arrays.toString(result));
    }
}
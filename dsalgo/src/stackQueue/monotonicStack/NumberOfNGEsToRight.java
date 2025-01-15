package stackQueue.monotonicStack;

import java.util.Stack;

public class NumberOfNGEsToRight {
    // Array to store the next greater element index
    private static void fillNext(int[] ngeArray, int[] arr) {
//        int n = arr.length;
//        Stack<Integer> stk = new Stack<>();
//
//        for (int i = n - 1; i >= 0; i--) {
//            while (!stk.empty() && stk.peek() <= arr[i]) {
//                stk.pop();
//            }
//            if (stk.isEmpty()) {
//                ngeArray[i] = -1;
//            } else {
//                ngeArray[i] = stk.peek();
//            }
//            stk.push(arr[i]);
//        }
//    }
    }

    private static void fillNext2(int[] next, int[] array, int size) {
        // Use of stack in Java
        Stack<Integer> stack = new Stack<>();

        // Push the 0th index to the stack
        stack.push(0);

        // Traverse in the loop from 1 to size-1
        for (int i = 1; i < size; i++) {
            // Iterate till stack is empty
            while (!stack.isEmpty()) {
                // Get the topmost index in the stack
                int currentIndex = stack.peek();

                // If the current element is greater than the top index-th element
                if (array[currentIndex] < array[i]) {
                    // Initialize the current index position's next greatest as index
                    next[currentIndex] = i;

                    // Pop the current index as its greater element has been found
                    stack.pop();
                } else {
                    // If not greater, then break
                    break;
                }
            }

            // Push the i index so that its next greatest can be found
            stack.push(i);
        }

        // Iterate for all other indices left inside stack
        while (!stack.isEmpty()) {
            int currentIndex = stack.peek();
            // Mark it as -1 as no element is greater than it on the right
            next[currentIndex] = -1;
            stack.pop();
        }
    }

    // Function to count the number of next greater numbers to the right
    private static void count(int[] array, int[] dp, int size) {
        // Initializes the next array as 0
        int[] next = new int[size];

        // Calls the function to pre-calculate the next greatest element indexes
        fillNext2(next, array, size);

        for (int i = size - 2; i >= 0; i--) {
            // If the i-th element has no next greater element to the right
            if (next[i] == -1) {
                dp[i] = 0;
            } else {
                // Count of next greater numbers to right
                dp[i] = 1 + dp[next[i]];
            }
        }
    }

    // Answers all queries in O(1)
    private static int answerQuery(int[] dp, int index) {
        // Returns the number of next greater elements to the right
        return dp[index];
    }

    // Driver program to test the above function
    public static void main(String[] args) {
        int[] array = {3, 4, 2, 7, 5, 8, 10, 6};
        int size = array.length;
        int[] dp = new int[size];

        // Calls the function to count the number of greater elements to the right for every element
        count(array, dp, size);

        // Query 1 answered
        System.out.println(answerQuery(dp, 0));

        // Query 2 answered
        System.out.println(answerQuery(dp, 5));

        // Query 3 answered
        System.out.println(answerQuery(dp, 1));
    }
}

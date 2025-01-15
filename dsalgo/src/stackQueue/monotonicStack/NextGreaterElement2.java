package stackQueue.monotonicStack;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement2 {
    public static void main(String[] args) {
        int[] arr = {2, 10, 12, 1, 11}; // circular array
        int[] ans = nge(arr);
        System.out.println(Arrays.toString(ans));
    }

    private static int[] nge(int[] arr) {
        int n = arr.length;
        int[] ngeArray = new int[n];
        Stack<Integer> stk = new Stack<>();
        // circular array -> double the array hypothetically
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stk.empty() && stk.peek() <= arr[i % n]) {
                stk.pop();
            }
            if (i < n) {
                if (stk.isEmpty()) {
                    ngeArray[i] = -1;
                } else {
                    ngeArray[i] = stk.peek();
                }
            }
            stk.push(arr[i%n]);
        }
        return ngeArray;
    }
}

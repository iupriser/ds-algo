package stackQueue.monotonicStack;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {
    public static void main(String[] args) {
        int[] arr = {4, 12, 5, 3, 1, 2, 5, 3, 1, 2, 4, 6};
        int[] ans = nge(arr);
        System.out.println(Arrays.toString(ans));
    }

    private static int[] nge(int[] arr) {
        int n = arr.length;
        int[] ngeArray = new int[n];
        Stack<Integer> stk = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stk.empty() && stk.peek() <= arr[i]) {
                stk.pop();
            }
            if (stk.isEmpty()) {
                ngeArray[i] = -1;
            } else {
                ngeArray[i] = stk.peek();
            }
            stk.push(arr[i]);
        }
        return ngeArray;
    }
}

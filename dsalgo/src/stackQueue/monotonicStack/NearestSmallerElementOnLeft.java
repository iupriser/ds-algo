package stackQueue.monotonicStack;

import java.util.Arrays;
import java.util.Stack;

public class NearestSmallerElementOnLeft {
    public static void main(String[] args) {
        int[] arr = {5, 7, 9, 6, 7, 4, 5, 1, 3, 7};
        int[] ans = nse(arr);
        System.out.println(Arrays.toString(ans));
    }

    private static int[] nse(int[] arr) {
        int n = arr.length;
        int[] nseArray = new int[n];

        Stack<Integer> stk = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stk.empty() && arr[i] < stk.peek()) {
                stk.pop();
            }
            if (stk.empty()) {
                nseArray[i] = -1;
            } else {
                nseArray[i] = stk.peek();
            }
            stk.push(arr[i]);
        }
        return nseArray;
    }
}

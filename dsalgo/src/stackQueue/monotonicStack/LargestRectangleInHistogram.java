package stackQueue.monotonicStack;

import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] height = {3, 2, 10, 11, 5, 10, 6, 3};
        int largestRectangleArea = largestRectangleAreaBetter(height);
        System.out.println("Largest Rectangle area: " + largestRectangleArea);
        largestRectangleArea = largestRectangleAreaOptimal(height);
        System.out.println("Largest Rectangle area: " + largestRectangleArea);
    }

    private static int largestRectangleAreaBetter(int[] heights) {
        int n = heights.length;
        int largestArea = 0;
        // for each bar, calculate pse and nse
        Stack<Integer> stk = new Stack<>();
        int[] pse = new int[n];
        int[] nse = new int[n];
        Arrays.fill(pse, -1);
        Arrays.fill(nse, n);
        // calculate pse index
        for (int i = 0; i < n; i++) {
            while (!stk.empty() && heights[stk.peek()] > heights[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                pse[i] = stk.peek();
            }
            stk.push(i);
        }
        // clear the stk
        stk.clear();
        // calculate nse index
        for (int i = n - 1; i >= 0; i--) {
            while (!stk.empty() && heights[stk.peek()] >= heights[i]) {
                stk.pop();
            }
            if (!stk.empty()) {
                nse[i] = stk.peek();
            }
            stk.push(i);
        }

        for (int i = 0; i < n; i++) {
            int currentRectangleArea = (nse[i] - pse[i] - 1) * heights[i];
            largestArea = Math.max(largestArea, currentRectangleArea);
        }
        return largestArea;
    }

    private static int largestRectangleAreaOptimal(int[] heights) {
        int n = heights.length;
        Stack<Integer> stk = new Stack<>(); // contain index of PSE
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            while (!stk.empty() && heights[stk.peek()] > heights[i]) {
                int elementIndex = stk.pop();
                int nse = i;
                int pse = stk.empty() ? -1 : stk.peek();
                maxArea = Math.max(maxArea, heights[elementIndex] * (nse - pse - 1));
            }
            stk.push(i);
        }
        // if elements are still left in stack after iteration completes
        while (!stk.empty()) {
            int nse = n;
            int element = stk.pop();
            int pse = stk.empty() ? -1 : stk.peek();

            maxArea = Math.max(maxArea, heights[element] * (nse - pse - 1));
        }

        return maxArea;
    }
}

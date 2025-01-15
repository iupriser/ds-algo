package stackQueue.monotonicStack;

import java.util.Arrays;
import java.util.Stack;

public class MaximalRectangle {
    public static void main(String[] args) {
        char[][] matrix = {{'1', '0', '1', '0', '1',},
                {'1', '0', '1', '1', '1',},
                {'1', '1', '1', '1', '1',},
                {'1', '0', '0', '1', '0',},};
        int largestRectangleArea = maximalRectangle(matrix);
        System.out.println("Maximal Rectangle area: " + largestRectangleArea);

    }

    private static int maximalRectangle(char[][] matrix) {
        int maxArea = Integer.MIN_VALUE;
        // rows
        int n = matrix.length;
        // columns
        int m = matrix[0].length;

        int[][] prefixSumMatrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(prefixSumMatrix[i], 0);
        }

        for (int j = 0; j < m; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += matrix[i][j] == '1' ? 1 : 0;
                if (matrix[i][j] == '0') {
                    sum = 0;
                }
                prefixSumMatrix[i][j] = sum;
            }
        }
        // compute for all the rows
        for (int i = 0; i < n; i++) {
            maxArea = Math.max(maxArea, largestRectangleAreaOptimal(prefixSumMatrix[i]));
        }
        return maxArea;
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

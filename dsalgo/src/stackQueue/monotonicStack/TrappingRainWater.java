package stackQueue.monotonicStack;

public class TrappingRainWater {
    public static void main(String[] args) {
        int height[] = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        System.out.println("trapped water is " + trapBrute(height));
//        System.out.println("trapped water is " + trapBetter(height));
//        System.out.println("trapped water is " + trapBetter2(height));
        System.out.println("trapped water is " + trapOptimized(height));
    }

    static int trapBrute(int[] height) {
        int n = height.length;
        int waterTrapped = 0;
        for (int i = 0; i < n; i++) {
            int leftMax = 0, rightMax = 0;
            // check for leftMax for currentElement
            int j = i;
            while (j >= 0) {
                leftMax = Math.max(leftMax, height[j]);
                j--;
            }
            // check for rightMax for currentElement
            j = i;
            while (j < n) {
                rightMax = Math.max(rightMax, height[j]);
                j++;
            }
            waterTrapped += Math.min(leftMax, rightMax) - height[i];
        }
        return waterTrapped;
    }

    private static int trapBetter(int[] height) {
        int n = height.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];

        prefix[0] = height[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = Math.max(prefix[i - 1], height[i]);
        }

        suffix[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], height[i]);
        }
        int waterTrapped = 0;
        for (int i = 0; i < n; i++) {
            waterTrapped += Math.min(prefix[i], suffix[i]) - height[i];
        }

        return waterTrapped;
    }

    private static int trapBetter2(int[] height) {
        int n = height.length;
//        int[] prefix = new int[n];
        int[] suffix = new int[n];

//        prefix[0] = height[0];
//        for (int i = 1; i < n; i++) {
//            prefix[i] = Math.max(prefix[i - 1], height[i]);
//        }

        suffix[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], height[i]);
        }
        int waterTrapped = 0;
        int leftMax = -1;
        for (int i = 0; i < n; i++) {
            leftMax = Math.max(leftMax, height[i]);
            waterTrapped += Math.min(leftMax, suffix[i]) - height[i];
        }

        return waterTrapped;
    }
// two pointer approach
    private static int trapOptimized(int[] height) {
//      height[] = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}
        int n = height.length;
        int left = 0, right = n - 1;
        int leftMax = 0, rightMax = 0;
        int trapperWater = 0;
        while (left < right) {
            // we have taller building on right side
            if (height[left] <= height[right]) {
                // look on the left side, do we have a building s.t we can store water
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    trapperWater += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    trapperWater += rightMax - height[right];
                }
                right--;
            }
        }
        return trapperWater;
    }
}

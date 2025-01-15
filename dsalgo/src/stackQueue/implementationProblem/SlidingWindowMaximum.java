package stackQueue.implementationProblem;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 2, 1, 6};
        int k = 3;
        int[] maxSlidingWindow = maxSlidingWindowBrute(nums, k);
        System.out.println(Arrays.toString(maxSlidingWindow));

        maxSlidingWindow = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(maxSlidingWindow));
    }

    private static int[] maxSlidingWindowBrute(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        // traverse all windows
        for (int i = 0; i <= n - k; i++) {
            int maxi = nums[i];
            for (int j = i; j <= i + k - 1; j++) {
                maxi = Math.max(maxi, nums[j]);
            }
            ans[i] = maxi;
        }
        return ans;
    }

    private static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int ri = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // remove numbers out of range
            if (!dq.isEmpty() && dq.peekFirst() == i - k) {
                dq.poll();
            }
            // remove smaller number in k range as they are useless
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }

            dq.offerLast(i);
            if (i >= k - 1) {
                ans[ri++] = nums[dq.peek()];
            }
        }
        return ans;

//
//        int i = 0;
//        int j = 0;
//
//        while (j < n) {
//            while (!dq.isEmpty() && dq.peekLast() < nums[j]) {
//                dq.removeLast();
//            }
//            dq.addLast(nums[j]);
//
//            if (j - i + 1 < k) {
//                j++;
//            } else if (j - i + 1 == k) {
//                ans[i] = dq.peekFirst();
//                if (dq.peekFirst() == nums[i]) {
//                    dq.removeFirst();
//                }
//                i++;
//                j++;
//            }
//        }
//        return ans;
    }

//    private static int[] maxSlidingWindowOptimal(int[] nums, int k) {
//        int n = nums.length;
//        Deque<Integer> dq = new ArrayDeque<>();
//        int[] ans = new int[n - k + 1];
//
//        for (int i = 0; i < n; i++) {
//            // remove element that exceed the window size boundary
//            if (!dq.isEmpty() && dq.peekFirst() == i - k) {
//                dq.pollFirst();
//            }
//            while (!dq.isEmpty() && nums[dq.peekFirst()] < nums[i]) {
//                dq.pollFirst();
//            }
//            dq.addLast(i);
//            // if window size is achieved, store the max in the ans[]
//            if(i>= k-1){
//
//            }
//        }
//    }
}

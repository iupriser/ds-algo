package arrays.medium;

import java.util.ArrayList;

public class RearrangeArrayElementsBySign {

    public static void main(String[] args) {
        int[] nums = {3, 1, -2, -5, 2, -4};
        int[] ans = rearrangeArray_brute(nums);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
        ans = rearrangeArray_better(nums);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
        ans = rearrangeArray_optimal(nums);
        for (int num : ans) {
            System.out.print(num + " ");
        }
    }

    private static int[] rearrangeArray_brute(int[] nums) {
        // create two array
        int n = nums.length;
        ArrayList<Integer> posArray = new ArrayList<>();
        ArrayList<Integer> negArray = new ArrayList<>();
        ;
        for (int num : nums) {
            if (num >= 0)
                posArray.add(num);
            else
                negArray.add(num);
        }

        for (int i = 0; i < n / 2; i++) {
            nums[i * 2] = posArray.get(i);
            nums[i * 2 + 1] = negArray.get(i);
        }

        return nums;
    }

    private static int[] rearrangeArray_better(int[] nums) {
        // in-place, 2 pointer
        int curr = 0, tmp = 0;

        for (int i = 0; i < nums.length; i++) {
            // positive number
            if (i % 2 == 0) {
                if (nums[curr] > 0) {
                    curr++;
                    continue;
                } else {
                    while (nums[++tmp] < 0) {
                        swap(nums, curr, tmp);
                    }
                }
            }
            // negative number
            else {
                if (nums[curr] < 0) {
                    curr++;
                    continue;
                } else {
                    while (nums[++tmp] > 0) {
                        swap(nums, curr, tmp);
                    }
                }
            }
        }
        return nums;
    }

    private static void swap(int[] nums, int curr, int tmp) {
        int t = nums[curr];
        nums[curr] = nums[tmp];
        nums[tmp] = t;
    }

    private static int[] rearrangeArray_optimal(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int posIndex = 0, negIndex = 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                ans[posIndex] = nums[i];
                posIndex += 2;
            } else {
                ans[negIndex] = nums[i];
                negIndex += 2;
            }
        }
        return ans;
    }
}

package arrays.medium;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    public static void main(String[] args) {
        int nums[] = {2, 7, 11, 15}, target = 9;
//		int nums[] = { 3, 2, 4 }, target = 6;
//		int nums[] = { 3, 3 }, target = 6;
        int[] ans = twoSum_Brute(nums, target);
        for (int index : ans) {
            System.out.print(index + " ");
        }
        System.out.println();
        ans = twoSum_Better(nums, target);
        for (int index : ans) {
            System.out.print(index + " ");
        }
        System.out.println();
        ans = twoSum_Optimal(nums, target);
        for (int index : ans) {
            System.out.print(index + " ");
        }

    }

    private static int[] twoSum_Optimal(int[] nums, int target) {
        Arrays.sort(nums);
        // two pointer approach
        int i = 0, j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] == target) {
                return new int[]{i, j};
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    // TC: O(n*n)
    private static int[] twoSum_Brute(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int rem = target - nums[i];
            for (int j = 0; j < n; j++) {
                if (j != i && nums[j] == rem) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private static int[] twoSum_Better(int[] nums, int target) {
        int n = nums.length;
        // element,index
        HashMap<Integer, Integer> map = new HashMap<>();
        int j = 0, i = 0;

        for (i = 0; i < n; i++) {
            int rem = target - nums[i];
            if (map.containsKey(rem)) {
                j = map.get(rem);
                break;
            }
            map.put(nums[i], i);
        }
        return new int[]{j, i};
    }

}

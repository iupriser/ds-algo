package arrays.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NextPermutation {

    public static void main(String[] args) {
//		int[] nums = { 1, 2, 3 };
        int[] nums = {1, 1, 2};
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        ans = permuteMethod1(nums);
        for (var list : ans) {
            for (var num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println("----------------");
        ans = permuteMethod2(nums);
        for (var list : ans) {
            for (var num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println("----------------");
        Set<List<Integer>> answer = permuteMethodWithDup(nums);
        for (var list : answer) {
            for (var num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        // Optimized way to find next permutation
//		int[] arr = { 2, 1, 5, 4, 3, 0, 0 };
//		int[] arr = { 5, 4, 3, 2, 1, 0, 0 };
        int[] arr = {1, 2, 3};
        arr = nextPer(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static List<List<Integer>> permuteMethod1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        boolean[] freq = new boolean[nums.length];
        recPermutationMethod1(freq, ds, ans, nums);
        return ans;
    }

    private static void recPermutationMethod1(boolean[] freq, List<Integer> ds, List<List<Integer>> ans, int[] nums) {
        if (ds.size() == nums.length) {
            ans.add(new ArrayList<Integer>(ds));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!freq[i]) {
                freq[i] = true;
                ds.add(nums[i]);
                recPermutationMethod1(freq, ds, ans, nums);
                ds.remove(ds.size() - 1);
                freq[i] = false;
            }
        }
    }

    private static List<List<Integer>> permuteMethod2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        recPermutationMethod2(0, nums, ans);
        return ans;
    }

    private static void recPermutationMethod2(int index, int[] nums, List<List<Integer>> ans) {
        if (index == nums.length) {
            List<Integer> ds = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; i++) {
                ds.add(nums[i]);
            }
            ans.add(ds);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(i, index, nums);
            recPermutationMethod2(index + 1, nums, ans);
            swap(i, index, nums);
        }

    }

    private static void swap(int i, int index, int[] nums) {
        int t = nums[i];
        nums[i] = nums[index];
        nums[index] = t;
    }

    private static Set<List<Integer>> permuteMethodWithDup(int[] nums) {
        Set<List<Integer>> ans = new HashSet<List<Integer>>();
        recPermutationMethodWithDup(0, nums, ans);
        return ans;
    }

    private static void recPermutationMethodWithDup(int index, int[] nums, Set<List<Integer>> ans) {
        if (index == nums.length) {
            List<Integer> ds = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; i++) {
                ds.add(nums[i]);
            }
            ans.add(new ArrayList<>(ds));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(i, index, nums);
            recPermutationMethodWithDup(index + 1, nums, ans);
            swap(i, index, nums);
        }

    }

    private static int[] nextPer(int[] arr) {
        // find the breaking point
        int n = arr.length;
        int index = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            rev(0, n - 1, arr);
            return arr;
        }

        // find next greater integer than arr[i], as we traverse from back, all the
        // elements are already in increasing order till breaking point
        for (int i = n - 1; i > index; i--) {
            if (arr[i] > arr[index]) {
                swap(index, i, arr);
                break;
            }
        }

        // sort/reverse the remaining array
        rev(index + 1, n - 1, arr);

        return arr;
    }

    private static void rev(int i, int j, int[] arr) {
        while (i < j) {
            swap(i, j, arr);
            i++;
            j--;
        }

    }
}

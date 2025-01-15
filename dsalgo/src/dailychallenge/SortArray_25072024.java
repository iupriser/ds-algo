package dailychallenge;

import java.util.ArrayList;

public class SortArray_25072024 {

    public static void main(String[] args) {
        int[] nums = {5, 1, 1, 2, 0, 0};
        // sort without using built-in method in TC:O(nlogn) and SC:O(n)
        nums = sortArray(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    // sorting using
    private static int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void mergeSort(int[] nums, int low, int high) {
        if (low >= high)
            return;
        int mid = (low + high) / 2;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);
        merge(nums, low, mid, high);

    }

    private static void merge(int[] nums, int low, int mid, int high) {
        ArrayList<Integer> tmp = new ArrayList<>();
        int left = low, right = mid + 1;

        while (left <= mid && right <= high) {
            if (nums[left] <= nums[right]) {
                tmp.add(nums[left]);
                left++;
            } else {
                tmp.add(nums[right]);
                right++;
            }
        }
        while (left <= mid) {
            tmp.add(nums[left]);
            left++;
        }
        while (right <= high) {
            tmp.add(nums[right]);
            right++;
        }
        for (int i = low; i <= high; i++) {
            nums[i] = tmp.get(i - low);
        }

    }

}

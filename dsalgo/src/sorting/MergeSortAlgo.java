package sorting;

import java.util.ArrayList;
import java.util.List;

//TC: NlogN
//SC: N
public class MergeSortAlgo {

    public static void main(String[] args) {
        int[] nums = {4, 5, 2, 3, 1, 8, 0};
        mergeSort(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.print(num + " ");
        }
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
        int left = low, right = mid + 1;
        List<Integer> tmp = new ArrayList<>();
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

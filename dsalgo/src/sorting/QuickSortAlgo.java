package sorting;

public class QuickSortAlgo {

    public static void main(String[] args) {
        int[] nums = {4, 5, 2, 3, 1, 8, 0};
        quickSort(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.print(num + " ");
        }

    }

    private static void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(nums, low, high);
            quickSort(nums, low, partitionIndex - 1);
            // partitionIndex contains the correct position of pivot
            quickSort(nums, partitionIndex + 1, high);
        }
    }

    private static int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        int i = low, j = high;
        while (i < j) {
            while (i <= high - 1 && pivot >= nums[i]) {
                i++;
            }
            while (j >= low + 1 && pivot < nums[j]) {
                j--;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, j, low);
        return j;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}

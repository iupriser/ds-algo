package sorting;

import java.util.Arrays;

public class CountingSort {

    public static void main(String[] args) {
        int[] nums = {1, 4, 2, 6, 1, 0, 5, 3, 9, 8, 1};

        System.out.println("array before sorting: " + Arrays.toString(nums));
        countingSort(nums);
        System.out.println("array after sorting: " + Arrays.toString(nums));

        String str = "edsab";
        System.out.println("String before sorting: " + str);
        str = countingSortForString(str);
        System.out.println("String after sorting: " + str);

    }

    private static String countingSortForString(String str) {
        int[] countArray = new int[128];// zero-based indexing

        for (int i = 0; i < str.length(); i++) {
            countArray[str.charAt(i)]++;
        }

        // prefixSum array created
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }

        char[] outputArray = new char[str.length()];
        // with the help of prefixSum array, determine the correct position of elements
        // present in nums
        for (int i = str.length() - 1; i >= 0; i--) {
            outputArray[countArray[str.charAt(i)] - 1] = str.charAt(i);
            countArray[str.charAt(i)]--;
        }
        str = new String(outputArray);
        return str;
    }

    private static void countingSort(int[] nums) {
        // find max in the array
        int maxi = nums[0];
        for (int num : nums) {
            maxi = Math.max(maxi, num);
        }
        int[] countArray = new int[maxi + 1];
        for (int i = 0; i < nums.length; i++) {
            countArray[nums[i]]++;
        }
        // make countArray as prefixSum array
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }
        int[] outputArray = new int[nums.length];
        // with the help of prefixSum array, determine the correct position of elements
        // present in nums
        for (int i = nums.length - 1; i >= 0; i--) {
            outputArray[countArray[nums[i]] - 1] = nums[i];
            countArray[nums[i]]--;
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = outputArray[i];
        }
    }

}

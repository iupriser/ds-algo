package dailychallenge;

import java.util.Arrays;

public class SortJumbledNumber_24072024 {

    public static void main(String[] args) {
//		int[] mapping = { 8, 9, 4, 0, 2, 1, 3, 5, 7, 6 };
//		int[] nums = { 991, 338, 0 };
        int[] mapping = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] ans = sortJumbledNumber(mapping, nums);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static int[] sortJumbledNumber(int[] mapping, int[] nums) {
        int[][] numMapped = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            numMapped[i][0] = nums[i];
            int num = nums[i];
            int mappedNum = (num == 0) ? mapping[0] : 0;
            int base = 1;
            while (num > 0) {
                int digit = num % 10;
                mappedNum = mappedNum + base * mapping[digit];
                base = base * 10;
                num = num / 10;
            }

            numMapped[i][1] = mappedNum;
        }
        for (var i = 0; i < nums.length; i++)
            System.out.print(numMapped[i][0] + " ");
        System.out.println();
        for (var i = 0; i < nums.length; i++)
            System.out.print(numMapped[i][1] + " ");

        System.out.println("---------------------------");
        Arrays.sort(numMapped, (a, b) -> Integer.compare(a[1], b[1]));

        for (var i = 0; i < nums.length; i++)
            nums[i] = numMapped[i][0];

        return nums;
    }

}

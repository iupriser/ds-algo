package arrays.hard;

import java.util.Arrays;
import java.util.HashMap;

public class MissingAndRepeatingNumber {

    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 2, 1, 1};
        int[] ans = new int[2];
        // brute
        ans = findTwoElementBrute(arr);
        System.out.println(Arrays.toString(ans));
        // better
        ans = findTwoElementBetter(arr);
        System.out.println(Arrays.toString(ans));
        // optimal -1 (Math)
        ans = findTwoElementOptimalMath(arr);
        System.out.println(Arrays.toString(ans));
        // optimal -2 (XOR)
        ans = findTwoElementOptimalXor(arr);
        System.out.println(Arrays.toString(ans));

    }

    private static int[] findTwoElementBrute(int arr[]) {
        int n = arr.length;
        int missing = -1, repeating = -1;
        // [1,2,3,4...,n]
        for (int i = 1; i <= n; i++) {
            int count = 0;
            // loop traversing arr
            for (int j = 0; j < n; j++) {
                if (arr[j] == i) {
                    count++;
                }
            }
            if (count == 2) {
                repeating = i;
            } else if (count == 0) {
                missing = i;
            }
            if (repeating != -1 && missing != -1)
                break;
        }
        return new int[]{repeating, missing};
    }

    private static int[] findTwoElementBetter(int[] arr) {
        int n = arr.length;
        int missing = -1, repeating = -1;
        int[] hash = new int[n + 1];

        for (int i = 0; i < n; i++) {
            hash[arr[i]]++;
        }

        for (int i = 1; i <= n; i++) {
            if (hash[i] == 2) {
                repeating = i;
            } else if (hash[i] == 0) {
                missing = i;
            }
        }

        return new int[]{repeating, missing};
    }

    private static int[] findTwoElementOptimalMath(int[] arr) {
        int n = arr.length;
        long s = 0, sn = (n * (n + 1)) / 2;
        long s2 = 0, s2n = (n * (n + 1) * (2 * n + 1)) / 6;

        for (int i = 0; i < n; i++) {
            s += (long) arr[i];
            s2 += (long) arr[i] * (long) arr[i];
        }

        long val1 = s - sn;
        long val2 = s2 - s2n;

        val2 = val2 / val1;

        long x = (val1 + val2) / 2;
        long y = x - val1;

        return new int[]{(int) x, (int) y};

    }

    private static int[] findTwoElementOptimalXor(int[] arr) {
        int n = arr.length;
        int xr = 0;
        for (int i = 0; i < n; i++) {
            xr = xr ^ arr[i];
            xr = xr ^ (i + 1);
        }
        // extract lowest set bit of xr(all other bits are cleared)
        int number = (xr & ~(xr - 1));

        int group0 = 0, group1 = 0;

        for (int i = 0; i < n; i++) {
            // part of group 1
            if ((arr[i] & number) != 0) {
                group1 ^= arr[i];
            } else {
                group0 ^= arr[i];
            }
        }

        for (int i = 1; i <= n; i++) {
            if ((i & number) != 0) {
                group1 ^= i;
            } else {
                group0 ^= i;
            }
        }

        // identify the numbers
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == group0) {
                cnt++;
            }
        }
        if (cnt == 2) {
            return new int[]{group0, group1};
        } else
            return new int[]{group1, group0};

    }

}

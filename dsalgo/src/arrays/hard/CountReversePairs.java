package arrays.hard;

import java.util.ArrayList;

public class CountReversePairs {

    public static void main(String[] args) {
//		int[] arr = { 40, 25, 19, 12, 9, 6, 2 };
        int[] arr = {2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
        System.out.println(reversePairs(arr));

    }

    private static int reversePairs(int[] arr) {
        int n = arr.length;
        return mergeSort(arr, 0, n - 1);
    }

    private static int mergeSort(int[] arr, int low, int high) {
        int count = 0;
        if (low >= high)
            return count;
        int mid = (low + high) / 2;
        count += mergeSort(arr, low, mid);
        count += mergeSort(arr, mid + 1, high);
        count += countPairs(arr, low, mid, high);
        merge(arr, low, mid, high);

        return count;
    }

    private static int countPairs(int[] arr, int low, int mid, int high) {
        int count = 0;
        int right = mid + 1;
        for (int i = low; i <= mid; i++) {
            while (right <= high && (long) arr[i] > 2 * (long) arr[right]) {
                right++;
            }
            count += (right - (mid + 1));
        }
        return count;
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> tmp = new ArrayList<>();
        int left = low, right = mid + 1;

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                tmp.add(arr[left++]);
            } else {
                tmp.add(arr[right++]);
            }
        }
        while (left <= mid) {
            tmp.add(arr[left++]);
        }
        while (right <= high) {
            tmp.add(arr[right++]);
        }

        for (int i = low; i <= high; i++) {
            arr[i] = tmp.get(i - low);
        }
    }

}

package arrays.hard;

import java.util.ArrayList;

public class CountInversionPairs {

    public static void main(String[] args) {
        long[] arr = {5, 3, 2, 4, 1};
        System.out.println(countInversionOptimised(arr));
    }

    private static long countInversionOptimised(long[] arr) {
        int n = arr.length;
        return mergeSort(arr, 0, n - 1);
    }

    private static long mergeSort(long[] arr, int low, int high) {
        long count = 0;
        if (low >= high)
            return count;
        int mid = (low + high) / 2;
        count += mergeSort(arr, low, mid);
        count += mergeSort(arr, mid + 1, high);
        count += merge(arr, low, mid, high);

        return count;
    }

    private static long merge(long[] arr, int low, int mid, int high) {
        ArrayList<Long> tmp = new ArrayList<>();
        int left = low, right = mid + 1;
        long count = 0;

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                tmp.add(arr[left++]);
            } else {
                count += (mid - left + 1);
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

        return count;
    }

}

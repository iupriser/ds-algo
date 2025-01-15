package arrays.medium;

public class SortArrayof012 {

    public static void main(String[] args) {
        int[] arr = {2, 0, 2, 1, 1, 0};
//		int[] arr = { 2, 0, 1 };
        arr = sortArray_Brute(arr);
        arr = sortArray_Optimized(arr);
        for (int e : arr) {
            System.out.print(e + " ");
        }
    }

    private static int[] sortArray_Brute(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            if (arr[i] > arr[j]) {
                swap(arr, i, j);
                j--;
            }
            i++;
        }
        return arr;
    }

    private static int[] sortArray_Optimized(int[] arr) {
        int n = arr.length;
        int low = 0, mid = 0, high = n - 1;
        while (mid < high) {
            if (arr[mid] == 0) {
                swap(arr, mid, low);
                low++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else {
                swap(arr, mid, high);
                high--;
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

}

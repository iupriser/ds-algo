package bs.oneDimArray;

public class BS {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 6;
        int index = -1;
        index = bsIterative(arr, target);
        System.out.println(index);
        index = bsRecursion(arr, 0, arr.length, target);
        System.out.println(index);

    }

    private static int bsIterative(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private static int bsRecursion(int[] arr, int low, int high, int target) {
        if (low > high)
            return -1;
        int mid = low + (high - low) / 2;
        if (arr[mid] == target)
            return mid;
        else if (arr[mid] < target) {
            return bsRecursion(arr, mid + 1, high, target);
        } else {
            return bsRecursion(arr, low, mid - 1, target);
        }

    }
}

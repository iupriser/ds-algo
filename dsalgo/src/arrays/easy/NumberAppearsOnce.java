package arrays.easy;

import java.util.HashMap;
import java.util.Map;

public class NumberAppearsOnce {

    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 1, 2};
        int ans = getSingleElement(arr);
        System.out.println("The single element is: " + ans);
        int n = arr.length;
        ans = getSingleElementByXoring(arr, n);
        System.out.println("The single element is: " + ans);
    }

    private static int getSingleElement(int[] arr) {
        // size of the array:
        int n = arr.length;

        // Declare the hashmap.
        // And hash the given array:
        HashMap<Integer, Integer> mpp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int value = mpp.getOrDefault(arr[i], 0);
            mpp.put(arr[i], value + 1);
        }

        // Find the single element and return the answer:
        for (Map.Entry<Integer, Integer> it : mpp.entrySet()) {
            if (it.getValue() == 1) {
                return it.getKey();
            }
        }

        // This line will never execute
        // if the array contains a single element.
        return -1;


    }

    private static int getSingleElementByXoring(int[] arr, int n) {
        // XOR all the elements:
        int xorr = 0;
        for (int i = 0; i < n; i++) {
            xorr = xorr ^ arr[i];
        }
        return xorr;
    }

}

package hashing;

import java.util.Arrays;

public class Basic_FrequencyCounter {

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 2, 5};
        frequencyCounter_Optimized(arr);
    }

    private static void frequencyCounter_Optimized(int[] arr) {
        // let say the maximum value in the arr can be 12
        int[] freq = new int[(int) Math.pow(10, 8)];
        Arrays.fill(freq, 0);
        for (int i = 0; i < arr.length; i++) {
            freq[arr[i]]++;
        }

        // let say, we want the frequency of 2 in the array, it can be computed in constant time
        System.out.println("frequency of occurance of 2: " + freq[2]);
    }

}

package dailychallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SortArrayByIncreasingFrequency_23072024 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 3, 2, 2, 2};
        nums = sortArrayByIncreasingFrequency(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static int[] sortArrayByIncreasingFrequency(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        List<Integer> list = new ArrayList<>(map.keySet());
//if nums = [2,3,1,3,2], then frequency of both 2 and 3 is same, i.e 2, so we order them in decreasing order, or else increasing order 
        Collections.sort(list, (a, b) -> {
            return (map.get(a) == map.get(b)) ? b - a : map.get(a) - map.get(b);
        });

        // list to int[]
        int[] res = new int[nums.length];
        int i = 0;
        for (int num : list) {
            for (int j = 0; j < map.get(num); j++) {
                res[i++] = num;
            }
        }
        return res;
    }

}

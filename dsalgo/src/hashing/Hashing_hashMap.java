package hashing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Hashing_hashMap {

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 3, 5, 2, 1, 9, 2, 10, 4, 22};
        frequencyCounter(arr);

    }

    private static void frequencyCounter(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        // iterate in map
        System.out.println("iterating over keys");
        for (Integer key : map.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();

        System.out.println("iterating over values");
        for (Integer value : map.values()) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("iterating over key-value pairs");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey() + " : " + entry.getValue());
            System.out.println();
        }
        System.out.println();

        System.out.println("using iterator");
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            System.out.print(entry.getKey() + " : " + entry.getValue());
            System.out.println();
        }
        System.out.println();

        System.out.println("using stream api");
        map.entrySet().stream().forEach(entry -> {
            System.out.print(entry.getKey() + " : " + entry.getValue());
            System.out.println();
        });
    }
}

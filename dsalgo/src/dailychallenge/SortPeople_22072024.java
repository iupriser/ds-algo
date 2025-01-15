package dailychallenge;

import java.util.Arrays;
import java.util.HashMap;

public class SortPeople_22072024 {

    public static void main(String[] args) {
        String[] names = {"Mary", "John", "Emma"};
        int[] heights = {180, 165, 170};
        String[] sortedPeople = sortPeople_Brute(names, heights);
        sortedPeople = sortPeople_Optimized(names, heights);
        Arrays.stream(sortedPeople).forEach(p -> System.out.print(p + " "));
    }

    private static String[] sortPeople_Brute(String[] names, int[] heights) {
        return null;
    }

    private static String[] sortPeople_Optimized(String[] names, int[] heights) {
        int n = heights.length;
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(heights[i], names[i]);
        }

        Arrays.sort(heights);
        for (int i = 0; i < n / 2; i++) {
            int tmp = heights[i];
            heights[i] = heights[n - i - 1];
            heights[n - i - 1] = tmp;
        }

//		heights = Arrays.stream(heights).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue)
//				.toArray();
        for (int i = 0; i < n; ++i) {
            names[i] = map.get(heights[i]);
        }

        return names;

    }

}

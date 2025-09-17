package greedy.medium_hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertIntervals {
    // TC: O(N), SC:O(N)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> result = new ArrayList<>();
        // left--overlapping--right
        int i = 0;
        // left, end of currentInterval should be less than start of newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i += 1;
        }
        // overlapping, as inverse of below condition, start of currInterval greater than end of newInterval holds true for Right section
        // starting of currInterval should be before ending of newInterval
        while (i < n && intervals[i][0] < newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i += 1;
        }
        result.add(newInterval);
        // right
        while (i < n) {
            result.add(intervals[i]);
            i += 1;
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        // intervals are sorted by starting of interval in ascending, and test cases are s.t. there are no
        // overlapping intervals
        int[][] intervals = {{1, 2}, {3, 4}, {5, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {6, 8};
        InsertIntervals obj = new InsertIntervals();
        int[][] afterInsertionIntervals = obj.insert(intervals, newInterval);
        Arrays.stream(afterInsertionIntervals).forEach(interval -> System.out.print(Arrays.toString(interval) + " "));
    }
}

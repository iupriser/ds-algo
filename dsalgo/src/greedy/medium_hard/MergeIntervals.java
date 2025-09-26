package greedy.medium_hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public int[][] mergeBrute(int[][] intervals) {
        int n = intervals.length;
        // grouping close intervals by sorting the given array(sorting based on starting point)
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            // skip all merged intervals:
            if (!ans.isEmpty() && end <= ans.getLast()[1]) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                // overlapping
                if (intervals[j][0] <= end) {
                    end = Math.max(end, intervals[j][1]);
                } else {
                    break;
                }
            }
            ans.add(new int[]{start, end});
        }
        return ans.toArray(new int[0][]);
    }

    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n < 2) return intervals;
        // grouping close intervals by sorting the given array(sorting based on starting point)
        /*
        Arrays.sort(intervals, new Comparator<>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        */
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> mergedIntervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // when start of currInterval is greater than end of interval last stored in mergedIntervals
            if (mergedIntervals.isEmpty() || intervals[i][0] > mergedIntervals.getLast()[1]) {
                mergedIntervals.add(intervals[i]);
            }
            // if currentInterval lies in the last interval
            else {
                mergedIntervals.getLast()[1] = Math.max(mergedIntervals.getLast()[1], intervals[i][1]);
            }
        }
        return mergedIntervals.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 9}, {9, 11}, {8, 10}, {2, 4}, {15, 18}, {16, 17}};
        MergeIntervals obj = new MergeIntervals();
        int[][] mergedIntervalsBrute = obj.mergeBrute(intervals);
        int[][] mergedIntervalsOptimal = obj.merge(intervals);

        Arrays.stream(mergedIntervalsBrute).forEach(row -> System.out.print(Arrays.toString(row) + " "));
        System.out.println("\n-------------------");
        Arrays.stream(mergedIntervalsOptimal).forEach(row -> System.out.print(Arrays.toString(row) + " "));
    }
}

package greedy.medium_hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class NonOverlappingIntervals {
    /**
     * array of intervals where intervals[i] = [starti, endi],
     * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
     * inverse of "N meetings in one room"
     * ans =  total - (maximum number of meetings can be done in a single room)
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int totalMeetings = intervals.length;
        List<Meeting> meets = new ArrayList<>();
        for (int i = 0; i < totalMeetings; i++) {
            meets.add(new Meeting(intervals[i][0], intervals[i][1], i + 1));
        }
        // sorting based on end time
        meets.sort(Comparator.comparingInt(a -> a.endTime));

        int cntMaxMeetingInOneRoom = 0;
        int currtime = Integer.MIN_VALUE;
        for (Meeting meet : meets) {
            // Check if the meeting room is free at the start time of the meeting
            // start time is greater than or equal the end time of the previously selected meeting
            if (meet.startTime >= currtime) {
                cntMaxMeetingInOneRoom++;
                currtime = meet.endTime;
            }
        }
        return totalMeetings - cntMaxMeetingInOneRoom;
    }

    /**
     * the idea is to use a greedy approach to select the interval to be removed, such that removal count is minimized.
     * First we sort the intervals by their starting values. Then traverse through the interval array and
     * check if any interval has a starting point smaller than the ending point of the previous interval
     * (ie. there is an overlap). Incase overlapping occurs remove the interval with greater ending point.
     * O(n*log n) Time and O(1) Space
     */
    public int minRemovalWay2(int[][] intervals) {
        int cnt = 0;

        // Sort by minimum starting point
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {

            // If the current starting point is less than
            // the previous interval's ending point
            // (ie. there is an overlap)
            if (intervals[i][0] < end) {

                // Increase cnt and remove the interval
                // with the higher ending point
                cnt++;
                end = Math.min(intervals[i][1], end);
            }

            // Incase of no overlapping, this interval is
            // not removed and 'end' will be updated
            else
                end = intervals[i][1];
        }

        return cnt;
    }

    public int eraseOverlapIntervalsWay2(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int prevEnd = intervals[0][1];
        int remove = 0, start;
        for (int i = 1; i < n; i++) {
            start = intervals[i][0];
            if (start >= prevEnd) {
                prevEnd = intervals[i][1];
            } else remove++;
        }
        return remove;
    }

    public static void main(String[] args) {
//        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {-100, -2}, {5, 7}};
        NonOverlappingIntervals obj = new NonOverlappingIntervals();
        System.out.println("Minimum number of intervals needs to be removed to make the rest of intervals non-overlapping: " + obj.eraseOverlapIntervals(intervals));
    }
}

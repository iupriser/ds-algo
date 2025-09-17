package greedy.medium_hard.meetings;

import java.util.Arrays;

//https://algo.monster/liteproblems/252
public class MeetingRooms {
    /**
     * Determines if a person can attend all meetings without any time conflicts.
     *
     * @param intervals 2D array where each element is [startTime, endTime] of a meeting
     * @return true if all meetings can be attended (no overlaps), false otherwise
     * TC: O(nlog n) + O(n) = O(nlog n).
     */
    public boolean canAttendMeetings(int[][] intervals) {
        // By sorting the intervals by start time first, we create a natural timeline of meetings.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        /**
         * Once sorted, checking for overlaps becomes straightforward - we only need to compare adjacent meetings.
         * For any two consecutive meetings in the sorted list, if the first meeting ends after the second meeting
         * starts (i.e., end_time_of_first > start_time_of_second), we have an overlap.
         */
        // Check for overlapping meetings
        for (int i = 1; i < intervals.length; i++) {
            // If previous meeting's end time is after current meeting's start time,
            // there is an overlap
            if (intervals[i - 1][1] > intervals[i][0]) {
                return false;
            }
        }
        // No overlaps found, all meetings can be attended
        return true;
    }

    public static void main(String[] args) {
        int[][] intervals = {{7, 10}, {2, 4}, {8, 12}};
        MeetingRooms obj = new MeetingRooms();
        System.out.println("can attend all the meetings: " + obj.canAttendMeetings(intervals));
    }
}

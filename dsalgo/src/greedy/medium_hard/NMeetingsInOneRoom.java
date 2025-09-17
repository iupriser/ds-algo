package greedy.medium_hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The idea is to use greedy approach to always choose the meeting whose
 * <p>
 * start time is greater than the end time of the previously selected meeting and
 * end time is the smallest among all the remaining meetings. This is because, smaller the end time, sooner the meeting will end and the meeting room will become available for the next meeting.
 * So, we can sort the meetings according to their end time so that we always choose the meeting which has minimum end time.
 */
public class NMeetingsInOneRoom {
    public List<Integer> maxMeetings(int[] start, int[] end) {
        List<Integer> res = new ArrayList<>();
        int n = start.length;
        // Store details of all meetings in a list
        List<Meeting> meets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            meets.add(new Meeting(start[i], end[i], i + 1));
        }
        // Sort the meetings according to the ending time
        meets.sort(new MeetingComparator());
        // Initialize current time as -1
        int currTime = Integer.MIN_VALUE;
        for (Meeting meeting : meets) {

            // Check if the meeting room is free at the start time of the meeting
            // start time is greater than the end time of the previously selected meeting
            if (meeting.startTime > currTime) {
                currTime = meeting.endTime;
                res.add(meeting.pos);
            }
        }

        // Sort result array
        res.sort(Integer::compareTo);
        return res;
    }

    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};

        List<Integer> res = new NMeetingsInOneRoom().maxMeetings(start, end);
        for (int meeting : res) {
            System.out.print(meeting + " ");
        }
    }
}

// Custom comparator to compare meetings according to end time
class MeetingComparator implements Comparator<Meeting> {
    @Override
    public int compare(Meeting m1, Meeting m2) {
        return Integer.compare(m1.endTime, m2.endTime);
    }
}

class Meeting {
    int startTime, endTime, pos;

    Meeting(int startTime, int endTime, int pos) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.pos = pos;
    }
}
package greedy.medium_hard;

import java.util.Arrays;

// https://www.geeksforgeeks.org/dsa/program-for-shortest-job-first-or-sjf-cpu-scheduling-set-1-non-preemptive/
public class shortestJobFirstCpuScheduling {
    private int shortestJobFirst(int[] jobs) {
        int n = jobs.length;
        Arrays.sort(jobs);
        int[] waitArr = new int[n];
        int totalWaitTime = 0;
        int totalTime = 0;
        for (int i = 0; i < n; i++) {
            waitArr[i] = totalTime;
            totalTime += jobs[i];
        }
        for (int waitTime : waitArr) {
            totalWaitTime += waitTime;
        }
//        for (int job : jobs) {
//            totalWaitTime = totalWaitTime + totalTime;
//            totalTime = totalTime + job;
//        }
        return totalWaitTime / n;
    }

    public static void main(String[] args) {
        int[] jobs = {4, 3, 7, 1, 2};
        shortestJobFirstCpuScheduling obj = new shortestJobFirstCpuScheduling();
        System.out.println("Average waiting time: " + obj.shortestJobFirst(jobs));

    }
}

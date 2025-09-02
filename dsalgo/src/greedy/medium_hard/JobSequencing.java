package greedy.medium_hard;

import graph.mst.DisjointSet;

import java.util.*;

public class JobSequencing {
    public ArrayList<Integer> jobSchedulingBrute1(int[] deadline, int[] profit) {
        int n = deadline.length;
        // total jobs count which is done
        int cnt = 0;
        // total profit earned
        int totalProfit = 0;

        // pair the profit and deadline of all the jobs together
        ArrayList<int[]> jobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            jobs.add(new int[]{profit[i], deadline[i]});
        }

        // sort the jobs based on the profit in decreasing order
        jobs.sort((a, b) -> Integer.compare(b[0], a[0]));

        // array to check slot for the job
        int[] slot = new int[n + 1];
        Arrays.fill(slot, -1);

        for (int i = 0; i < n; i++) {
            // for each job, check if it can be done on its last day?
            // iterating to find the first day that is empty
            for (int j = jobs.get(i)[1]; j > 0; j--) {
                // if slot is empty
                if (slot[j] == -1) {
                    slot[j] = i;
                    cnt++;
                    totalProfit += jobs.get(i)[0];
                    break;
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.add(cnt);
        result.add(totalProfit);
        return result;
    }

    /**
     * Time Complexity: O(N log N) + O(N*M).
     * O(N log N ) for sorting the jobs in decreasing order of profit.
     * O(N*M) since we are iterating through all N jobs and for every job we are checking from the last deadline,
     * say M deadlines in the worst case.
     * <p>
     * Space Complexity: O(M) for an array that keeps track of which day job is performed
     * if M is the maximum deadline available.
     *
     * @return array of size 2 having the 0th element equal to the count
     * and 1st element equal to the maximum profit
     */
    public int[] jobScheduling(Job arr[], int n) {
        Arrays.sort(arr, (a, b) -> (b.profit - a.profit));
        // get the maximum deadline to create an array of slots
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i].deadline > maxDeadline) {
                maxDeadline = arr[i].deadline;
            }
        }

        int slots[] = new int[maxDeadline + 1];
        // jobs are not performed yet
        for (int i = 1; i <= maxDeadline; i++) {
            slots[i] = -1;
        }

        int countJobs = 0, jobProfit = 0;

        // iterate over all the jobs
        for (int i = 0; i < n; i++) {
            // for each job, check if it can be done on its last day?
            // iterating to find the first day that is empty
            for (int j = arr[i].deadline; j > 0; j--) {

                // Free slot found
                if (slots[j] == -1) {
                    slots[j] = i;
                    countJobs++;
                    jobProfit += arr[i].profit;
                    break;
                }
            }
        }

        int ans[] = new int[2];
        ans[0] = countJobs;
        ans[1] = jobProfit;
        return ans;
    }

    /**
     * sort the jobs based on their deadlines in ascending order. This ensures that jobs with earlier deadlines are
     * processed first, preventing situations where a job with a short deadline remains unscheduled because a job with
     * a later deadline was chosen instead. We use a min-heap to keep track of the selected jobs, allowing us to
     * efficiently replace lower-profit jobs when a more profitable job becomes available.
     * O(n * log(n)) Time and O(n) Space
     */
    public ArrayList<Integer> jobSequencingBetter_PQ(int[] deadline, int[] profits) {
        int n = deadline.length;
        ArrayList<Integer> ans = new ArrayList<>(Arrays.asList(0, 0));
        List<int[]> jobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            jobs.add(new int[]{deadline[i], profits[i]});
        }
        // sorting ascending based on deadlines
        jobs.sort(Comparator.comparingInt(a -> a[0]));
        // Min-heap to maintain the scheduled jobs based on profit, profit of job gets inserted into PQ
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] job : jobs) {
            // If job can be scheduled within its deadline, job[0]--> deadline, job[1]--> profit
            if (job[0] > pq.size()) {
                pq.add(job[1]);
            }

            // Replace the job with the lowest profit
            else if (!pq.isEmpty() && pq.peek() < job[1]) {
                pq.poll();
                pq.add(job[1]);
            }
        }

        while (!pq.isEmpty()) {
            ans.set(1, ans.get(1) + pq.poll());
            ans.set(0, ans.get(0) + 1);
        }

        return ans;
    }

    public ArrayList<Integer> jobSequencingOptimal_DSU(int[] deadline, int[] profit) {
        int n = deadline.length;
        ArrayList<Integer> ans = new ArrayList<>(Arrays.asList(0, 0));


        // pair the profit and deadline of all the jobs together
        ArrayList<int[]> jobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            jobs.add(new int[]{profit[i], deadline[i]});
        }

        // sort the jobs based on the profit in decreasing order
        jobs.sort((a, b) -> Integer.compare(b[0], a[0]));

        // Find maximum deadline
        int d = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            d = Math.max(d, deadline[i]);
        }

        // create a disjoint set of d nodes, 0,1...d
        DisjointSet ds = new DisjointSet(d);
        // Traverse through all the jobs
        for (int i = 0; i < n; i++) {

            // Find the maximum available free slot for
            // this job (corresponding to its deadline)
            int currJobDeadline = jobs.get(i)[1];
            int slots = ds.findUPar(currJobDeadline);

            // If maximum available free slot is greater
            // than 0, then free slot available
            if (slots > 0) {
                // update the greatest free slot.
                ds.unionBySize(ds.findUPar(slots - 1), slots);

                // update answer
                ans.set(1, ans.get(1) + jobs.get(i)[0]);
                ans.set(0, ans.get(0) + 1);
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        int[] deadline = {4, 1, 2, 2}, profit = {20, 10, 40, 30};
        JobSequencing obj = new JobSequencing();
        ArrayList<Integer> result = obj.jobSchedulingBrute1(deadline, profit);
        System.out.println("total number of jobs: " + result.get(0) + ", total profit earned: " + result.get(1) + "\n");
        result = obj.jobSequencingBetter_PQ(deadline, profit);
        System.out.println("total number of jobs: " + result.get(0) + ", total profit earned: " + result.get(1) + "\n");
        result = obj.jobSequencingOptimal_DSU(deadline, profit);
        System.out.println("total number of jobs: " + result.get(0) + ", total profit earned: " + result.get(1) + "\n");


        Job[] arr = new Job[4];
        arr[0] = new Job(1, 4, 20);
        arr[1] = new Job(2, 1, 10);
        arr[2] = new Job(3, 2, 40);
        arr[3] = new Job(4, 2, 30);

        obj = new JobSequencing();
        int[] res = obj.jobScheduling(arr, 4);
        System.out.println("total number of jobs: " + res[0] + ", total profit earned: " + res[1]);

    }
}

class Job {
    int id, deadline, profit;

    public Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

package dp.twoDim_theeDim_grids;

import java.util.Arrays;

public class NinjaTraining {
    private int[][] dp;

    public NinjaTraining(int days) {
        // 0-running,1-fighting,2-sword-ship,3-no task
        dp = new int[days][4];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
    }

    private int ninjaTrainingRec(int nthDay, int lastPerformedTask, int[][] points) {
        // base case
        if (nthDay == 0) {
            int maxi = 0;
            for (int task = 0; task <= 2; task++) {
                if (task != lastPerformedTask) {
                    maxi = Math.max(maxi, points[0][task]);
                }
            }
            return maxi;
        }
        int maxPoints = 0;
        for (int task = 0; task <= 2; task++) {
            if (task != lastPerformedTask) {
                int currMaxPoints = points[nthDay][task] + ninjaTrainingRec(nthDay - 1
                        , task, points);
                maxPoints = Math.max(maxPoints, currMaxPoints);
            }
        }
        return maxPoints;
    }

    private int ninjaTrainingMemoization(int nthDay, int lastPerformedTask, int[][] points) {
        // base case
        if (nthDay == 0) {
            int maxi = 0;
            for (int task = 0; task <= 2; task++) {
                if (task != lastPerformedTask) {
                    maxi = Math.max(maxi, points[0][task]);
                }
            }
            return dp[0][lastPerformedTask] = maxi;
        }
        if (dp[nthDay][lastPerformedTask] != -1) return dp[nthDay][lastPerformedTask];
        int maxPoints = 0;
        for (int task = 0; task <= 2; task++) {
            if (task != lastPerformedTask) {
                int currMaxPoints = points[nthDay][task] + ninjaTrainingMemoization(nthDay - 1, task, points);
                maxPoints = Math.max(maxPoints, currMaxPoints);
            }
        }
        return dp[nthDay][lastPerformedTask] = maxPoints;
    }

    private int ninjaTrainingTabulation(int days, int[][] points) {
        // base case

        // day 0, when last performed task is 0 on day 1
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        // day 0, when last performed task is 1 on day 1
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        // day 0, when last performed task is 2 on day 1
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        // day 0, when last performed task is 3 on day 1
        dp[0][3] = Math.max(Math.max(points[0][0], points[0][1]), points[0][2]);

        // iterate over each day, starting from day=1 as we have already got the values for day=0
        for (int day = 1; day < days; day++) {
            // for each day, check for each possible last task (0,1,2,3)
            for (int lastPerformedTask = 0; lastPerformedTask < 4; lastPerformedTask++) {
                // as we cannot select the same task on consecutive days (day-1 and day)
                dp[day][lastPerformedTask] = 0;
                // Iterate through the tasks (activities) for the current day
                for (int task = 0; task < 3; task++) {
                    // If the current task is different from the last task, we can perform this task
                    if (task != lastPerformedTask) {
                        // Calculate the points for the current activity and add it to the maximum points obtained
                        // on the previous day with the previous task
                        int currMaxPoints = points[day][task] + dp[day - 1][task];
                        // Update the maximum points for the current day and last activity
                        dp[day][lastPerformedTask] = Math.max(dp[day][lastPerformedTask], currMaxPoints);
                    }
                }
            }
        }
        return dp[days - 1][3];
    }

    private int ninjaTrainingSpaceOptimization(int days, int[][] points) {
        // base case
        int[] prev = new int[4];
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(Math.max(points[0][0], points[0][1]), points[0][2]);

        // iterate over each day, starting from day=1 as we have already got the values for day=0
        for (int day = 1; day < days; day++) {
            int[] temp = new int[4];
            // for each day, check for each possible last task (0,1,2,3)
            for (int lastPerformedTask = 0; lastPerformedTask < 4; lastPerformedTask++) {
                // as we cannot select the same task on consecutive days (day-1 and day)
                temp[lastPerformedTask] = 0;
                // Iterate through the tasks (activities) for the current day
                for (int task = 0; task < 3; task++) {
                    // If the current task is different from the last task, we can perform this task
                    if (task != lastPerformedTask) {
                        // Calculate the points for the current activity and add it to the maximum points obtained
                        // on the previous day with the previous task
                        int currMaxPoints = points[day][task] + prev[task];
                        // Update the maximum points for the current day and last activity
                        temp[lastPerformedTask] = Math.max(temp[lastPerformedTask],
                                currMaxPoints);
                    }
                }
            }
            prev = temp;
        }
        return prev[3];
    }

    public static void main(String[] args) {
        int[][] points = {{1, 2, 5}, {3, 1, 1}, {3, 3, 3}};
        int numOfDays = points.length;
        NinjaTraining obj = new NinjaTraining(numOfDays);
        // zero based indexing
        int ninjaTrainingRec = obj.ninjaTrainingRec(numOfDays - 1, 3, points);
        System.out.println("maximum point after training: " + ninjaTrainingRec);
        int ninjaTrainingMemoization = obj.ninjaTrainingMemoization(numOfDays - 1, 3, points);
        System.out.println("maximum point after training: " + ninjaTrainingMemoization);
        obj = new NinjaTraining(numOfDays);
        int ninjaTrainingTabulation = obj.ninjaTrainingTabulation(numOfDays, points);
        System.out.println("maximum point after training: " + ninjaTrainingTabulation);
        int ninjaTrainingSpaceOptimization = obj.ninjaTrainingSpaceOptimization(numOfDays, points);
        System.out.println("maximum point after training: " + ninjaTrainingSpaceOptimization);
    }
}

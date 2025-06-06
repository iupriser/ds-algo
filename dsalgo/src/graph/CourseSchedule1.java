package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/course-schedule/description/
public class CourseSchedule1 {
    public boolean canFinish(int N, int[][] prerequisites) {
        // creating adj from prerequisites[][]
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }
        int m = prerequisites.length;
        for (int i = 0; i < m; i++) {
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        int[] indegree = new int[N];
        for (int i = 0; i < N; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        Queue<Integer> q = new LinkedList<Integer>();

        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        int index = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            index++;
            for (int neighbour : adj.get(node)) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) {
                    q.add(neighbour);
                }
            }
        }
        // contains cycle
        if (index != N) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CourseSchedule1 obj = new CourseSchedule1();
        int n = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        boolean canFinish = obj.canFinish(n, prerequisites);
        System.out.print("can finish all courses: " + canFinish);
    }
}

package graph.toposort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/course-schedule-ii/description/
public class CourseSchedule2 {
    public int[] findOrder(int n, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        int m = prerequisites.length;
        for (int i = 0; i < m; i++) {
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        int[] inorder = new int[n];
        for (int i = 0; i < n; i++) {
            for (int it : adj.get(i)) {
                inorder[it]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inorder[i] == 0) {
                q.add(i);
            }
        }

        int[] path = new int[n];
        int index = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            path[index++] = node;

            for (int it : adj.get(node)) {
                inorder[it]--;
                if (inorder[it] == 0) {
                    q.add(it);
                }
            }
        }

        if (n != index) {
            return new int[0];
        }

        return path;
    }

    public static void main(String[] args) {
        CourseSchedule2 obj = new CourseSchedule2();
        int n = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] order = obj.findOrder(n, prerequisites);
        if (order.length == 0) {
            System.out.println("it is impossible to finish all courses");
        } else {
            System.out.println("ordering of course");
            for (int node : order) {
                System.out.print(node + " ");
            }
        }
    }
}

package binaryTree.mediumProblem;

import binaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

import static binaryTree.TreeNode.createBinaryTree;
//https://leetcode.com/problems/maximum-width-of-binary-tree/
public class MaxWidthOfBT {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        System.out.println("Maximum width of binary tree: " + maxWidth(root));
    }

    private static int maxWidth(TreeNode root) {
        if (root == null) return 0;
        int ans = 0;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        while (!q.isEmpty()) {
            int size = q.size();
            int minIndexAtCurrentLevel = q.peek().index;
            int firstNodeIndex = 0, lastNodeIndex = 0;
            for (int i = 0; i < size; i++) {
                Pair p = q.poll();
                TreeNode node = p.node;
                int curr_index = p.index - minIndexAtCurrentLevel;
                if (i == 0) {
                    firstNodeIndex = curr_index;
                }
                if (i == size - 1) {
                    lastNodeIndex = curr_index;
                }
                if (node.left != null) {
                    q.offer(new Pair(node.left, 2 * curr_index + 1));
                }
                if (node.right != null) {
                    q.offer(new Pair(node.right, 2 * curr_index + 2));
                }
            }
            ans = Math.max(ans, lastNodeIndex - firstNodeIndex + 1);
        }
        return ans;
    }
}

class Pair {
    TreeNode node;
    int index;

    public Pair(TreeNode node, int index) {
        this.node = node;
        this.index = index;
    }
}

package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        left = right = null;
    }

    public TreeNode createBinaryTree(int[] arr) {
        return null;
    }

    public static void printBinaryTree(TreeNode root) {
        // print in level order
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            int elementsInCurrentLevel = queue.size();
            for (int i = 0; i < elementsInCurrentLevel; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.data + " ");
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            System.out.println();
        }
    }
}

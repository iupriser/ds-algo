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

    public static TreeNode createBinaryTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(8);
        root.right.right.right = new TreeNode(9);

        return root;
    }

    public static TreeNode createBinarySearchTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);
        return root;
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
        System.out.println("------------------------------------------------------------------");
    }
}

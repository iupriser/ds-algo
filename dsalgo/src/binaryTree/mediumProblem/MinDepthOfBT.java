package binaryTree.mediumProblem;

import binaryTree.TreeNode;

public class MinDepthOfBT {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        int minDepthOfBT = minDepth(root);
        System.out.println("Minimum depth of Binary Tree: " + minDepthOfBT);
    }

    private static int minDepth(TreeNode root) {
        // leaf node
        if (root.left == null && root.right == null) return 1;
        else if (root.left != null && root.right != null) {
            int lh = minDepth(root.left) + 1;
            int rh = minDepth(root.right) + 1;
            return Math.min(lh, rh);
        } else if (root.left != null) {
            return 1 + minDepth(root.left);
        } else {
            return 1 + minDepth(root.right);
        }

    }

    private static TreeNode createBinaryTree() {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(5);
        root.left.right.left.right = new TreeNode(6);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(2);
        root.right.right.left = new TreeNode(1);
        root.right.right.left.right = new TreeNode(4);
        root.right.right.left.right.left = new TreeNode(3);
        root.right.right.left.right.right = new TreeNode(6);
        root.right.right.left.right.right.right = new TreeNode(2);

        return root;
    }
}

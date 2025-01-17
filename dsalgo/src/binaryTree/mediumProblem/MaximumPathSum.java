package binaryTree.mediumProblem;

import binaryTree.TreeNode;

import static binaryTree.TreeNode.printBinaryTree;

public class MaximumPathSum {
    public static void main(String[] args) {
        TreeNode root = createBinaryTreeWithSomeNegativeNodes();
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        System.out.println("Maximum Path Sum of Binary Tree: " + maxPathSum(root));
    }

    //    TC: O(n) SC:O(1)
    private static int maxPathSum(TreeNode root) {
        int[] maxValue = new int[1];
        maxValue[0] = Integer.MIN_VALUE;
        maxPath(root, maxValue);
        return maxValue[0];
    }

    private static int maxPath(TreeNode root, int[] maxValue) {
        if (root == null)
            return 0;
        int leftSum = Math.max(0, maxPath(root.left, maxValue));
        int rightSum = Math.max(0, maxPath(root.right, maxValue));

        maxValue[0] = Math.max(maxValue[0], root.data + leftSum + rightSum);

        return root.data + Math.max(leftSum, rightSum);
    }

    private static TreeNode createBinaryTreeWithSomeNegativeNodes() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }
}

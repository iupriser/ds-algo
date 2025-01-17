package binaryTree.mediumProblem;

import binaryTree.TreeNode;

import static binaryTree.TreeNode.printBinaryTree;

//https://leetcode.com/problems/symmetric-tree/description/
public class SymmetricalTree {
    public static void main(String[] args) {
        TreeNode root = createSymmetricBinaryTree();
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        System.out.println("Is binary tree is symmetric tree: " + isSymmetrical(root));
    }

    //TC:O(N) SC:O(N)
    private static boolean isSymmetrical(TreeNode root) {
        return root == null || isSymmetricalHelper(root.left, root.right);
    }

    private static boolean isSymmetricalHelper(TreeNode left, TreeNode right) {
        if (left == null || right == null) return left == right;

        if (left.data != right.data) return false;

        return isSymmetricalHelper(left.left, right.right) && isSymmetricalHelper(left.right,
                right.left);
    }

    private static TreeNode createSymmetricBinaryTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        return root;
    }
}

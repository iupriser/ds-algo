package binaryTree.mediumProblem;

import binaryTree.TreeNode;

//https://leetcode.com/problems/same-tree/description/
public class IsIdenticalBinaryTree {
    public static void main(String[] args) {
        // Node1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);

        // Node2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);

        System.out.println("Is both Binary Tree are identical: " + isIdentical(root1, root2));
    }

    private static boolean isIdentical(TreeNode p, TreeNode q) {
        // If both nodes are NULL, they are identical
        if (p == null && q == null) {
            return true;
        }
        // If only one of the nodes is NULL, they are not identical
        if (p == null || q == null) {
            return false;
        }
        // Check if the current nodes have the same data value
        // and recursively check their left and right subtrees
        return ((p.data == q.data) && isIdentical(p.left, q.left) && isIdentical(p.right, q.right));
    }
}

package bst;

import binaryTree.TreeNode;

import static binaryTree.TreeNode.createBinarySearchTree;

//https://leetcode.com/problems/validate-binary-search-tree/description/
public class ValidateBST {
    public static void main(String[] args) {
        TreeNode root = createBinarySearchTree();
        System.out.println("is BST is valid : " + isValidBST(root));
    }

    private static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBSTHelper(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        // check for invalid node
        if (root.data >= maxVal || root.data <= minVal) return false;
        return isValidBSTHelper(root.left, minVal, root.data) && isValidBSTHelper(root.right, root.data, maxVal);
    }
}

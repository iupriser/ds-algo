package binaryTree.mediumProblem;

import binaryTree.TreeNode;

public class DiameterOfBT {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        // brute approach : O(N2)
        int diameterOfBinaryTree = diameterBrute(root);
        System.out.println("diameter of Binary tree: " + diameterOfBinaryTree);
        // Optimal1:TC:O(N) SC:O(H)
        root = createBinaryTree();
        diameterOfBinaryTree = diameterOptimal1(root);
        System.out.println("diameter of Binary tree: " + diameterOfBinaryTree);
    }

    private static int diameterBrute(TreeNode root) {
        if (root == null) return 0;
        // not adding 1 to comp1 as we are finding the longest length {number of edges, not the nodes}
        int comp1 = getHeight(root.left) + getHeight(root.right);
        int comp2 = diameterBrute(root.left);
        int comp3 = diameterBrute(root.right);

        return Math.max(comp1, Math.max(comp2, comp3));
    }

    private static int diameterOptimal1(TreeNode node) {
        int[] res = new int[1];
        diameterRecur(node, res);
        return res[0];
    }

    private static int diameterRecur(TreeNode node, int[] res) {
        // Base case: tree is empty
        if (node == null) return 0;
        int lh = diameterRecur(node.left, res);
        int rh = diameterRecur(node.right, res);
        res[0] = Math.max(res[0], lh + rh);
        return 1 + Math.max(lh, rh);
    }

    static int getHeight(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
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

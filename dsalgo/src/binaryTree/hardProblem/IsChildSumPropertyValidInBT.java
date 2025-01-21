package binaryTree.hardProblem;

import binaryTree.TreeNode;

import static binaryTree.TreeNode.printBinaryTree;

public class IsChildSumPropertyValidInBT {
    public static void main(String[] args) {
        TreeNode root = createCSP_validBT();
        boolean isCSP_Followed = isChildSumProperty(root) == 1;
        System.out.println("Is Binary Tree follows Child Sum Property: " + isCSP_Followed);
        root = createBT_thatFollowSCP();
        System.out.println("--Original Binary Tree--");
        printBinaryTree(root);
        convertBT_ToFollowCSP(root);
        System.out.println("--Binary Tree after conversion--");
        printBinaryTree(root);
    }

    private static void convertBT_ToFollowCSP(TreeNode root) {
        if (root == null) return;
        int childSum = 0;
        if (root.left != null) {
            childSum += root.left.data;
        }
        if (root.right != null) {
            childSum += root.right.data;
        }
        if (childSum >= root.data) {
            root.data = childSum;
        } else {
            if (root.left != null) root.left.data = root.data;
            if (root.right != null) root.right.data = root.data;
        }

        convertBT_ToFollowCSP(root.left);
        convertBT_ToFollowCSP(root.right);

        int total = 0;
        if (root.left != null) total += root.left.data;
        if (root.right != null) total += root.right.data;
        if (root.left != null || root.right != null) root.data = total;
    }

    private static int isChildSumProperty(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 1;
        }
        int sum = 0;
        if (root.left != null) {
            sum += root.left.data;
        }
        if (root.right != null) {
            sum += root.right.data;
        }

        return ((root.data == sum)
                && (isChildSumProperty(root.left) == 1)
                && (isChildSumProperty(root.right) == 1)) ? 1 : 0;
    }

    private static TreeNode createCSP_validBT() {
        TreeNode root = new TreeNode(45);
        root.left = new TreeNode(35);
        root.left.left = new TreeNode(30);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(2);
        return root;
    }

    private static TreeNode createBT_thatFollowSCP() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(35);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(2);
        return root;
    }
}

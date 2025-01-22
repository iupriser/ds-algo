package binaryTree.hardProblem;

import binaryTree.TreeNode;

import static binaryTree.TreeNode.printBinaryTree;

public class countNodesInCompleteBT {
    public static void main(String[] args) {
        TreeNode root = createCompleteBinaryTree();
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        int nodes = countNodesInCompleteBT_brute(root);
        System.out.println("number of nodes in complete binary tree: " + nodes);
        nodes = countNodesInCompleteBT_optimal(root);
        System.out.println("number of nodes in complete binary tree: " + nodes);
    }

    private static int countNodesInCompleteBT_brute(TreeNode root) {
        if (root == null) return 0;
        int[] count = new int[1];
        inorder(root, count);
        return count[0];
    }

    private static void inorder(TreeNode root, int[] count) {
        if (root == null) return;
        count[0]++;
        inorder(root.left, count);
        inorder(root.right, count);
    }

    private static int countNodesInCompleteBT_optimal(TreeNode root) {
        if (root == null) return 0;
        int left = getLeftHeight(root);
        int right = getRightHeight(root);
        if (left == right) {
            return ((2 << (left)) - 1);
        } else {
            return 1 + countNodesInCompleteBT_optimal(root.left) + countNodesInCompleteBT_optimal(root.right);
        }
    }

    private static int getLeftHeight(TreeNode root) {
        if (root == null) return 0;
        int count = 0;
        while (root.left != null) {
            count++;
            root = root.left;
        }
        return count;
    }

    private static int getRightHeight(TreeNode root) {
        if (root == null) return 0;
        int count = 0;
        while (root.right != null) {
            count++;
            root = root.right;
        }
        return count;
    }

    private static TreeNode createCompleteBinaryTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);

        return root;
    }
}

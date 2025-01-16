package binaryTree.mediumProblem;

import binaryTree.TreeNode;

import static binaryTree.mediumProblem.DiameterOfBT.getHeight;

public class HeightBalanced {
    public static void main(String[] args) {
        TreeNode root = createTree();
        System.out.println("is Binary tree is Height balanced: " + isHeightBalanceBrute(root));
        //Optimal
        root = createTree();
        System.out.println("is Binary tree is Height balanced: " + isHeightBalanceOptimal(root));
    }

    private static boolean isHeightBalanceBrute(TreeNode root) {
        if (root == null) return true;
        int lh = getHeight(root.left);
        int rh = getHeight(root.right);
        return (Math.abs(rh - lh) < 2) && isHeightBalanceBrute(root.left) && isHeightBalanceBrute(root.right);
    }

    private static boolean isHeightBalanceOptimal(TreeNode root) {
        return dfsHeight(root) != -1;
    }

    private static int dfsHeight(TreeNode root) {
        if (root == null) return 0;
        int lh = dfsHeight(root.left);
        int rh = dfsHeight(root.right);
        if (Math.abs(rh - lh) > 1) return -1;
        return 1 + Math.max(lh, rh);
    }

    private static TreeNode createTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.left = new TreeNode(7);
        root.right.right.left.right = new TreeNode(8);
        return root;
    }
}

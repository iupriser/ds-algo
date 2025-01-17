package binaryTree.mediumProblem;

import binaryTree.TreeNode;

import static binaryTree.TreeNode.createBinaryTree;
import static binaryTree.mediumProblem.DiameterOfBT.getHeight;

public class HeightBalanced {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        System.out.println("is Binary tree is Height balanced: " + isHeightBalanceBrute(root));
        //Optimal
        root = createBinaryTree();
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
}

package binaryTree.mediumProblem;

import binaryTree.TreeNode;

import static binaryTree.TreeNode.createBinaryTree;

public class CountNodes {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        int nodesInBinaryTree = countNodes(root);
        System.out.println("Number of Nodes in Binary Tree: " + nodesInBinaryTree);

        int leafNodesInBinaryTree = countLeafNodes(root);
        System.out.println("Number of Leaf Nodes in Binary Tree: " + leafNodesInBinaryTree);
    }


    private static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private static int countLeafNodes(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return countLeafNodes(root.left) + countLeafNodes(root.right);
    }
}

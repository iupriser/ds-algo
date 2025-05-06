package bst;

import binaryTree.TreeNode;

import static binaryTree.TreeNode.createBinarySearchTree;

class NodeValue {
    int minNode;
    int maxNode;
    int maxSize;

    public NodeValue(int minNode, int maxNode, int maxSize) {
        this.minNode = minNode;
        this.maxNode = maxNode;
        this.maxSize = maxSize;
    }
}

public class largestBST {
    public static void main(String[] args) {
        TreeNode root = createBinarySearchTree();
        int bstSubtreeSize = largestBSTSubtree(root);
        System.out.println("Size of  largest BST from Binary Tree: " + bstSubtreeSize);
    }

    private static int largestBSTSubtree(TreeNode root) {
        return largestBSTSubtreeHelper(root).maxSize;
    }

    // Post Order Traversal
    private static NodeValue largestBSTSubtreeHelper(TreeNode root) {
        if (root == null) return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        NodeValue left = largestBSTSubtreeHelper(root.left);
        NodeValue right = largestBSTSubtreeHelper(root.right);

        if (left.maxNode < root.data && root.data < right.minNode) {
            // it is a BST
            return new NodeValue(Math.min(root.data, left.minNode), Math.max(root.data,
                    right.maxNode), left.maxSize + right.maxSize + 1);
        }
        return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSize,
                right.maxSize));
    }
}

package bst;
//https://leetcode.com/problems/binary-search-tree-iterator/description/

import binaryTree.TreeNode;

import java.util.Stack;

/**
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 */
public class BSTIterator {
    private Stack<TreeNode> nextStk = new Stack<>();
    private Stack<TreeNode> beforeStk = new Stack<>();

    public BSTIterator(TreeNode root) {
        pushLeftNodes(root);
        pushRightNodes(root);
    }

    public int next() {
        TreeNode tmpNode = nextStk.pop();
        if (tmpNode.right != null) pushLeftNodes(tmpNode.right);
        return tmpNode.data;
    }

    public boolean hasNext() {
        return !nextStk.isEmpty();
    }

    public int before() {
        TreeNode tmpNode = beforeStk.pop();
        if (tmpNode.left != null) pushRightNodes(tmpNode.left);
        return tmpNode.data;
    }

    public boolean hasBefore() {
        return !beforeStk.isEmpty();
    }

    private void pushLeftNodes(TreeNode node) {
        while (node != null) {
            nextStk.push(node);
            node = node.left;
        }
    }

    private void pushRightNodes(TreeNode node) {
        while (node != null) {
            nextStk.push(node);
            node = node.right;
        }
    }
}

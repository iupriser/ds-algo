package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PreOrder {
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Getting preorder traversal
        List<Integer> result = preOrder(root);

        // Displaying the preorder traversal result
        System.out.print("Preorder Traversal: ");
        // Output each value in the
        // preorder traversal result
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    private static List<Integer> preOrder(TreeNode root) {
        List<Integer> arr = new ArrayList<>();
        preOrder(root, arr);
        return arr;
    }

    private static void preOrder(TreeNode root, List<Integer> arr) {
        if (root == null) return;
        arr.add(root.data);
        preOrder(root.left, arr);
        preOrder(root.right, arr);
    }
}

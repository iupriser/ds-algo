package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class InOrder {
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
        List<Integer> result = inOrder(root);

        // Displaying the preorder traversal result
        System.out.print("Preorder Traversal: ");
        // Output each value in the
        // preorder traversal result
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    private static List<Integer> inOrder(TreeNode root) {
        List<Integer> arr = new ArrayList<>();
        inOrder(root, arr);
        return arr;
    }

    private static void inOrder(TreeNode root, List<Integer> arr) {
        if (root == null) return;
        inOrder(root.left);
        arr.add(root.data);
        inOrder(root.right);
    }
}

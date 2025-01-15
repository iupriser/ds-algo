package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PostOrder {
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
        List<Integer> result = postOrder(root);

        // Displaying the preorder traversal result
        System.out.print("Preorder Traversal: ");
        // Output each value in the
        // preorder traversal result
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    private static List<Integer> postOrder(TreeNode root) {
        List<Integer> arr = new ArrayList<>();
        postOrder(root, arr);
        return arr;
    }

    private static void postOrder(TreeNode root, List<Integer> arr) {
        if (root == null) return;
        postOrder(root.left,arr);
        postOrder(root.right,arr);
        arr.add(root.data);
    }
}

package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static binaryTree.TreeNode.createBinaryTree;

public class PreOrderTraversal {
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = createBinaryTree();

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

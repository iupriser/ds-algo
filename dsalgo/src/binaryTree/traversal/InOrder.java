package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static binaryTree.traversal.PostOrder.createSampleBinaryTree;

public class InOrder {
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = createSampleBinaryTree();

        // Getting inorder traversal
        List<Integer> result = inOrder(root);

        // Displaying the preorder traversal result
        System.out.print("Inorder Traversal: ");
        // Output each value in the
        // inorder traversal result
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

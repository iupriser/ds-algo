package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static binaryTree.TreeNode.createBinaryTree;

public class PostOrderTraversal {
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = createBinaryTree();

        // Getting postorder traversal
        List<Integer> result = postOrder(root);

        // Displaying the postorder traversal result
        System.out.print("Postorder Traversal: ");
        // Output each value in the
        // postorder traversal result
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
        postOrder(root.left, arr);
        postOrder(root.right, arr);
        arr.add(root.data);
    }
}

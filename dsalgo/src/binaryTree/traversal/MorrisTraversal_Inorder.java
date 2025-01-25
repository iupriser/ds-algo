package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static binaryTree.TreeNode.createBinaryTree;
import static binaryTree.TreeNode.printBinaryTree;

public class MorrisTraversal_Inorder {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        printBinaryTree(root);
        List<Integer> inorderTraversal = inorderMorris(root);
        for (int nodeVal : inorderTraversal) {
            System.out.print(nodeVal + " ");
        }
    }

    private static List<Integer> inorderMorris(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                inorder.add(curr.data);
                curr = curr.right;
            } else {
                // If the left child is not NULL,
                // find the predecessor (rightmost node
                // in the left subtree)
                TreeNode prev = curr.left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }
                // If the predecessor's right child
                // is NULL, establish a temporary link
                // and move to the left child
                if (prev.right == null) {
                    prev.right = curr;
                    curr = curr.left;
                } else {
                    // If the predecessor's right child
                    // is already linked, remove the link,
                    // add current node to inorder list,
                    // and move to the right child
                    prev.right = null;
                    inorder.add(curr.data);
                    curr = curr.right;
                }
            }
        }
        return inorder;
    }
}

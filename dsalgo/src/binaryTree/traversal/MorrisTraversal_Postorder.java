package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static binaryTree.TreeNode.createBinaryTree;
import static binaryTree.TreeNode.printBinaryTree;

public class MorrisTraversal_Postorder {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        printBinaryTree(root);
        List<Integer> postorderTraversal = postorderMorris(root);
        for (int nodeVal : postorderTraversal) {
            System.out.print(nodeVal + " ");
        }
    }

    // similar to Morris traversal for Preorder except swapping between left and right node links
    // and reserving the result at end
    private static List<Integer> postorderMorris(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            if (curr.right == null) {
                postorder.add(curr.data);
                curr = curr.left;
            } else {
                // If the right child is not NULL,
                // find the leftmost node in the left subtree
                TreeNode prev = curr.right;
                while (prev.left != null && prev.left != curr) {
                    prev = prev.left;
                }
                // If the predecessor's left child
                // is NULL, establish a temporary link
                // and move to the right child
                if (prev.left == null) {
                    prev.left = curr;
                    postorder.add(curr.data);
                    curr = curr.right;
                } else {
                    // If the predecessor's left child
                    // is already linked, remove the link,
                    // add current node to postorder list,
                    // and move to the left child
                    prev.left = null;
                    curr = curr.left;
                }
            }
        }
        Collections.reverse(postorder);
        return postorder;
    }
}

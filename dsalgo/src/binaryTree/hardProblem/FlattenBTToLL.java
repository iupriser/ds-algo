package binaryTree.hardProblem;

import binaryTree.TreeNode;

import java.util.Stack;

import static binaryTree.TreeNode.createBinaryTree;
import static binaryTree.TreeNode.printBinaryTree;

//https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
public class FlattenBTToLL {
    // Initialize a global variable 'prev' to keep track of the previously processed node.
    static TreeNode prev = null;

    public static void main(String[] args) {
        //---Brute--
        TreeNode root = createBinaryTree();
        System.out.println("--Original Binary Tree--");
        printBinaryTree(root);
        flattenBrute(root);
        System.out.println("--Tree after flattening, Brute Approach--");
        printBinaryTree(root);
        //---Better--
        root = createBinaryTree();
        System.out.println("--Original Binary Tree--");
        printBinaryTree(root);
        flattenBetter(root);
        System.out.println("--Tree after flattening, Better Approach--");
        printBinaryTree(root);
        //---Optimal--
        root = createBinaryTree();
        System.out.println("--Original Binary Tree--");
        printBinaryTree(root);
        flattenOptimal(root);
        System.out.println("--Tree after flattening, Optimal Approach--");
        printBinaryTree(root);
    }

    // recursion , TC:O(N), SC:O(ln(N))
    private static void flattenBrute(TreeNode node) {
        if (node == null) return;
        flattenBrute(node.right);
        flattenBrute(node.left);
        // At this point, both left and right subtrees are flattened, and 'prev' is pointing to
        // the rightmost node in the flattened right subtree.

        // Set the right child of the current node to 'pr
        node.right = prev;
        // Set the left child of the current node to null
        node.left = null;
        // Update 'prev' to the current node for the next iteration.
        prev = node;
    }

    // stack, TC:O(N), SC:O(ln(N))
    private static void flattenBetter(TreeNode node) {
        Stack<TreeNode> stk = new Stack<>();
        stk.push(node);
        while (!stk.isEmpty()) {
            TreeNode curr = stk.pop();
            if (curr.right != null) {
                stk.push(curr.right);
            }
            if (curr.left != null) {
                stk.push(curr.left);
            }
            if (!stk.isEmpty()) {
                curr.right = stk.peek();
            }
            curr.left = null;
        }
    }

    // Morris traversal, TC:O(N), SC:O(1)
    private static void flattenOptimal(TreeNode node) {
        TreeNode curr = node;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode prev = curr.left;
                while (prev.right != null) {
                    prev = prev.right;
                }
                prev.right = curr.right;
                curr.right = curr.left;
                // Set the left child of the current node to NULL
                curr.left = null;
            }
            curr = curr.right;
        }
    }
}

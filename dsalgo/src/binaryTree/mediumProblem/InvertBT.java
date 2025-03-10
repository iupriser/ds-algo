package binaryTree.mediumProblem;

import binaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

import static binaryTree.TreeNode.createBinaryTree;
import static binaryTree.TreeNode.printBinaryTree;

public class InvertBT {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        TreeNode invertBT = invertBT(root);
        printBinaryTree(invertBT);

        System.out.println();
        root = createBinaryTree();
        invertBT = invertBTIterative(root);
        printBinaryTree(invertBT);
    }

    private static TreeNode invertBT(TreeNode root) {
        if (root == null) return root;
        invertBT(root.left);
        invertBT(root.right);
        // swapping
        TreeNode curr = root.left;
        root.left = root.right;
        root.right = curr;
        return root;
    }

    private static TreeNode invertBTIterative(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) {
            q.add(root);
        }
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr.left != null) {
                q.add(curr.left);
            }
            if (curr.right != null) {
                q.add(curr.right);
            }
            // swapping
            TreeNode tmp = curr.left;
            curr.left = curr.right;
            curr.right = tmp;
        }
        return root;
    }
}

package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static binaryTree.TreeNode.printBinaryTree;
import static binaryTree.traversal.ZigzagTraversal.createBinaryTree;

public class BoundaryTraversal {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        System.out.println("----Boundary Traversal-----");
        List<Integer> boundaryTraversal = boundaryTrav(root);
        for (Integer nodeVal : boundaryTraversal) {
            System.out.print(nodeVal + " ");
        }
    }

    private static List<Integer> boundaryTrav(TreeNode root) {
        // root, leftBoundary, leaves, rightBoundary
        List<Integer> ds = new ArrayList<>();
        // root
        if (root != null) {
            ds.add(root.data);
        } else {
            return ds;
        }
        // left Boundary
        if (root.left != null) {
            leftBoundary(root.left, ds);
        }
        // leaves
        leaves(root, ds);
        //right Boundary
        Stack<Integer> stk = new Stack<>();
        if (root.left != null) {
            rightBoundary(root.right, stk);
        }
        while (!stk.isEmpty()) {
            ds.add(stk.pop());
        }
        return ds;
    }

    private static void rightBoundary(TreeNode node, Stack<Integer> stk) {
        // terminate at leaf node
        if (node.left == null && node.right == null) return;
        stk.push(node.data);
        if (node.left != null) {
            rightBoundary(node.left, stk);
        } else {
            rightBoundary(node.right, stk);
        }

    }

    private static void leaves(TreeNode node, List<Integer> ds) {
        if (node.left == null && node.right == null) {
            ds.add(node.data);
        } else {
            if (node.left != null) {
                leaves(node.left, ds);
            }
            if (node.right != null) {
                leaves(node.right, ds);
            }
        }
    }

    private static void leftBoundary(TreeNode node, List<Integer> ds) {
        // terminate at leaf node
        if (node.left == null && node.right == null) return;
        ds.add(node.data);
        if (node.left != null) {
            leftBoundary(node.left, ds);
        } else {
            leftBoundary(node.right, ds);
        }
    }

}

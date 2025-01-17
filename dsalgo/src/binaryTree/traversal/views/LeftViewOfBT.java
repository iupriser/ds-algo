package binaryTree.traversal.views;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static binaryTree.TreeNode.createBinaryTree;
import static binaryTree.TreeNode.printBinaryTree;

public class LeftViewOfBT {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        System.out.println("----Left View of Binary Tree-----");
        List<Integer> leftView = leftViewOfBt_way1(root);
        for (Integer nodeVal : leftView) {
            System.out.print(nodeVal + " ");
        }
        System.out.println("\n----Left View of Binary Tree way 2-----");
        leftView = leftViewOfBt_way2(root);
        for (Integer nodeVal : leftView) {
            System.out.print(nodeVal + " ");
        }
    }

    // using level order traversal
    private static List<Integer> leftViewOfBt_way1(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        q.offer(root);
        while (!q.isEmpty()) {
            int sizeOfQueue = q.size();
            for (int i = 0; i < sizeOfQueue; i++) {
                TreeNode node = q.poll();
                // add the first element to res
                if (i == 0) {
                    res.add(node.data);
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return res;
    }

    // using recursion -> Root Left Right
    private static List<Integer> leftViewOfBt_way2(TreeNode root) {
        List<Integer> ds = new ArrayList<>();
        leftSideView(root, 0, ds);
        return ds;
    }

    private static void leftSideView(TreeNode node, int level, List<Integer> ds) {
        if (node == null) return;
        if (level == ds.size()) {
            ds.add(node.data);
        }
        leftSideView(node.left, level + 1, ds);
        leftSideView(node.right, level + 1, ds);
    }

}

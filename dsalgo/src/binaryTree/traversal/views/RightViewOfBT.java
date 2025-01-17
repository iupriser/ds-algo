package binaryTree.traversal.views;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static binaryTree.TreeNode.createBinaryTree;
import static binaryTree.TreeNode.printBinaryTree;

public class RightViewOfBT {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        System.out.println("----Right View of Binary Tree-----");
        List<Integer> rightView = rightViewOfBt_way1(root);
        for (Integer nodeVal : rightView) {
            System.out.print(nodeVal + " ");
        }
        System.out.println("\n----Right View of Binary Tree way 2-----");
        rightView = rightViewOfBt_way2(root);
        for (Integer nodeVal : rightView) {
            System.out.print(nodeVal + " ");
        }
    }

    // using level order traversal
    private static List<Integer> rightViewOfBt_way1(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        q.offer(root);
        while (!q.isEmpty()) {
            int sizeOfQueue = q.size();
            for (int i = 0; i < sizeOfQueue; i++) {
                TreeNode node = q.poll();
                // add the first element to res
                if (i == sizeOfQueue - 1) {
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

    // using recursion -> Root Right Left
    private static List<Integer> rightViewOfBt_way2(TreeNode root) {
        List<Integer> ds = new ArrayList<>();
        rightSideView(root, 0, ds);
        return ds;
    }

    private static void rightSideView(TreeNode node, int level, List<Integer> ds) {
        if (node == null) return;
        if (level == ds.size()) {
            ds.add(node.data);
        }
        rightSideView(node.right, level + 1, ds);
        rightSideView(node.left, level + 1, ds);
    }
}

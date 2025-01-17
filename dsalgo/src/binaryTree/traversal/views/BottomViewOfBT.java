package binaryTree.traversal.views;

import binaryTree.TreeNode;

import java.util.*;

import static binaryTree.TreeNode.printBinaryTree;
import static binaryTree.traversal.ZigzagTraversal.createBinaryTree;

public class BottomViewOfBT {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        System.out.println("----Bottom View of Binary Tree-----");
        List<Integer> bottomView = bottomView(root);
        for (Integer nodeVal : bottomView) {
            System.out.print(nodeVal + " ");
        }
    }

    private static List<Integer> bottomView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Pair> q = new LinkedList<>();
        // as we want ordering as per verticals, i.e. -2,-1,0,1,2,3
        // TreeMap<vertical,node.val>
        TreeMap<Integer, Integer> map = new TreeMap<>();
        q.offer(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            TreeNode node = p.node;
            int ver = p.vertical;
            // override previous entries in map so to get the deepest node values at any vertical
            map.put(ver, node.data);
            if (node.left != null) {
                q.offer(new Pair(node.left, ver - 1));
            }
            if (node.right != null) {
                q.offer(new Pair(node.right, ver + 1));
            }
        }
        res.addAll(map.values());
        return res;
    }
}
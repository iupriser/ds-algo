package binaryTree.traversal.views;

import binaryTree.TreeNode;

import java.util.*;

import static binaryTree.TreeNode.createBinaryTree;
import static binaryTree.TreeNode.printBinaryTree;

public class TopViewOfBT {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        System.out.println("----Top View of Binary Tree-----");
        List<Integer> topView = topView(root);
        for (Integer nodeVal : topView) {
            System.out.print(nodeVal + " ");
        }
    }

    private static List<Integer> topView(TreeNode root) {
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
            if (!map.containsKey(ver)) {
                map.put(ver, node.data);
            }
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

class Pair {
    TreeNode node;
    int vertical;

    public Pair(TreeNode node, int vertical) {
        this.node = node;
        this.vertical = vertical;
    }
}

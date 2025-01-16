package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.*;

import static binaryTree.TreeNode.printBinaryTree;
import static binaryTree.traversal.ZigzagTraversal.createBinaryTree;

public class VerticalOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        System.out.println("----Vertical order Traversal-----");
        List<List<Integer>> verticalOrderTraversal = verticalOrderTrav(root);
        for (List<Integer> level : verticalOrderTraversal) {
            for (Integer nodeVal : level) {
                System.out.print(nodeVal + " ");
            }
            System.out.println();
        }
    }

    private static List<List<Integer>> verticalOrderTrav(TreeNode root) {
        // ds ==>  TreeMap<Verticals, TreeMap<Levels, PriorityQueue<Integer>>>
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Tuple> q = new LinkedList<>();
        q.offer(new Tuple(root, 0, 0));
        while (!q.isEmpty()) {
            Tuple tuple = q.poll();
            TreeNode node = tuple.node;
            int ver = tuple.vertical;
            int lev = tuple.level;
            if (!map.containsKey(ver)) {
                map.put(ver, new TreeMap<>());
            }
            if (!map.get(ver).containsKey(lev)) {
                map.get(ver).put(lev, new PriorityQueue<>());
            }
            map.get(ver).get(lev).offer(node.data);
            if (node.left != null) {
                q.offer(new Tuple(node.left, ver - 1, lev + 1));
            }
            if (node.right != null) {
                q.offer(new Tuple(node.right, ver + 1, lev + 1));
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> levels : map.values()) {
            List<Integer> list = new ArrayList<>();
            for (PriorityQueue<Integer> nodes : levels.values()) {
                while (!nodes.isEmpty()) {
                    list.add(nodes.poll());
                }
            }
            res.add(list);
        }
        return res;
    }
}

class Tuple {
    TreeNode node;
    int vertical;
    int level;

    public Tuple(TreeNode node, int vertical, int level) {
        this.node = node;
        this.vertical = vertical;
        this.level = level;
    }
}

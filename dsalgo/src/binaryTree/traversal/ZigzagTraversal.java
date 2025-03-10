package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static binaryTree.TreeNode.createBinaryTree;
import static binaryTree.TreeNode.printBinaryTree;

public class ZigzagTraversal {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        List<List<Integer>> zigzaggedTrav = zigzagTrav(root);
        System.out.println("----Zig-zag Traversal-----");
        for (List<Integer> level : zigzaggedTrav) {
            for (Integer nodeVal : level) {
                System.out.print(nodeVal + " ");
            }
            System.out.println();
        }
    }

    private static List<List<Integer>> zigzagTrav(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        boolean leftToRight = true;
        q.offer(root);
        while (!q.isEmpty()) {
            int sizeOfQueue = q.size();
            List<Integer> currList = new ArrayList<>();
            for (int i = 0; i < sizeOfQueue; i++) {
                TreeNode currNode = q.poll();
                if (currNode.left != null)
                    q.offer(currNode.left);
                if (currNode.right != null)
                    q.offer(currNode.right);

                if (leftToRight) {
                    currList.add(currNode.data);
                } else {
                    currList.add(0, currNode.data);
                }
            }
            leftToRight = !leftToRight;
            res.add(currList);
        }
        return res;
    }
}

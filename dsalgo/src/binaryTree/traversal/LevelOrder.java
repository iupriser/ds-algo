package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Getting preorder traversal
        List<List<Integer>> result = levelOrder(root);

        // Displaying the preorder traversal result
        System.out.print("Level order Traversal: ");
        result.stream().flatMap(List::stream).toList().forEach(System.out::print);
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> wrapList = new ArrayList<>();
        if (root == null) return wrapList;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int elementsAtCurrentLevel = queue.size();
            List<Integer> listOfElementsAtCurrentLevel = new ArrayList<>();
            for (int i = 0; i < elementsAtCurrentLevel; i++) {
                TreeNode node = queue.poll();
                listOfElementsAtCurrentLevel.add(node.data);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return wrapList;
    }
}

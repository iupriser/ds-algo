package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static binaryTree.TreeNode.createBinaryTree;

public class LevelOrderTraversal {
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = createBinaryTree();

        // Getting levelOrder traversal
        List<List<Integer>> result = levelOrder(root);

        // Displaying the levelOrder traversal result
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

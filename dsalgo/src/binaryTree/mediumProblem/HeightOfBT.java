package binaryTree.mediumProblem;

import binaryTree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class HeightOfBT {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        int heightOfBT = heightOFBT(root);
        System.out.println("Height/maxDepth of Binary tree: " + heightOfBT);

        root = createBinaryTree();
        heightOfBT = heightOFBTLevelOrder(root);
        System.out.println("Height/maxDepth of Binary tree: " + heightOfBT);
    }

    private static int heightOFBT(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = heightOFBT(root.left) + 1;
        int rightHeight = heightOFBT(root.right) + 1;
        return Math.max(leftHeight, rightHeight);
    }

    private static int heightOFBTLevelOrder(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int levels = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                TreeNode node = q.removeFirst();
                if (node.left != null) {
                    q.addLast(node.left);
                }
                if (node.right != null) {
                    q.addLast(node.right);
                }
            }
            levels++;
        }
        return levels;
    }


    private static TreeNode createBinaryTree() {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(5);
        root.left.right.left.right = new TreeNode(6);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(2);
        root.right.right.left = new TreeNode(1);
        root.right.right.left.right = new TreeNode(4);
        root.right.right.left.right.left = new TreeNode(3);
        root.right.right.left.right.right = new TreeNode(6);
        root.right.right.left.right.right.right = new TreeNode(2);

        return root;
    }
}

package binaryTree.hardProblem;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static binaryTree.TreeNode.createBinaryTree;

//https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
public class SerializeDeserializeBT {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        ArrayList<Integer> serialized = serialize(root);
        TreeNode treeNode = deSerialize(serialized);
    }

    // Function to serialize a tree and return a list containing nodes of tree.
    public static ArrayList<Integer> serialize(TreeNode root) {
        ArrayList<Integer> serialized = new ArrayList<>();
        if (root == null) return serialized;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node == null) {
                    serialized.add(-1);
                    continue;
                }
                serialized.add(node.data);
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        return serialized;
    }

    // Function to deserialize a list and construct the tree.
    public static TreeNode deSerialize(ArrayList<Integer> serialized) {
        if (serialized.isEmpty()) return null;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(serialized.get(0));
        q.offer(root);
        for (int i = 1; i < serialized.size(); i++) {
            TreeNode parent = q.poll();
            // look for left and right of parent
            if (!serialized.get(i).equals(-1)) {
                TreeNode left = new TreeNode(serialized.get(i));
                parent.left = left;
                q.add(left);
            }
            if (!serialized.get(++i).equals(-1)) {
                TreeNode right = new TreeNode(serialized.get(i));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }
}

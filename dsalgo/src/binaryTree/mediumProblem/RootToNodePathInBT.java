package binaryTree.mediumProblem;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static binaryTree.TreeNode.createBinaryTree;
import static binaryTree.TreeNode.printBinaryTree;

public class RootToNodePathInBT {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        TreeNode targetNode = new TreeNode(7);
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        System.out.print("Root to Node Paths: ");
        List<Integer> path = rootToNodePath(root, targetNode);
        for (Integer nodeVal : path) {
            System.out.print(nodeVal + " ");
        }
    }

    private static List<Integer> rootToNodePath(TreeNode root, TreeNode targetNode) {
        List<Integer> path = new ArrayList<>();
        if (root == null) return path;
        getPath(root, targetNode, path);
        return path;
    }

    private static boolean getPath(TreeNode currNode, TreeNode targetNode, List<Integer> path) {
        if (currNode == null) return false;

        path.add(currNode.data);
        if (currNode.data == targetNode.data) {
            return true;
        }
        if (getPath(currNode.left, targetNode, path) || getPath(currNode.right, targetNode, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }
}

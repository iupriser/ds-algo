package binaryTree.mediumProblem;

import binaryTree.TreeNode;

import java.util.ArrayList;

import static binaryTree.TreeNode.createBinaryTree;
import static binaryTree.TreeNode.printBinaryTree;

//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
public class LowestCommonAncestorInBT {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(9);

        TreeNode lowestCommonAncestor = lcaBrute(root, node1, node2);
        System.out.print("Lowest Common Ancestor of Binary Tree: " + lowestCommonAncestor.data);
        System.out.println();
        lowestCommonAncestor = lcaOptimal(root, node1, node2);
        System.out.print("Lowest Common Ancestor of Binary Tree: " + lowestCommonAncestor.data);

//        List<Integer> path = rootToNodePath(root, targetNode);
//        for (Integer nodeVal : path) {
//            System.out.print(nodeVal + " ");
//        }
    }

    private static TreeNode lcaBrute(TreeNode root, TreeNode node1, TreeNode node2) {
        ArrayList<TreeNode> path1 = new ArrayList<>();
        ArrayList<TreeNode> path2 = new ArrayList<>();

        // only run code, when both of nodes are present in the tree
        if (!findPath(root, node1, path1) || !findPath(root, node2, path2)) {
            return null;
        }

        // Compare the paths to get the first different value
        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) != path2.get(i)) {
                return path1.get(i - 1);
            }
        }
        // if both the datas are same, return last node
        return path1.get(i - 1);
    }

    private static boolean findPath(TreeNode curr, TreeNode target, ArrayList<TreeNode> path) {
        if (curr == null) return false;
        path.add(curr);
        if ((curr.data == target.data) || findPath(curr.left, target, path) || findPath(curr.right, target, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    private static TreeNode lcaOptimal(TreeNode curr, TreeNode node1, TreeNode node2) {
        if (curr == null || curr.data == node1.data || curr.data == node2.data) return curr;
        TreeNode left = lcaOptimal(curr.left, node1, node2);
        TreeNode right = lcaOptimal(curr.right, node1, node2);
        if (left == null && right == null) return null;
        if (left == null) return right;
        else if (right == null) {
            return left;
        } else {
            // both left and right are not null, we found LCA
            return curr;
        }
    }
}

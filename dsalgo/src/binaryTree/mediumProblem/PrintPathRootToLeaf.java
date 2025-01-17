package binaryTree.mediumProblem;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static binaryTree.TreeNode.createBinaryTree;

public class PrintPathRootToLeaf {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        printAllPathsUsingString(root);

        root = createBinaryTree();
        printAllPathsUsingList(root);
    }

    private static void printAllPathsUsingString(TreeNode root) {
        List<String> allPaths = new ArrayList<>();
        if (root != null) {
            helperPAP(root, "", allPaths);
        }
        allPaths.forEach(System.out::println);
    }

    private static void helperPAP(TreeNode node, String currPath, List<String> allPaths) {
        // leaf node
        if (node.left == null && node.right == null) {
            currPath += node.data;
            allPaths.add(currPath);
        } else {
            currPath += node.data;
            if (node.left != null)
                helperPAP(node.left, currPath + "->", allPaths);
            if (node.right != null)
                helperPAP(node.right, currPath + "->", allPaths);
        }
    }

    private static void printAllPathsUsingList(TreeNode root) {
        List<List<Integer>> allPaths = new ArrayList<>();
        if (root != null) {
            helperPAP_list(root, new ArrayList<>(), allPaths);
        }
        // print list of list
        for (List<Integer> list : allPaths) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    private static void helperPAP_list(TreeNode node, ArrayList<Integer> currPath,
                                       List<List<Integer>> allPaths) {
        // leaf node
        if (node.left == null && node.right == null) {
            currPath.add(node.data);
            allPaths.add(new ArrayList<>(currPath));
            currPath.remove(currPath.size() - 1);
        } else {
            currPath.add(node.data);
            if (node.left != null) helperPAP_list(node.left, currPath, allPaths);
            if (node.right != null) helperPAP_list(node.right, currPath, allPaths);
            // backtracking
            currPath.remove(currPath.size() - 1);
        }
    }
}

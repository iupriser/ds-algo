package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static binaryTree.TreeNode.createBinaryTree;
import static binaryTree.TreeNode.printBinaryTree;

public class DiagonalTraversal {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        System.out.println("----Diagonal View of Binary Tree-----");
        List<Integer> diagonalView = diagonalView_way1(root);
        for (Integer nodeVal : diagonalView) {
            System.out.print(nodeVal + " ");

        }

    }

    private static List<Integer> diagonalView_way1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // hashmap to store nodes at its respective diagonal level (Diagonal)
        HashMap<Integer, ArrayList<Integer>> levelData = new HashMap<>();
        diagonalRec(root, 0, levelData);

        int diagonalLevel = 0;
        while (levelData.containsKey(diagonalLevel)) {
            ArrayList<Integer> v = levelData.get(diagonalLevel);
            res.addAll(v);
            diagonalLevel++;
        }
        return res;
    }

    private static void diagonalRec(TreeNode root, int diagonalLevel, HashMap<Integer, ArrayList<Integer>> levelData) {
        if (root == null) return;
        if (!levelData.containsKey(diagonalLevel)) {
            levelData.put(diagonalLevel, new ArrayList<>());
        }
        levelData.get(diagonalLevel).add(root.data);
//        levelData.computeIfAbsent(diagonalLevel, k -> new ArrayList<>()).add(root.data);
        diagonalRec(root.left, diagonalLevel + 1, levelData);
        diagonalRec(root.right, diagonalLevel, levelData);
    }
}

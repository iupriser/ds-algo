package binaryTree.hardProblem;

import binaryTree.TreeNode;

import java.util.HashMap;
import java.util.Map;

import static binaryTree.TreeNode.printBinaryTree;

public class ConstructBTInPostOrder {
    public static void main(String[] args) {
        int[] postorder = {40, 50, 20, 60, 30, 10};
        int[] inorder = {40, 20, 50, 10, 60, 30};
        TreeNode root = constructBT_InOrderPostOrder(inorder, postorder);
        printBinaryTree(root);
    }

    private static TreeNode constructBT_InOrderPostOrder(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return constructBT(postorder, 0, postorder.length - 1, inorder, 0,
                inorder.length - 1, inMap);
    }

    private static TreeNode constructBT(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        //
        if (postStart > postEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(postorder[postEnd]);

        int inRoot = inMap.get(postorder[postEnd]);
        int numsLeft = inRoot - inStart;

        root.left = constructBT(postorder, postStart, postStart + numsLeft - 1, inorder, inStart,
                inRoot - 1, inMap);
        root.right = constructBT(postorder, postStart + numsLeft, postEnd - 1, inorder,
                inRoot + 1, inEnd, inMap);

        return root;
    }
}

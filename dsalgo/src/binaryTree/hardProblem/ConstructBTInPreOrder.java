package binaryTree.hardProblem;

import binaryTree.TreeNode;

import java.util.HashMap;
import java.util.Map;

import static binaryTree.TreeNode.printBinaryTree;

public class ConstructBTInPreOrder {
    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = constructBT_InOrderPreOrder(inorder, preorder);
        printBinaryTree(root);
    }

    private static TreeNode constructBT_InOrderPreOrder(int[] inorder, int[] preorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return constructBT(preorder, 0, preorder.length - 1, inorder, 0,
                inorder.length - 1, inMap);
    }

    private static TreeNode constructBT(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        //
        if (preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);

        int inRoot = inMap.get(preorder[preStart]);
        int numsLeft = inRoot - inStart;

        root.left = constructBT(preorder, preStart + 1, preStart + numsLeft, inorder, inStart,
                inRoot - 1, inMap);
        root.right = constructBT(preorder, preStart + numsLeft + 1, preEnd, inorder,
                inRoot + 1,
                inEnd, inMap);

        return root;
    }


}

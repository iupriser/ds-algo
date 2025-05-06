package bst;

import binaryTree.TreeNode;

//https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/description/
public class BstFromPreorder {
    public static void main(String[] args) {

    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, Integer.MAX_VALUE, new int[]{0});
    }

    private TreeNode bstFromPreorder(int[] preorder, int ub, int[] i) {
        if (i[0] == preorder.length || preorder[i[0]] > ub) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[i[0]++]);
        root.left = bstFromPreorder(preorder, root.data, i);
        root.right = bstFromPreorder(preorder, ub, i);
        return root;
    }
}

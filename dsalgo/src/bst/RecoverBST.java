package bst;
//https://leetcode.com/problems/recover-binary-search-tree/

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static binaryTree.TreeNode.printBinaryTree;

public class RecoverBST {
    List<Integer> inorder = new ArrayList<>();
    TreeNode first;
    TreeNode prev;
    TreeNode middle;
    TreeNode last;

    public static void main(String[] args) {
        // creating Distored BST
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        RecoverBST obj = new RecoverBST();
        printBinaryTree(root);
        obj.recoverTreeBrute(root);
        printBinaryTree(root);
    }

    public void recoverTreeBrute(TreeNode root) {
        // traversal -O(N)
        inorderTraversal(root);
        // sorting - O(NlogN)
        Collections.sort(inorder);
        // O(N)
        inorderTraversalTweaked(root, 0);
    }

    public void recoverBST(TreeNode root) {
        first=middle=last=null;
        prev = new TreeNode(Integer.MIN_VALUE);
        inorderTravOptimized(root);
    }

    private void inorderTravOptimized(TreeNode root) {
        if(root == null) return;
        inorderTravOptimized(root.left);
        // check for violation
        if(prev!=null && (root.data < prev.data)) {
            // if this is first violation, mark these two nodes as first and middle
            if(null==first){
                first = prev;
                middle = root;
            }
            // if this is second violation, mark this node as last
            else{
                last = root;
            }
        }
        // mark this node as prev
        prev = root;
        inorderTravOptimized(root.right);
    }

    private void inorderTraversalTweaked(TreeNode root, int index) {
        if (root == null)
            return;
        inorderTraversalTweaked(root.left, index);
        if (root.data != inorder.get(index)) {
            root.data = inorder.get(index);
        }
        inorderTraversalTweaked(root.right, index);
    }

    private void inorderTraversal(TreeNode root) {
        if (root == null)
            return;
        inorderTraversal(root.left);
        inorder.add(root.data);
        inorderTraversal(root.right);
    }
}

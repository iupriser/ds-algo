package bst;
//https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/

import binaryTree.TreeNode;

import java.util.Stack;

public class TwoSumBSTIterator {
    private Stack<TreeNode> stk = new Stack<>();
    // reverse -> true -> before -> next largest element
    // reverse -> false -> next -> next smallest element
    private final boolean reverse;

    public TwoSumBSTIterator(TreeNode root, boolean isReverse) {
        reverse = isReverse;
        pushAll(root);
    }

    private void pushAll(TreeNode node) {
        while (node != null) {
            stk.push(node);
            if (reverse) node = node.right;
            else node = node.left;
        }
    }

    int next() {
        TreeNode tmpNode = stk.pop();
        if (!reverse) pushAll(tmpNode.right);
        else pushAll(tmpNode.left);
        return tmpNode.data;
    }
}

class SolutionBSTTwoSum {
    boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        // left iterator => nextIterator
        TwoSumBSTIterator l = new TwoSumBSTIterator(root, false);
        // right iterator => beforeIterator
        TwoSumBSTIterator r = new TwoSumBSTIterator(root, true);

        int i = l.next();
        int j = r.next(); // actually it is r.before()
        while (i < j) {
            if (i + j == k) return true;
            else if (i + j < k) i = l.next();
            else j = r.next();
        }
        return false;
    }
}

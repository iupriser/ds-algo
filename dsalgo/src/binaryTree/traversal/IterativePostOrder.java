package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class IterativePostOrder {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(4);
        root.left.left.right.right = new TreeNode(5);
        root.left.left.right.right.right = new TreeNode(6);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(8);

        ArrayList<Integer> postOrderTraversal = postOrderTraversalSingleStack(root);
        postOrderTraversal.forEach(System.out::print  );
    }

    private static ArrayList<Integer> postOrderTraversalSingleStack(TreeNode curr) {
        ArrayList<Integer> postOrderList = new ArrayList<>();
        Stack<TreeNode> stk = new Stack<>();
        while (curr != null || !stk.isEmpty()) {
            if (curr != null) {
                stk.push(curr);
                curr = curr.left;
            }
            // if curr is null
            else {
                TreeNode tmp = stk.peek().right;
                // both left and right child of stk.peek() node are null, so it is a leaf node
                if (tmp == null) {
                    tmp = stk.pop();
                    postOrderList.add(tmp.data);
                    while (!stk.isEmpty() && tmp == stk.peek().right) {
                        tmp = stk.pop();
                        postOrderList.add(tmp.data);
                    }
                } else {
                    curr = tmp;
                }
            }
        }
        return postOrderList;
    }
}

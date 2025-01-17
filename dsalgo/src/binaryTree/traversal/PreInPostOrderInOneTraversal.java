package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static binaryTree.TreeNode.createBinaryTree;
import static binaryTree.TreeNode.printBinaryTree;

public class PreInPostOrderInOneTraversal {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        List<List<Integer>> allTraversal = traversal(root);
        System.out.println("----Pre-In-Post_order-Traversal-----");
        for (List<Integer> trav : allTraversal) {
            for (int nodeVal : trav) {
                System.out.print(nodeVal + " ");
            }
            System.out.println();
        }
    }

    private static List<List<Integer>> traversal(TreeNode root) {
        List<Integer> preOrderList = new ArrayList<>();
        List<Integer> inOrderList = new ArrayList<>();
        List<Integer> postOrderList = new ArrayList<>();

        if (root == null) {
            return new ArrayList<>();
        }
        Stack<Pair> stk = new Stack<>();
        // Start with the root node and state 1 (preorder)
        stk.push(new Pair(root, 1));

        while (!stk.isEmpty()) {
            Pair p = stk.pop();
            TreeNode node = p.node;
            int state = p.state;
            // this is part of preOrder
            if (state == 1) {
                // store the node's data in preOrderList
                preOrderList.add(node.data);
                // Move to state 2 (inorder) for this node
                //Push the updated state back onto the stack
                stk.push(new Pair(node, state + 1));
                // Push left child onto the stack for processing
                if (node.left != null) {
                    stk.push(new Pair(node.left, 1));
                }
            }
            // this is part of inOrderList
            else if (state == 2) {
                // Store the node's data in the inorder traversal
                inOrderList.add(node.data);
                // Move to state 3 (postorder) for this node
                // Push the updated state back onto the stack
                stk.push(new Pair(node, 3));

                // Push right child onto the stack for processing
                if (node.right != null) {
                    stk.push(new Pair(node.right, 1));
                }
            }
            // this is part of post
            else {
                // Store the node's data in the postorder traversal
                postOrderList.add(node.data);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        result.add(preOrderList);
        result.add(inOrderList);
        result.add(postOrderList);
        return result;
    }
}

class Pair {
    TreeNode node;
    int state;

    public Pair(TreeNode node, int state) {
        this.node = node;
        this.state = state;
    }
}

package binaryTree.hardProblem;

import binaryTree.TreeNode;

import java.util.*;

import static binaryTree.TreeNode.createBinaryTree;
import static binaryTree.TreeNode.printBinaryTree;

public class MinTimeToBurnBT {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        System.out.println("----Original Tree-----");
        printBinaryTree(root);
        int target = 6;
        int minTime = minTimeToBurnBTFromNode(root, target);
        System.out.println("Minimum time to burn Binary Tree: " + minTime);
    }

    private static int minTimeToBurnBTFromNode(TreeNode root, int target) {
        Map<TreeNode, TreeNode> childParentMap = new HashMap<>();
        TreeNode targetNode = getChildParentMap(root, childParentMap, target);
        // find the min time to burn binary tree
        Set<TreeNode> isVisited = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(targetNode);
        isVisited.add(targetNode);
        int time = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            // if burning any adjacent node(left, right, parent) mark it 1 or else 0;
            // there can be case when current node have left, right and parent, but all of them
            // have already burnt(marked visited in set)
            int fl = 0;

            for (int i = 0; i < size; i++) {
                TreeNode currNode = q.poll();
                // if left of currNode is not null and has not been visited
                if (currNode.left != null && !isVisited.contains(currNode.left)) {
                    isVisited.add(currNode.left);
                    q.offer(currNode.left);
                    fl = 1;
                }
                // if right of currNode is not null and has not been visited
                if (currNode.right != null && !isVisited.contains(currNode.right)) {
                    isVisited.add(currNode.right);
                    q.offer(currNode.right);
                    fl = 1;
                }
                // if parent of currNode exist and has not been visited
                if (childParentMap.get(currNode) != null && !isVisited.contains(childParentMap.get(currNode))) {
                    isVisited.add(childParentMap.get(currNode));
                    q.offer(childParentMap.get(currNode));
                    fl = 1;
                }
            }
            if (fl == 1) time++;
        }
        return time;
    }

    // do child-parent mapping and find the address of targetNode
    static TreeNode getChildParentMap(TreeNode root,
                                      Map<TreeNode, TreeNode> mmp, int target) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        TreeNode targetNode = null;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode currNode = q.poll();
                if (currNode.data == target) {
                    targetNode = currNode;
                }
                if (currNode.left != null) {
                    mmp.put(currNode.left, currNode);
                    q.offer(currNode.left);
                }
                if (currNode.right != null) {
                    mmp.put(currNode.right, currNode);
                    q.offer(currNode.right);
                }
            }
        }
        return targetNode;
    }
}

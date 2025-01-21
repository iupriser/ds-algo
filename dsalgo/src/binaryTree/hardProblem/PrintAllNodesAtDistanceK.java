package binaryTree.hardProblem;

import binaryTree.TreeNode;

import java.util.*;

import static binaryTree.TreeNode.createBinaryTree;

public class PrintAllNodesAtDistanceK {
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        TreeNode target = root.right;
        int k = 2;
        List<Integer> distancedKNodes = distanceK(root, target, k);
        System.out.print("Nodes at a distance " + k + " : ");
        for (Integer nodeVal : distancedKNodes) {
            System.out.print(nodeVal + " ");
        }
    }

    private static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> distancedKNodes = new ArrayList<>();
        if (root == null) return distancedKNodes;

        // first Level Order Traversal to get parent-child relationship recorded in Hashmap
        Map<TreeNode, TreeNode> childParentMap = getChildParentMap(root);
        // Print the HashMap
        for (Map.Entry<TreeNode, TreeNode> entry : childParentMap.entrySet()) {
            System.out.println("Child: " + entry.getKey().data + ", Parent: " + entry.getValue().data);
        }
        // as we have child and parent relationship, we can go upward and downward in BT

        // get the address of target node, if value of target node is given, do any traversal to
        // get the address of targetNode
        int distance = 0;
        Set<TreeNode> isVisited = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(target);
        isVisited.add(target);
        while (!q.isEmpty() && distance != k) {
            int size = q.size();
            distance++;
            for (int i = 0; i < size; i++) {
                TreeNode currNode = q.poll();
                // if left of currNode is not null and has not been visited
                if (currNode.left != null && !isVisited.contains(currNode.left)) {
                    isVisited.add(currNode.left);
                    q.offer(currNode.left);
                }
                // if right of currNode is not null and has not been visited
                if (currNode.right != null && !isVisited.contains(currNode.right)) {
                    isVisited.add(currNode.right);
                    q.offer(currNode.right);
                }
                // if parent of currNode exist and has not been visited
                if (childParentMap.get(currNode) != null && !isVisited.contains(childParentMap.get(currNode))) {
                    isVisited.add(childParentMap.get(currNode));
                    q.offer(childParentMap.get(currNode));
                }
            }
        }
        while (!q.isEmpty()) {
            distancedKNodes.add(q.poll().data);
        }
        return distancedKNodes;
    }

    private static Map<TreeNode, TreeNode> getChildParentMap(TreeNode root) {
        Map<TreeNode, TreeNode> childParentMap = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode currNode = q.poll();
                if (currNode.left != null) {
                    childParentMap.put(currNode.left, currNode);
                    q.offer(currNode.left);
                }
                if (currNode.right != null) {
                    childParentMap.put(currNode.right, currNode);
                    q.offer(currNode.right);
                }
            }
        }
        return childParentMap;
    }
}
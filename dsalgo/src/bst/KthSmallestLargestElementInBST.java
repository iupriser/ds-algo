package bst;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static binaryTree.TreeNode.createBinarySearchTree;

public class KthSmallestLargestElementInBST {
    public static void main(String[] args) {
        TreeNode root = createBinarySearchTree();
        int k = 3;
        List<Integer> kthElements = kthSmallestElementLargestElement_brute(root, k);
        System.out.println("Kth smallest element: " + kthElements.get(0));
        System.out.println("Kth largest element: " + kthElements.get(1));
        System.out.println("----------------------------------------------");
        kthElements = kthSmallestElementLargestElement_optimal(root, k);
        System.out.println("Kth smallest element: " + kthElements.get(0));
        System.out.println("Kth largest element: " + kthElements.get(1));
    }

    // TC:O(N), SC:O(N)
    private static List<Integer> kthSmallestElementLargestElement_brute(TreeNode root, int k) {
        ArrayList<Integer> arr = new ArrayList<>();
        inorder(root, arr);

        // Calculate Kth largest and smallest elements
        int kLargest = arr.get(arr.size() - k);
        int kSmallest = arr.get(k - 1);

        // Returning a list containing
        // Kth smallest and largest elements
        return Arrays.asList(kSmallest, kLargest);
    }

    private static List<Integer> kthSmallestElementLargestElement_optimal(TreeNode root, int k) {
        int[] kSmallest = new int[]{Integer.MIN_VALUE};
        int[] kLargest = new int[]{Integer.MIN_VALUE};
        int[] counter = new int[]{0};
        inorderModified(root, counter, k, kSmallest);
        counter[0] = 0;
        reverseInorderModified(root, counter, k, kLargest);


        // Returning a list containing
        // Kth smallest and largest elements
        return Arrays.asList(kSmallest[0], kLargest[0]);
    }

    // Inorder traversal
    private static void inorder(TreeNode node, ArrayList<Integer> arr) {
        if (node == null) {
            return;
        }
        inorder(node.left, arr);
        arr.add(node.data);
        inorder(node.right, arr);
    }

    // Modified Inorder traversal
    private static void inorderModified(TreeNode node, int[] counter, int k, int[] kSmallest) {
        if (node == null || counter[0] > k) {
            return;
        }
        inorderModified(node.left, counter, k, kSmallest);
        counter[0]++;
        if (counter[0] == k) {
            kSmallest[0] = node.data;
        }
        inorderModified(node.right, counter, k, kSmallest);
    }

    private static void reverseInorderModified(TreeNode node, int[] counter, int k, int[] kLargest) {
        if (node == null || counter[0] > k) {
            return;
        }
        reverseInorderModified(node.right, counter, k, kLargest);
        counter[0]++;
        if (counter[0] == k) {
            kLargest[0] = node.data;
        }
        reverseInorderModified(node.left, counter, k, kLargest);
    }

}

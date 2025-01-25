package binaryTree.traversal;

import binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static binaryTree.TreeNode.printBinaryTree;

public class MorrisTraversal_LevelOrder {
    public static void main(String[] args) {
//        TreeNode root = createBinaryTree();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(6);
        printBinaryTree(root);

        List<PairForLOT> levelOrderTraversal = levelOrderMorris(root);
//        // find the height of the tree
//        int h = 0;
//        for (Object[] i : levelOrderTraversal) {
//            h = Math.max(h, (int) i[1] + 1);
//        }
//
//        // print the date of nodes at each level
//        for (int i = 0; i < h; i++) {
//            for (Object[] j : levelOrderTraversal) {
//                if ((int) j[1] == i) {
//
//                    System.out.print(((TreeNode) j[0]).data + " ");
//                }
//            }
//            System.out.println();
    }

    //    use Morris Preorder Traversal to traverse the tree in level order traversal.
    private static List<PairForLOT> levelOrderMorris(TreeNode root) {
        List<PairForLOT> levelOrder = new ArrayList<>();
        TreeNode curr = root;
        int level = 0;
        while (curr != null) {
            if (curr.left == null) {
                levelOrder.add(new PairForLOT(curr.data, level));
                curr = curr.right;
                if (curr != null) {
                    level++;
                } else {
                    level--;
                }
            } else {
                // If the left child is not NULL,
                // find the rightmost node in the left subtree
                TreeNode prev = curr.left;
                int toUp = 0;

                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                    toUp++;
                }
                // If the predecessor's right child
                // is NULL, establish a temporary link
                // and move to the left child
                if (prev.right == null) {
                    levelOrder.add(new PairForLOT(curr.data, level));
                    prev.right = curr;
                    curr = curr.left;
                    level++;
                } else {
                    // If the predecessor's right child
                    // is already linked, remove the link,
                    // add current node to inorder list,
                    // and move to the right child
                    prev.right = null;
                    curr = curr.right;
                    level = level - toUp + 1;
                }
            }
        }
        return levelOrder;
    }
}

class PairForLOT {
    int nodeVal;
    int level;

    public PairForLOT(int nodeVal, int level) {
        this.nodeVal = nodeVal;
        this.level = level;
    }
}

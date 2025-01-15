package linkedList;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    public static void main(String[] args) {
        // Creating a linked list with random pointer
        RandomNode head = new RandomNode(7);
        createLLWithRandomNodes(head);

        RandomNode clonedHead = copyListBrute(head);
        printList(clonedHead);

        clonedHead = copyListOptimal(head);
        printList(clonedHead);
    }

    private static RandomNode copyListBrute(RandomNode head) {
        Map<RandomNode, RandomNode> mp = new HashMap<>();
        RandomNode tmp = head;
        while (tmp != null) {
            mp.put(tmp, new RandomNode(tmp.val));
            tmp = tmp.next;
        }
        tmp = head;
        while (tmp != null) {
            RandomNode newNode = mp.get(tmp);
            newNode.next = mp.get(tmp.next);
            newNode.random = mp.get(tmp.random);

            tmp = tmp.next;
        }

        return mp.get(head);
    }

    private static RandomNode copyListOptimal(RandomNode head) {
        RandomNode tmp = head;
        // 1. place copy nodes in between
        while (tmp != null) {
            RandomNode copyNode = new RandomNode(tmp.val);
            copyNode.next = tmp.next;
            tmp.next = copyNode;
            tmp = tmp.next.next;
        }
        // 2. connect random pointers of copy nodes
        tmp = head;
        while (tmp != null) {
            // copyNode -> tmp.next
            RandomNode copyNode = tmp.next;
            // random pointer of copyNode => tmp.next.random
            if (tmp.random != null)
                copyNode.random = tmp.random.next;
            tmp = tmp.next.next;
        }
        //3. connect next pointer
        RandomNode dummy = new RandomNode(-1);
        RandomNode res = dummy;
        tmp = head;
        while (tmp != null) {
            res.next = tmp.next;
            tmp.next = tmp.next.next;
            tmp = tmp.next;
            res = res.next;
        }

        return dummy.next;
    }

    private static void createLLWithRandomNodes(RandomNode head) {
        head.next = new RandomNode(13);
        head.next.next = new RandomNode(11);
        head.next.next.next = new RandomNode(10);
        head.next.next.next.next = new RandomNode(1);
        head.random = head.next.next.next.next.next;
        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head;
    }

    static void printList(RandomNode head) {
        while (head != null) {
            System.out.print(head.val + "(");
            if (head.random != null)
                System.out.print(head.random.val + ")");
            else
                System.out.print("null" + ")");

            if (head.next != null)
                System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }
}

class RandomNode {
    int val;
    RandomNode next;
    RandomNode random;

    RandomNode(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

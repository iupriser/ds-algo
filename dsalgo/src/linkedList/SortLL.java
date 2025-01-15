package linkedList;

import java.util.ArrayList;
import java.util.Collections;

import static linkedList.Node.createLL;
import static linkedList.Node.printLL;

public class SortLL {
    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 1, 5};
        Node head = createLL(arr);
        Node sortedHead = sortLLBrute(head);
        printLL(head);

        sortedHead = sortLLOptimal(head);
        printLL(head);
    }


    private static Node sortLLBrute(Node head) {
        Node tmp = head;
        ArrayList<Integer> arr = new ArrayList<>();
        while (tmp != null) {
            arr.add(tmp.val);
            tmp = tmp.next;
        }
        Collections.sort(arr);
        tmp = head;
        for (int val : arr) {
            tmp.val = val;
            tmp = tmp.next;
        }
        return head;
    }

    private static Node sortLLOptimal(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node middle = findMiddle(head);
        Node rightHead = middle.next;
        middle.next = null;
        Node leftHead = head;

        // return head of sorted list
        leftHead = sortLLOptimal(leftHead);
        rightHead = sortLLOptimal(rightHead);

        // merge both sorted list
        return merge2SortedList(leftHead, rightHead);
    }

    // modified tortoise and hare algo to get first middle instead of second middle
    private static Node findMiddle(Node head) {
        Node slow = head, fast = head;
        fast = fast.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static Node merge2SortedList(Node head1, Node head2) {
        Node dummy = new Node(-1);
        Node curr = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                curr.next = head1;
                curr = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                curr = head2;
                head2 = head2.next;
            }
        }
        if (head1 != null) {
            curr.next = head1;
        } else {
            curr.next = head2;
        }
        return dummy.next;
    }
}

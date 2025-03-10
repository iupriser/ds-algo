package linkedList;

import static linkedList.Node.createLL;
import static linkedList.Node.printLL;

//https://leetcode.com/problems/add-two-numbers-ii/description/
//The most significant digit comes first and each of their nodes
// contains a single digit. Add the two numbers and return the sum as a linked list.
public class AddTwoNumberII {
    public static void main(String[] args) {
        int[] num1 = {9, 2, 4, 3};
        // most significant
        // 9->2->4->3, 9(MSD and head), 3(LSD)
        Node l1 = createLL(num1);
        int[] num2 = {8, 6, 4};
        // 8->6->4, 8(MSD and head), 4(LSD)
        Node l2 = createLL(num2);
        // 9243+864 = 10007, 1->0->0->0->7, 1(MSD and head), 7(LSD)
        Node summedList = addTwoNumbers(l1, l2);
        printLL(summedList);

        l1 = createLL(num1);
        l2 = createLL(num2);
        summedList = addTwoNumbersWithoutExtraSpace(l1, l2);
        printLL(summedList);
    }

    // creating new LL to store summed LL
    private static Node addTwoNumbers(Node l1, Node l2) {
        l1 = reverseLL(l1);
        l2 = reverseLL(l2);
        Node dummy = new Node(-1);
        Node tmp = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry == 1) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            Node node = new Node(sum % 10);
            tmp.next = node;
            tmp = node;
        }
        return reverseLL(dummy.next);
    }

    // adding sum to longest LL to avoid extra space
    private static Node addTwoNumbersWithoutExtraSpace(Node l1, Node l2) {
        int nodesInList1 = countNodes(l1);
        int nodesInList2 = countNodes(l2);
        // if l1 is smaller, swap the two linked lists by calling the function with reversed parameters
        if (nodesInList1 < nodesInList2) return addTwoNumbersWithoutExtraSpace(l2, l1);

        l1 = trimLeadingZeros(l1);
        l2 = trimLeadingZeros(l2);

        l1 = reverseLL(l1);
        l2 = reverseLL(l2);

        Node res = l1;

        int carry = 0;
        while (l2 != null || carry == 1) {
            l1.val += carry;

            if (l2 != null) {
                l1.val += l2.val;
                l2 = l2.next;
            }
            carry = l1.val / 10;
            l1.val = l1.val % 10;
            // If we are at the last node of l1 but carry is
            // still left, then append a new node to num1
            if (l1.next == null && carry != 0) {
                l1.next = new Node(0);
            }

            l1 = l1.next;
        }
        return reverseLL(res);
    }

    private static Node trimLeadingZeros(Node head) {
        while (head != null && head.val == 0) {
            head = head.next;
        }
        return head;
    }

    private static int countNodes(Node head) {
        int count = 0;
        if (head == null) return 0;
        Node tmp = head;
        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }
        return count;
    }

    private static Node reverseLL(Node head) {
        if (head == null || head.next == null) return head;
        Node tmp = head;
        Node prev = null;
        Node front = null;
        while (tmp != null) {
            front = tmp.next;
            tmp.next = prev;
            prev = tmp;
            tmp = front;
        }
        return prev;
    }
}

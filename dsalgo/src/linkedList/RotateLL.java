package linkedList;

import static linkedList.Node.createLL;
import static linkedList.Node.printLL;

public class RotateLL {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        // right rotate 2 times
        int k = 2;
        Node head = createLL(arr);
        head = rotateLL(head, k);
        printLL(head);
    }

    private static Node rotateLL(Node head, int k) {
        Node tail = head;
        int len = 1;
        while (tail.next != null) {
            len++;
            tail = tail.next;
        }
        if (k % len == 0) return head;
        k = k % len;
        tail.next = head;
        Node newLastNode = findNthNode(head, len - k);
        head = newLastNode.next;
        newLastNode.next = null;
        return head;
    }

    private static Node findNthNode(Node tmp, int n) {
        int cnt = 1;
        while (tmp != null) {
            if (cnt == n) {
                return tmp;
            }
            cnt++;
            tmp = tmp.next;
        }
        return tmp;
    }


}

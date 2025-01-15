package linkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static linkedList.Node.printLL;

public class MergeKSortedLists {
    public static void main(String[] args) {
        List<Node> arr = new ArrayList<>();

        arr.add(new Node(1));
        arr.get(0).next = new Node(3);
        arr.get(0).next.next = new Node(5);
        arr.get(0).next.next.next = new Node(7);

        arr.add(new Node(2));
        arr.get(1).next = new Node(4);
        arr.get(1).next.next = new Node(6);
        arr.get(1).next.next.next = new Node(8);

        arr.add(new Node(0));
        arr.get(2).next = new Node(9);
        arr.get(2).next.next = new Node(10);
        arr.get(2).next.next.next = new Node(11);

        Node head = mergeKLists(arr);

        printLL(head);
    }

    private static Node mergeKLists(List<Node> lists) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        // insert head node in PQ
        for (Node head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }

        Node dummy = new Node(-1);
        Node tail = dummy;
        while (!pq.isEmpty()) {
            Node top = pq.poll();
            tail.next = top;
            tail = top;

            if (tail.next != null) {
                pq.add(tail.next);
            }
        }
        return dummy.next;
    }
}

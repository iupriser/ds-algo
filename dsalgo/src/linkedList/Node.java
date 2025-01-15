package linkedList;

public class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
        this.next = null;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    static Node createLL(int[] arr) {
        Node head = new Node(arr[0]);
        Node tmp = head;
        for (int i = 1; i < arr.length; i++) {
            Node nextNode = new Node(arr[i]);
            tmp.next = nextNode;
            tmp = tmp.next;
        }
        return head;
    }

    static void printLL(Node head) {
        Node tmp = head;
        while (tmp != null) {
            System.out.print(tmp.val + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }
}

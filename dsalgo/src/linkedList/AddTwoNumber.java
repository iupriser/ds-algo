package linkedList;

import static linkedList.Node.createLL;
import static linkedList.Node.printLL;

//https://leetcode.com/problems/add-two-numbers/description/
public class AddTwoNumber {
    public static void main(String[] args) {
        // l1 = 345
        int[] num1 = {3, 4, 5};
        Node l1 = createLL(num1);
        // l2 = 645
        int[] num2 = {6, 4, 5};
        Node l2 = createLL(num2);
        Node summedList = addTwoNumbers(l1, l2);
        printLL(summedList);
    }

    private static Node addTwoNumbers(Node l1, Node l2) {
        Node dummy = new Node();
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
            tmp = tmp.next;
        }
        return dummy.next;

    }
}

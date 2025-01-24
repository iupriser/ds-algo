package linkedList;

import static linkedList.Node.createLL;
import static linkedList.Node.printLL;

//https://leetcode.com/problems/add-two-numbers/description/
public class AddTwoNumber {
    public static void main(String[] args) {
        int[] num1 = {2, 4, 3};
        //The digits are stored in reverse order
        // 3<-4<-2 , 3(MSD), 2(head and LSD)
        Node l1 = createLL(num1);
        int[] num2 = {5, 6, 4};
        //The digits are stored in reverse order
        // 4<-6<-5, 4(MSD), 5(head and LSD)
        Node l2 = createLL(num2);
        // 342+465=807, 8<-0<-7,  8(MSD), 7(head and LSD)
        Node summedList = addTwoNumbers(l1, l2);
        printLL(summedList);
    }

    // create a new LL to store sum for each digit as nodes
    private static Node addTwoNumbers(Node l1, Node l2) {
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
            tmp = tmp.next;
        }
        return dummy.next;
    }
}

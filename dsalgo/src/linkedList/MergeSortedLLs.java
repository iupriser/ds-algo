package linkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static linkedList.Node.createLL;
import static linkedList.Node.printLL;

public class MergeSortedLLs {
    public static void main(String[] args) {
        int[] arr1 = {2, 4, 8, 10};
        int[] arr2 = {1, 3, 3, 6, 11, 14};
        Node head1 = createLL(arr1);
        Node head2 = createLL(arr2);

        Node sortedListHead = mergeTwoSortedLLBrute(head1, head2);
        printLL(sortedListHead);
    }

    private static Node mergeTwoSortedLLBrute(Node head1, Node head2) {
        List<Integer> list = new ArrayList<>();
        Node tmp1 = head1, tmp2 = head2;
        while (tmp1 != null) {
            list.add(tmp1.val);
            tmp1 = tmp1.next;
        }
        while (tmp2 != null) {
            list.add(tmp2.val);
            tmp2 = tmp2.next;
        }

        Collections.sort(list);

        int[] arr = list.stream().mapToInt(i -> i).toArray();
        return createLL(arr);
    }
}

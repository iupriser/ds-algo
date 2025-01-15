package arrays.easy;

import java.util.ArrayList;
import java.util.List;

public class IntersectionOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 3, 4, 5, 6};
        int[] arr2 = {2, 3, 3, 5, 6, 7};
        List<Integer> intersectionArray;
        intersectionArray = insertion(arr1, arr2);

        intersectionArray.stream().forEach(e -> System.out.print(e + " "));
    }

    private static List<Integer> insertion(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;
        List<Integer> interesectionList = new ArrayList<>();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (arr1[i] == arr2[j]) {
                interesectionList.add(arr1[i]);
                i++;
                j++;
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return interesectionList;
    }

}

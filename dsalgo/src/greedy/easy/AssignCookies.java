package greedy.easy;

import java.util.Arrays;

//https://leetcode.com/problems/assign-cookies/description/
// TC: (NlogN + MlogM + M), SC: O(1)
public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        int cookiesNums = s.length;
        if (cookiesNums == 0)
            return 0;
        Arrays.sort(g);
        Arrays.sort(s);

        int maxNum = 0;
        int cookieIndex = s.length - 1;
        int childIndex = g.length - 1;
        while (cookieIndex >= 0 && childIndex >= 0) {
            if (s[cookieIndex] >= g[childIndex]) {
                maxNum++;
                cookieIndex--;
                childIndex--;
            } else {
                childIndex--;
            }
        }

        return maxNum;
    }

    public static void main(String[] args) {
        int[] greed = {1, 5, 3, 3, 4};
        int[] size = {4, 2, 1, 2, 1, 3};
        AssignCookies obj = new AssignCookies();
        int contentChildren = obj.findContentChildren(greed, size);
        System.out.println("Maximum number of content children: " + contentChildren);
    }
}

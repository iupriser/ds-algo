package greedy.easy;

// https://leetcode.com/problems/lemonade-change/description/
public class LemonadeChange {
    /**
     * 1 <= bills.length <= 105
     * bills[i] is either 5, 10, or 20.
     */
    public boolean lemonadeChange(int[] bills) {
        int fives = 0, tens = 0;
        // O(N)
        for (int bill : bills) {
            if (bill == 5) fives++;
            else if (bill == 10) {
                if (fives > 0) {
                    tens++;
                    fives--;
                } else {
                    return false;
                }
            } else {
                /**
                 * where is greedy approach in this question:
                 * suppose a customer pays 20 Rs then we first try to give him change by giving 10 Rs and 5 Rs
                 * and if are unable to do then we given three 5 Rs change ,
                 * in this way saving 5 Rs for future customers.
                 */
                if (tens > 0 && fives > 0) {
                    tens--;
                    fives--;
                } else if (fives >= 3) {
                    fives -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] bills = {5, 5, 5, 10, 20};
        LemonadeChange obj = new LemonadeChange();
        System.out.println(obj.lemonadeChange(bills));
    }
}

package stackQueue.implementationProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/online-stock-span/description/
public class StockSpanProblem {

    public static void main(String[] args) {
        /**
         * Your StockSpanner object will be instantiated and called as such:
         * StockSpanner obj = new StockSpanner();
         * int param_1 = obj.next(price);
         */
        int[] prices = {7, 2, 1, 3, 3, 1, 8};
//        StockSpanner obj = new StockSpanner();
//        for (int price : prices)
//            System.out.println(obj.next(price));

        StockSpannerBetter obj2 = new StockSpannerBetter();
        for (int price : prices)
            System.out.println(obj2.next(price));

    }
}

class StockSpanner {
    List<Integer> output;

    public StockSpanner() {
        output = new ArrayList<>();
    }

    public int next(int price) {
        output.add(price);
        int consecutiveDays = 1;
        for (int i = output.size() - 2; i >= 0; i--) {
            if (output.get(i) <= price) {
                consecutiveDays++;
            } else {
                break;
            }
        }
        return consecutiveDays;
    }
}

class Pair {
    int val;
    int index;

    Pair(int val, int index) {
        this.val = val;
        this.index = index;
    }
}

// use concept of previous greater element index (PSE)
class StockSpannerBetter {
    // store the index of PSE in stack
    Stack<Pair> stk;
    int indexCounter;

    public StockSpannerBetter() {
        stk = new Stack<>();
        indexCounter = 0;
    }

    public int next(int price) {
        int ans = 0;
        // getting PGE
        while (!stk.empty() && stk.peek().val <= price) {
            stk.pop();
        }
        // PSE occurs at peek of stack
        if (!stk.empty()) {
            ans = indexCounter - stk.peek().index;
            stk.add(new Pair(price, indexCounter));
        } else {
            stk.add(new Pair(price, indexCounter));
            ans = indexCounter + 1;
        }

        indexCounter++;
        return ans;
        // calculate PGE

//        // increment at the end
//        indexCounter++;
    }
}
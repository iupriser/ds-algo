package stackQueue.monotonicStack;

import java.util.Stack;

public class RemoveKDigits {
    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;
        String resultantNum = removeKdigits(num, k);
        System.out.println(resultantNum);
    }

    private static String removeKdigits(String num, int k) {
        Stack<Character> stk = new Stack<>();
        for (char digit : num.toCharArray()) {
            if (!stk.empty() && k > 0 && (stk.peek() - '0') > (digit - '0')) {
                stk.pop();
                k--;
            }
            stk.push(digit);
        }
        // remove remaining k digits
        while (!stk.empty() && k > 0) {
            stk.pop();
            k--;
        }

        // construct the resultant string from the stack
        StringBuilder sb = new StringBuilder();
        while (!stk.empty()) {
            sb.append(stk.pop());
        }
        sb.reverse();

        // remove leading zeros
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.length() > 0 ? sb.toString() : "0";
    }
}

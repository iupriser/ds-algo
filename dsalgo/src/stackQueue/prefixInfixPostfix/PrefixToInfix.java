package stackQueue.prefixInfixPostfix;

import java.util.Stack;

public class PrefixToInfix {
    public static void main(String[] args) {
        String prefix = "*+PQ-MN";
        System.out.println(prefixToInfixConversion(prefix.toCharArray()));
    }

    private static String prefixToInfixConversion(char[] prefix) {
        int l = prefix.length;
        Stack<String> stk = new Stack<>();
        for (int i = l - 1; i >= 0; i--) {
            char ch = prefix[i];
            if (Character.isLetterOrDigit(ch)) {
                stk.push(String.valueOf(ch));
            } else {
                String operand1 = stk.pop();
                String operand2 = stk.pop();
                String newValue = '(' + operand1 + ch + operand2 + ')';
                stk.push(newValue);
            }
        }
        return stk.pop();
    }

}

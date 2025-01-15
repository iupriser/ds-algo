package stackQueue.prefixInfixPostfix;

import java.util.Stack;

public class PostfixToInfix {
    public static void main(String[] args) {
        String postfix = "AB-DE+F*/";
        System.out.println(postfixToInfixConversion(postfix.toCharArray()));
    }

    private static String postfixToInfixConversion(char[] postfix) {
        int l = postfix.length;
        Stack<String> stk = new Stack<>();
        for (int i = 0; i < l; i++) {
            char ch = postfix[i];
            if (Character.isLetterOrDigit(ch)) {
                stk.push(String.valueOf(ch));
            } else {
                String operand1 = stk.pop();
                String operand2 = stk.pop();
                String newValue = '(' + operand2 + ch + operand1 + ')';
                stk.push(newValue);
            }
        }
        return stk.pop();
    }

}

package stackQueue.prefixInfixPostfix;

import java.util.Stack;

public class InfixToPrefix {
    public static void main(String[] args) {
        String s = ("(A+B)*^-D+F");
        System.out.print(infixToPrefixConversion(s.toCharArray()));
    }

    public static String infixToPrefixConversion(char[] infix) {
        int l = infix.length;
        // infix -> (A+B)*C-D+F
        String infix1 = reverse(infix, 0, l - 1);
        // infix1 -> F+D-C*)B+A(
        infix = infix1.toCharArray();

        for (int i = 0; i < l; i++) {
            if (infix[i] == ')') {
                infix[i] = '(';
            } else if (infix[i] == '(') {
                infix[i] = ')';
            }
        }
        // infix1 -> F+D-C*(B+A)
        String prefix = infixToPostfix(infix);

        // Reverse postfix
        prefix = reverse(prefix.toCharArray(), 0, prefix.length() - 1);
        return prefix;
    }

    private static String infixToPostfix(char[] infix1) {
        // infix -> u+w/z*y+x
        String infix = '(' + String.valueOf(infix1) + ')';
        // infix -> (u+w/z*y+x)
        int l = infix.length();
        Stack<Character> stk = new Stack<>();
        String output = "";

        for (int i = 0; i < l; i++) {
            char ch = infix.charAt(i);
            // If the scanned character is an
            // operand, add it to output.
            if (Character.isLetterOrDigit(ch)) output += ch;

                // If the scanned character is an
                // ‘(‘, push it to the stack.
            else if (ch == '(') stk.add('(');

                // If the scanned character is an
                // ‘)’, pop and output from the stack
                // until an ‘(‘ is encountered.
            else if (ch == ')') {
                while (!stk.isEmpty() && stk.peek() != '(') {
                    output += stk.pop();
                }
                // Remove '(' from the stack
                stk.pop();
            }

            // Operator found
            else {
                while ((getPriority(ch) < getPriority(stk.peek())) || (getPriority(ch) <= getPriority(stk.peek()) && ch == '^')) {
                    output += stk.pop();
                }

                // Push current Operator on stack
                stk.add(ch);
            }
        }
        while (!stk.empty()) {
            output += stk.pop();
        }
        return output;
    }

    private static String reverse(char[] str, int start, int end) {
        char tmp;
        while (start < end) {
            tmp = str[start];
            str[start] = str[end];
            str[end] = tmp;
            start++;
            end--;
        }
        return String.valueOf(str);
    }

    private static int getPriority(char ch) {
        if (ch == '-' || ch == '+') return 1;
        else if (ch == '*' || ch == '/') return 2;
        else if (ch == '^') return 3;

        return 0;
    }
}

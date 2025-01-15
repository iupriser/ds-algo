package bitManipulation.questions;

public class DivideTwoInteger {

    public static void main(String[] args) {
        int dividend = -2147483648, divisor = -1;
        System.out.println(divide(dividend, divisor));
    }

    private static int divide(int dividend, int divisor) {
        if (dividend == divisor)
            return 1;
        boolean sign = true; // positive
        if (dividend >= 0 && divisor < 0)
            sign = false;
        else if (dividend < 0 && divisor > 0)
            sign = false;

        long n = Math.abs((long) dividend);
        long d = Math.abs(divisor);
        long ans = 0;

        while (n >= d) {
            int cnt = 0;
            while (n >= (d << (cnt + 1))) {
                cnt++;
            }
            ans = ans + (1L << cnt);
            n = n - (d << cnt);
        }

        if (ans > (1L << 31) - 1 && sign) {
            return Integer.MAX_VALUE;
        }
        if (!sign && ans > (1L << 31)) {
            return Integer.MIN_VALUE;
        }

        return sign ? (int) ans : (int) (-1 * ans);
    }
}

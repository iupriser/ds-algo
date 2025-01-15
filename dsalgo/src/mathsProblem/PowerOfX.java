package mathsProblem;

public class PowerOfX {

    public static void main(String[] args) {
        //pow(x,n)
        int x = 2, n = 5;
        System.out.println(powerOfX(x, n));
        double y = 2;
        n = -2147483648;
        System.out.println(powerOfX_negativeN(y, n));

    }

    private static int powerOfX(int x, int n) {
        int ans = 1;
        while (n > 0) {
            // power(exponent) is even
            if (n % 2 == 0) {
                x = x * x;
                n = n / 2;
            }
            // power is odd
            else {
                ans *= x;
                n--;
            }
        }
        return ans;
    }

    private static double powerOfX_negativeN(double x, int n) {
        if (x == 0 || x == 1) return x;  // Handle base cases
        boolean isNegative = (n < 0);    // Check if exponent is negative
        if (isNegative) {
            x = 1 / x;                  // Invert x for negative exponent
            if (n == Integer.MIN_VALUE) {
                n = -(n + 2);  // Adjust n for the overflow case
            } else {
                n = -n;                 // Convert n to positive
            }
        }

        int pow = n;
        double ans = 1;
        while (pow > 0) {
            if (pow % 2 == 0) {
                x = x * x;
                pow /= 2;
            } else {
                ans *= x;
                pow = pow - 1;
            }
        }
        return ans;
    }
}

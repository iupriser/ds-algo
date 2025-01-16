package mathsProblem;

// https://www.geeksforgeeks.org/finding-sum-of-digits-of-a-number-until-sum-becomes-single-digit/
public class RepetitiveAdditionOfDigits {
    public static void main(String[] args) {
        int num = 5674;
        System.out.println("Single digit left after repetitive addition of digits: " + singleDigitBrute(num));
        System.out.println("Single digit left after repetitive addition of digits: " + singleDigitOptimal(num));

    }

    static int singleDigitBrute(int n) {
        int sum = 0;
        while (n > 0 || sum > 9) {
            if (n == 0) {
                n = sum;
                sum = 0;
            }
            sum = sum + (n % 10);
            n = n / 10;
        }
        return sum;
    }

    static int singleDigitOptimal(int n) {
        if (n == 0) return 0;
        if (n % 9 == 0) {
            return 9;
        }
        return (n % 9);
    }

}

package mathsProblem;

public class ArmstrongNumber {
    public static void main(String[] args) {
        int num = 371;
        System.out.println(isArmstrongNumber(num));
    }

    private static boolean isArmstrongNumber(int num) {
        int sum = 0;
        int dup = num;
        while (num > 0) {
            int lastDigit = num % 10;
            num = num / 10;
            sum = sum + (int) Math.pow(lastDigit, 3);
        }
        if (sum == dup) {
            return true;
        }
        return false;
    }
}

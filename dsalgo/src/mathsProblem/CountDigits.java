package mathsProblem;

public class CountDigits {

    public static void main(String[] args) {
        int num = 0;
        printDigit(num);
        System.out.println();
        printDigitRec(num);
        System.out.println();
        System.out.println(countDigit(num));
        System.out.println(countDigit_log(num));
    }

    private static void printDigit(int num) {
        while (num > 0) {
            int lastDigit = num % 10;
            System.out.print(lastDigit + " ");
            num = num / 10;
        }
    }

    private static void printDigitRec(int num) {
        if (num == 0) return;
        int lastDigit = num % 10;
        printDigitRec(num / 10);
        System.out.print(lastDigit + " ");
    }

    private static int countDigit(int num) {
        int cnt = 0;
        while (num > 0) {
            num = num / 10;
        }
        return cnt;
    }

    private static int countDigit_log(int num) {
        return (int) Math.log10(num) + 1;
    }
}

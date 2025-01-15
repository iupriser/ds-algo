package mathsProblem;

public class ReverseNumber {

    public static void main(String[] args) {
        int num = 7789;
        System.out.println(revNum(num));

    }

    private static int revNum(int num) {
        int revNum = 0;
        while (num > 0) {
            int lastDigit = num % 10;
            num = num / 10;
            revNum = revNum * 10 + lastDigit;
        }
        return revNum;
    }

}

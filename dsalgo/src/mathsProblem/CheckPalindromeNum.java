package mathsProblem;

public class CheckPalindromeNum {

    public static void main(String[] args) {
        int num = 121;
        System.out.println(checkPalindrome(num));

    }

    private static boolean checkPalindrome(int num) {
        int dup = num;
        int revNum = 0;
        while (dup > 0) {
            int lastDigit = dup % 10;
            dup = dup / 10;
            revNum = revNum * 10 + lastDigit;
        }
        return revNum == num ? true : false;
    }
}

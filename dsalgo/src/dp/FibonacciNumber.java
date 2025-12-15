package dp;

import java.util.Arrays;

public class FibonacciNumber {
    private final int[] dp;

    public FibonacciNumber(int n) {
        this.dp = new int[n + 1];
        Arrays.fill(dp, -1);
    }

    private int fibNum(int n) {
        if (n <= 1) return n;
        return fibNum(n - 1) + fibNum(n - 2);
    }

    // TC:O(N), SC:O(N) + O(N)
    private int fiboMemoization(int n) {
        if (n <= 1) return n;
        if (dp[n] != -1) {
            return dp[n];
        }
        return dp[n] = fiboMemoization(n - 1) + fiboMemoization(n - 2);
    }

    // TC:O(N), SC:O(N)
    private int fiboTabulation(int n) {
        // base case to main(required n) {Bottom-Up}
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // TC:O(N), SC:O(1)
    private int fiboSpaceOptimization(int n) {
        int prev2 = 0, prev1 = 1;
        if (n <= 1) return n;
        for (int i = 2; i <= n; i++) {
            int curr = prev2 + prev1;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    public static void main(String[] args) {
        int n = 5;
        FibonacciNumber obj = new FibonacciNumber(n);
        // calculating fibonacci number using basic recursion
        int fibonacciUsingRec = obj.fibNum(n);
        System.out.println("calculating fibonacci number using recursion: " + fibonacciUsingRec);

        // calculating fibonacci number using Memoization(Top-Down)
        int fibonacciUsingMemo = obj.fiboMemoization(n);
        System.out.println("calculating fibonacci number using Memoization(Top-Down): " + fibonacciUsingMemo);

        // calculating fibonacci number using Tabulation(Bottom-Up)
        int fibonacciUsingTabulation = obj.fiboTabulation(n);
        System.out.println("calculating fibonacci number using Tabulation(Bottom-Up): " + fibonacciUsingTabulation);

        // calculating fibonacci number using Space Optimization
        int fibonacciUsingSpaceOptimization = obj.fiboSpaceOptimization(n);
        System.out.println("calculating fibonacci number using Space Optimization: " + fibonacciUsingSpaceOptimization);

    }

}

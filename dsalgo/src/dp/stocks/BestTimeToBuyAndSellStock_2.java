package dp.stocks;

public class BestTimeToBuyAndSellStock_2 {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
//		int[] prices = {7,6,4,3,1};
        int profit = 0;
        profit = maxProfit_recursion(prices);
        System.out.println(profit);

    }

    public static int maxProfit_recursion(int[] prices) {
        return helper(prices, 0, true);
    }

    private static int helper(int[] prices, int index, boolean buy) {
        // base case
        if (index == prices.length) {
            return 0;
        }
        // buy it --> sell / ignore
        int profit = 0;
        if (buy) {
            profit = Math.max(-prices[index] + helper(prices, index + 1, false), 0 + helper(prices, index + 1, true));
        }
        // sell it --> buy / ignore
        else {
            profit = Math.max(prices[index] + helper(prices, index + 1, true), 0 + helper(prices, index + 1, false));
        }
        // ignore --> buy / sell / ignore
        return profit;
    }
}

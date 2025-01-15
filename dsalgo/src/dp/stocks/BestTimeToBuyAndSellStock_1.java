package dp.stocks;

public class BestTimeToBuyAndSellStock_1 {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
//		int[] prices = {7,6,4,3,1};
        int profit = 0;
        profit = maxProfit_brute(prices);
        System.out.println(profit);
        profit = maxProfit_optimized(prices);
        System.out.println(profit);

    }

    private static int maxProfit_brute(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int profit = 0;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    profit = prices[j] - prices[i];
                    maxProfit = Math.max(maxProfit, profit);
                }
            }
        }
        return maxProfit;
    }

    public static int maxProfit_optimized(int[] prices) {
        int profit = 0;
        int mini = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int cost = prices[i] - mini;
            profit = Math.max(profit, cost);
            mini = Math.min(mini, prices[i]);
        }
        return profit;
    }
}

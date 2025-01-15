package arrays.medium;

public class BuySellStock1 {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
//		int[] prices = {7,6,4,3,1};
        int profit = 0;
        profit = maxProfit_brute(prices);
        System.out.println(profit);
        profit = maxProfit_optimized(prices);
        System.out.println(profit);
        profit = maxProfit_optimized2(prices);
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

    private static int maxProfit_optimized(int[] prices) {
        // buy at lowest and sell at highest after low has occured
        int mini = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < prices.length; i++) {
            if (mini > prices[i]) {
                mini = prices[i];
                minIndex = i;
            }
        }
        int maxi = Integer.MIN_VALUE;
//		int maxProfit = 0;
        for (int i = minIndex; i < prices.length; i++) {
            maxi = Math.max(maxi, prices[i]);
        }

        return maxi - mini;

    }

    private static int maxProfit_optimized2(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

}

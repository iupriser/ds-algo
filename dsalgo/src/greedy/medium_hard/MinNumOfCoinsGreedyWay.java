package greedy.medium_hard;

import java.util.ArrayList;
import java.util.List;

public class MinNumOfCoinsGreedyWay {
    public static void main(String[] args) {
        int[] coins = {1,2,5,10,20,50,100,500,1000};
        int V = 49;
        List<Integer> minCoinsList = findMinCoins(coins, V);
        System.out.println(minCoinsList);
        int minCoins = minCoinsList.size();
        System.out.println("Minimum number of coins that are required: "+ minCoins);
    }

    private static List<Integer> findMinCoins(int[] coins, int v) {
        int n = coins.length;
        List<Integer> ans = new ArrayList<>();
        // traversing the coins from largest to smallest
        for(int i=n-1; i>=0; i--) {
            // use as many coins[i] as possible
            while(v >= coins[i]) {
                v = v - coins[i];
                ans.add(coins[i]);
            }
        }
        return ans;
    }

}

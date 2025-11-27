package greedy.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * The weight of N items and their corresponding values are given.
 * We have to put these items in a knapsack of weight W such that the total value obtained is maximized.
 *
 * Note: We can either take the item as a whole or break it into smaller units.
 */
public class FractionalKnapsackAlgo {
    static class ItemComparator implements Comparator<Item> {
        @Override
        public int compare(Item a, Item b) {
            double r1 = (double)a.value / (double) a.weight;
            double r2 = (double)b.value / (double) b.weight;
            return Double.compare(r2,r1);
        }
    }

    private double maxSum(int W, Item[] arr, int n) {
        Arrays.sort(arr, new ItemComparator());

        int currWeight = 0;
        double finalValue = 0.0;

        for(int i=0; i<n; i++) {
            if(currWeight + arr[i].weight <= W) {
                currWeight += arr[i].weight;
                finalValue += arr[i].value;
            }else {
                int remain = W- currWeight;
                finalValue += (arr[i].weight/(double) arr[i].weight)*(double) remain;
                break;
            }
        }
        return finalValue;
    }
    public static void main(String[] args) {
        int n = 3, weight = 50;
        Item[] arr = {new Item(100,20), new Item(60,10), new Item(120,30)};
        FractionalKnapsackAlgo obj = new FractionalKnapsackAlgo();
        double ans = obj.maxSum(weight, arr, n);
        System.out.println("The maximum value is: "+ String.format("%.2f", ans));
    }
}
class Item {
    int value;
    int weight;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}



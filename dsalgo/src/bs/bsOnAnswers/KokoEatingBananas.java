package bs.bsOnAnswers;

/**
 * https://takeuforward.org/binary-search/koko-eating-bananas/
 * A monkey is given ‘n’ piles of bananas, whereas the 'ith' pile has ‘a[i]’ bananas.
 * An integer ‘h’ is also given, which denotes the time (in hours) for all the bananas to be eaten.
 * Each hour, the monkey chooses a non-empty pile of bananas and eats ‘k’ bananas. If the pile
 * contains less than ‘k’ bananas, then the monkey consumes all the bananas and won’t eat any more bananas in that hour.
 *
 * Find the minimum number of bananas ‘k’ to eat per hour so that the monkey can eat all the bananas within ‘h’ hours.
 */
public class KokoEatingBananas {
    public static void main(String[] args) {
        int[] piles = {3,6,7,11};
        int h = 8;
        int ans = minimumRateToEatBananas(piles,h);
        System.out.println("Koko should eat at least " + ans + " bananas/hr.");
    }

    /**
     *  range of answer will lie in [1,max(piles[])]
     *  [3,6,7,11], if k
     */
    private static int minimumRateToEatBananas(int[] piles, int allowedHours) {
        int maxi = findMax(piles);
        int low = 1, high = maxi;
        while(low<=high){
            int mid = (low+high)/2;
            int midH = totalNumberOfHoursTakenToEatAllBananas(piles,mid);
            if(midH <= allowedHours){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        // low is pointing to minimum possible K
        return low;
    }

    private static int totalNumberOfHoursTakenToEatAllBananas(int[] piles, int K) {
        int count = 0;
        for (int pile : piles) {
            count += (int) Math.ceil((double) pile / (double) K);
        }
        return count;
    }

    static int findMax(int[] piles){
        int maxi = Integer.MIN_VALUE;
        for (int pile : piles) {
            maxi = Math.max(maxi, pile);
        }
        return maxi;
    }

}

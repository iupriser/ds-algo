package bs.bsOnAnswers;

/**
 * https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/
 * https://takeuforward.org/arrays/minimum-days-to-make-m-bouquets/
 *
 */
public class MinimumDaysToMake_mBouquets {
    public static void main(String[] args) {
//        int[] bloomDay = {7,7,7,7,13,11,12,7};
//        int M = 2; // number of bouquets
//        int K =3; // number of flowers in each bouquet
        int[] bloomDay = {1000000000,1000000000};
        int M = 1; // number of bouquets
        int K =1; // number of flowers in each bouquet

        int minDays = findMinDaysToMake_mBouquetsBrute(bloomDay,M,K);
        System.out.println("minimum number of days required to make bouquets: "+ minDays);
        minDays = findMinDaysToMake_mBouquetsOptimized(bloomDay,M,K);
        System.out.println("minimum number of days required to make bouquets: "+ minDays);
    }

    private static int findMinDaysToMake_mBouquetsBrute(int[] bloomDay, int m, int k) {
        int numOfFlowers = bloomDay.length;
        // case where answer is not possible
        if((numOfFlowers/k) < m) return -1;

        // range of answer -> [low,high]
        int mini = Integer.MAX_VALUE;
        int maxi = Integer.MIN_VALUE;
        for (int day: bloomDay) {
            mini = Math.min(mini, day);
            maxi = Math.max(maxi,day);
        }

        // case where answer is possible
        for(int day=mini; day<=maxi; day++){
            int numBouquet = numBouquetPossibleOnCurrentBloomDay(bloomDay,day,k);
            if(numBouquet >= m) return day;
        }
        return -1;

    }

    private static int findMinDaysToMake_mBouquetsOptimized(int[] bloomDay, int m, int k) {
        int numOfFlowers = bloomDay.length;
        // case where answer is not possible
        if((numOfFlowers/k) < m) return -1;

        // range of answer -> [low,high]
        int mini = Integer.MAX_VALUE;
        int maxi = Integer.MIN_VALUE;
        for (int day: bloomDay) {
            mini = Math.min(mini, day);
            maxi = Math.max(maxi,day);
        }

        int low = mini, high = maxi;

        // case where answer is possible
        while(low<=high){
            int mid = low+(high-low)/2;
            int numBouquet = numBouquetPossibleOnCurrentBloomDay(bloomDay,mid,k);
            if(numBouquet>=m) high = mid-1;
            else low = mid+1;
        }
        return low;
    }

    private static int numBouquetPossibleOnCurrentBloomDay(int[] bloomDay, int day, int k) {
        int count = 0;
        int numOfBouquets = 0;
        for(int i=0; i<bloomDay.length; i++){
            if(bloomDay[i]<=day){
                count++;
            }else{
                numOfBouquets += count/k;
                count = 0;
            }
        }
        // if count != 0, then the count is because of last elements
        numOfBouquets+=count/k;
        return numOfBouquets;
    }


}

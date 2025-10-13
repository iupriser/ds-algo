package greedy.medium_hard;

public class Candy {
    /**
     * TC:O(3N)
     * SC:O(2N)
     */
    public int candyBrute(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        right[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i - 1] < ratings[i]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        for (int j = n - 2; j >= 0; j--) {
            if (ratings[j + 1] < ratings[j]) {
                right[j] = right[j + 1] + 1;
            } else {
                right[j] = 1;
            }
        }

        int minCandy = 0;
        for (int i = 0; i < n; i++) {
            minCandy += Math.max(left[i], right[i]);
        }
        return minCandy;
    }

    /**
     * TC:O(2N)
     * SC:O(N)
     */
    public int candyBetter(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i - 1] < ratings[i]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 1, curr = 1;// curr tell the value at index 'j'
        int minCandy = Math.max(left[n - 1], right);
        for (int j = n - 2; j >= 0; j--) {
            if (ratings[j + 1] < ratings[j]) {
                curr = right + 1;
                right = curr;
            } else {
                curr = 1;
            }
            minCandy += Math.max(left[j], curr);
        }

        return minCandy;
    }

    /**
     * SLOPE APPROACH
     * TC:O(2N)
     * SC:O(1)
     */
    public int candyOptimal(int[] ratings) {
        int n = ratings.length;
        int sum = 1, i = 1;
        while (i < n) {
            // flat surface
            if (ratings[i - 1] == ratings[i]) {
                sum += 1;
                i++;
                continue;
            }
            int peak = 1;
            // upward slope
            while (i < n && ratings[i] > ratings[i - 1]) {
                peak += 1;
                sum += peak;
                i++;
            }
            int down = 1;
            while (i < n && ratings[i] < ratings[i - 1]) {
                sum += down;
                down += 1;
                i++;
            }
            if (down > peak) {
                sum += (down - peak);
            }
        }

        return sum;

//
//        int[] left = new int[n];
//        left[0] = 1;
//        for (int i = 1; i < n; i++) {
//            if (ratings[i - 1] < ratings[i]) {
//                left[i] = left[i - 1] + 1;
//            } else {
//                left[i] = 1;
//            }
//        }
//        int right = 1, curr = 1;// curr tell the value at index 'j'
//        int minCandy = Math.max(left[n - 1], right);
//        for (int j = n - 2; j >= 0; j--) {
//            if (ratings[j + 1] < ratings[j]) {
//                curr = right + 1;
//                right = curr;
//            } else {
//                curr = 1;
//            }
//            minCandy += Math.max(left[j], curr);
//        }
//
//        return minCandy;
    }

    public static void main(String[] args) {
        int[] ratings = {0, 2, 4, 7, 6, 5, 4, 3, 2, 1, 1, 1, 2, 3, 4, 2, 1, 1, 1};
        Candy obj = new Candy();
        int candyRequired = obj.candyBrute(ratings);
        System.out.println("Number of candies required: " + candyRequired);
        System.out.println("--------------------------------------------");
        int candyRequiredOptimal = obj.candyOptimal(ratings);
        System.out.println("Number of candies required: " + candyRequiredOptimal);
    }
}

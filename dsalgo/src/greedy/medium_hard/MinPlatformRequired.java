package greedy.medium_hard;

import java.util.Arrays;

public class MinPlatformRequired {
    private int minPlatformBrute(int[] arr, int[] dep) {
        int n = arr.length;
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            // initially one platform is needed
            int cnt = 1;
            for (int j = i + 1; j < n; j++) {
                // overlapping/intersecting checks
                if ((arr[i] >= arr[j] && arr[i] <= dep[j]) ||
                        (arr[j] >= arr[i] && arr[j] <= dep[i])) {
                    cnt++;
                }
                maxCount = Math.max(maxCount, cnt);
            }
        }
        return maxCount;
    }

    private int minPlatformOptimal(int[] arr, int[] dep) {
        int numTrains = arr.length;
        Arrays.sort(arr);
        Arrays.sort(dep);
        int maxPlatforms = 0;
        int currPlatformReq = 0;
        int p1 = 0, p2 = 0;
        while (p1 < numTrains) {
            if (arr[p1] <= dep[p2]) {
                currPlatformReq++;
                p1++;
            } else {
                currPlatformReq--;
                p2++;
            }
            maxPlatforms = Math.max(maxPlatforms, currPlatformReq);
        }
        return maxPlatforms;
    }

    public static void main(String[] args) {
        MinPlatformRequired obj = new MinPlatformRequired();
        int[] arrival = {900, 945, 955, 1100, 1500};
        int[] departure = {920, 1200, 1130, 1150, 2000};
        int minPlatform = obj.minPlatformBrute(arrival, departure);
        System.out.println("minimum number of platforms required : " + minPlatform);
        System.out.println("--------------------");
        minPlatform = obj.minPlatformOptimal(arrival, departure);
        System.out.println("minimum number of platforms required : " + minPlatform);
    }
}

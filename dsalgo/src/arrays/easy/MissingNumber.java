package arrays.easy;

public class MissingNumber {

    public static void main(String[] args) {
//		int[] nums = {3,0,1};
//		int[] nums = {0,1};
        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        int n = nums.length;
        int missingNum = findMissingNumber(nums, n);
        System.out.println("missing number in array: " + missingNum + "\n");
        missingNum = findMissingNumberByHashing(nums, n);
        System.out.println("missing number in array: " + missingNum + "\n");
        missingNum = findMissingNumberByXoring(nums, n);
        System.out.println("missing number in array: " + missingNum + "\n");
    }

    private static int findMissingNumber(int[] nums, int n) {
        int sumOfNnumbers = n * (n + 1) / 2;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        int missingNum = sumOfNnumbers - sum;
        if (missingNum == 0) {
            return 0;
        }
        return missingNum;
    }

    private static int findMissingNumberByHashing(int[] nums, int n) {
        int[] hash = new int[n + 1];
        for (int i = 0; i < n; i++) {
            hash[nums[i]]++;
        }

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] == 0) {
                return i;
            }
        }
        return -1;

//		HashMap<Integer, Integer> map = new HashMap<>();
//		for (int i = 0; i <= n; i++) {
//			map.put(i, 0);
//		}
//		// storing frequency in map
//		for (int num : nums) {
//			map.put(num, map.get(num) + 1);
//		}
//
//		// checking frequency of element with zero
//		for (var entry : map.entrySet()) {
//			if (entry.getValue() == 0) {
//				return entry.getKey();
//			}
//		}
//		return -1;
    }

    private static int findMissingNumberByXoring(int[] nums, int n) {
        int xor1 = 0, xor2 = 0;
        for (int i = 0; i <= n; i++) {
            xor1 = xor1 ^ i;
        }
        for (int i = 0; i < n; i++) {
            xor2 = xor2 ^ nums[i];
        }
        return xor1 ^ xor2;
    }

}

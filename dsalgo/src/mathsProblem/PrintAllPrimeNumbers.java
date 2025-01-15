package mathsProblem;

import java.util.ArrayList;
import java.util.List;

public class PrintAllPrimeNumbers {

    public static void main(String[] args) {
        int N = 100;
        int[] ans = allPrimeFactors(N);
        for (Integer num : ans) {
            System.out.print(num + " ");
        }
    }

    private static int[] allPrimeFactors(int N) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i * i <= N; i++) {
            if (N % i == 0) {
                list.add(i);
                while (N % i == 0) {
                    N = N / i;
                }
            }
        }
        if (N != 1) {
            list.add(N);
        }
        int[] ans = list.stream().mapToInt(Integer::intValue).toArray();
        return ans;
    }

}

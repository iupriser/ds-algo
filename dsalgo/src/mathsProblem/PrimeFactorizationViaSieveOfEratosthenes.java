package mathsProblem;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorizationViaSieveOfEratosthenes {

    static int[] spf;
    // 1<=N<=2*10^5
    static int maxNum = 2 * (int) Math.pow(10, 5);

    public static void main(String[] args) {
        int N = 25;
        List<Integer> list = findPrimeFactors(N);
        list.stream().forEach(e -> System.out.print(e + " "));

    }

    static void sieve() {
        spf = new int[maxNum + 1];
        for (int i = 1; i < maxNum; i++) {
            spf[i] = i;
        }

        for (int i = 2; i * i <= maxNum; i++) {
            // if number is prime
            if (spf[i] == i) {
                for (int j = i * i; j <= maxNum; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
    }

    static List<Integer> findPrimeFactors(int N) {
        List<Integer> primes = new ArrayList<>();
        while (N != 1) {
            primes.add(spf[N]);
            N = N / spf[N];
        }
        return primes;
    }
}

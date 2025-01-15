package mathsProblem;

import java.util.Arrays;

public class PrintAllPrimeTillN_SieveOfEratosthenes {

    public static void main(String[] args) {
        int num = 100;
        printAllPrimeNumber(num);
        printAllPrimeNumberOptimized(num);
    }

    private static void printAllPrimeNumber(int num) {
        int[] primes = new int[num + 1];
        Arrays.fill(primes, 1);

        for (int i = 2; i <= num; i++) {
            if (primes[i] == 1) {
                // mark all the multiple of i with zero
                for (int j = i * 2; j <= num; j += i) {
                    primes[j] = 0;
                }
            }
        }
        // all the numbers in primes array with value 1 are prime, except 0 and 1
        for (int i = 2; i <= num; i++) {
            if (primes[i] == 1) {
                System.out.print(i + " ");
            }
        }
    }

    private static void printAllPrimeNumberOptimized(int num) {
        int[] primes = new int[num + 1];
        Arrays.fill(primes, 1);

        for (int i = 2; i <= num; i++) {
            if (primes[i] == 1) {
                // mark all the multiple of i with zero
                for (int j = i * 2; j <= num; j += i) {
                    primes[j] = 0;
                }
            }
        }
        // all the numbers in primes array with value 1 are prime, except 0 and 1
        for (int i = 2; i <= num; i++) {
            if (primes[i] == 1) {
                System.out.print(i + " ");
            }
        }
    }

}

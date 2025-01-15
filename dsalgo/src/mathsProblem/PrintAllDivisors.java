package mathsProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintAllDivisors {

    public static void main(String[] args) {
        int num = 36;
        printDivisors_bf(num);
        System.out.println();
        printDivisors_optimized(num);
    }

    private static void printDivisors_bf(int num) {
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                System.out.print(i + " ");
            }
        }
    }

    private static void printDivisors_optimized(int num) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                list.add(i);
                if (num / i != i) {
                    list.add(num / i);
                }
            }
        }
        Collections.sort(list);
        list.stream().forEach(e -> System.out.print(e + " "));

    }

}
	
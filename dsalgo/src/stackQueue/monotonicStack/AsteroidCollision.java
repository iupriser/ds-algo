package stackQueue.monotonicStack;

import java.util.Arrays;
import java.util.Stack;

public class AsteroidCollision {
    public static void main(String[] args) {
        int[] asteroids = {4, 7, 1, 1, 2, -3, -7, 17, 15, -16};
        int[] finalState = asteroidCollision(asteroids);
        System.out.println(Arrays.toString(finalState));
    }

    private static int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        Stack<Integer> stk = new Stack<>();

        for (int a : asteroids) {
            if (a > 0) {
                stk.add(a);
            } else {
                int currentAsteroid = Math.abs(a);
                while (!stk.empty() && stk.peek() > 0 && stk.peek() < currentAsteroid) {
                    stk.pop();
                }
                if (stk.empty() || stk.peek() < 0) {
                    stk.push(a);
                }
                if (!stk.empty() && stk.peek() == currentAsteroid) {
                    stk.pop();
                }
            }
        }
        int[] finalState = new int[stk.size()];
        int i = stk.size() - 1;

        while (!stk.empty()) {
            finalState[i--] = stk.pop();
        }
        return finalState;
    }
}

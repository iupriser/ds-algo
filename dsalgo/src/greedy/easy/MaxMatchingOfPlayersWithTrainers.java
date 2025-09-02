package greedy.easy;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-matching-of-players-with-trainers/description/
// similar to assign cookies
public class MaxMatchingOfPlayersWithTrainers {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int maxNum = 0;
        int l = players.length - 1;
        int r = trainers.length - 1;
        while (l >= 0 && r >= 0) {
            if (trainers[r] >= players[l]) {
                maxNum++;
                l--;
                r--;
            } else {
                l--;
            }
        }
        return maxNum;
    }

    public static void main(String[] args) {
        int[] players = {4, 7, 9}, trainers = {8, 2, 5, 8};
        MaxMatchingOfPlayersWithTrainers obj = new MaxMatchingOfPlayersWithTrainers();
        System.out.println(obj.matchPlayersAndTrainers(players, trainers));

    }
}

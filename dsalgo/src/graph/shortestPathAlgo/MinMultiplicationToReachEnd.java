package graph.shortestPathAlgo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
//https://www.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1
public class MinMultiplicationToReachEnd {
    int minimumMultiplications(int[] arr, int start, int end) {
        if(start == end) return 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, start));
        int[] dist = new int[100000];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        int mod = 100000;
        int n = arr.length;

        while(!q.isEmpty()) {
            int steps = q.peek().steps;
            int node = q.peek().node;
            q.remove();

            for(int num: arr){
                int newNode = (node*num)%mod;
                if(steps+1 < dist[newNode]) {
                    dist[newNode] = steps+1;
                    if(newNode == end) return steps+1;
                    q.add(new Pair(steps+1, newNode));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr= {2,5,7};
        int start = 3, end = 30;
        MinMultiplicationToReachEnd obj = new MinMultiplicationToReachEnd();
        int minimumedMultiplications = obj.minimumMultiplications(arr, start, end);
        System.out.println(minimumedMultiplications);
    }
}
class Pair{
    int steps;
    int node;
    public Pair(int steps, int node){
        this.steps = steps;
        this.node = node;
    }
}
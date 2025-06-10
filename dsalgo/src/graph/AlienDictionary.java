package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//O(n * m) , where n is size of array arr[], m is the size of each string arr[i]
public class AlienDictionary {
    public String findOrder(String[] words) {
        // create Directed graph
        ArrayList<ArrayList<Integer>> graphAdj = new ArrayList<>();
        // Initialize graph
        for (int i = 0; i < 26; i++) {
            graphAdj.add(new ArrayList<>());
        }
        int[] inDegree = new int[26];
        boolean[] exists = new boolean[26];

        // mark the characters that exist in the input
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                exists[ch - 'a'] = true;
            }
        }

        // build graph based on adjacent word comparison
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int len = Math.min(w1.length(), w2.length());
            int ptr = 0;
            while (ptr < len && w1.charAt(ptr) == w2.charAt(ptr)) {
                ptr++;
            }
            if (ptr < len) {
                // edge from u->v
                int u = w1.charAt(ptr) - 'a';
                int v = w2.charAt(ptr) - 'a';
                graphAdj.get(u).add(v);
                inDegree[v]++;
            } else if (w1.length() > w2.length()) {
                // invalid input
                return "";
            }
        }

        // Directed graph is created, now to get the order of Directed graph,
        // run topological sort
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (exists[i] && inDegree[i] == 0) {
                q.add(i);
            }
        }

        StringBuilder result = new StringBuilder();
        while (!q.isEmpty()) {
            int u = q.poll();
            result.append((char) (u + 'a'));

            for (int v : graphAdj.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    q.add(v);
                }
            }
        }

        // Check for cycle
        for (int i = 0; i < 26; i++) {
            if (exists[i] && inDegree[i] != 0) {
                return "";
            }
        }

        return result.toString();
    }


    public static void main(String[] args) {
        String[] words = {"baa", "abcd", "abca", "cab", "cad"};
        AlienDictionary obj = new AlienDictionary();
        String order = obj.findOrder(words);
        System.out.println("order of Alien Dictionary: " + order);
    }
}

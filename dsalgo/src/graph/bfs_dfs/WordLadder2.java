package graph.bfs_dfs;

import java.util.*;

//https://leetcode.com/problems/word-ladder-ii/description/
public class WordLadder2 {
    public ArrayList<ArrayList<String>> findSequences(String startWord, String targetWord,
                                                      String[] wordList) {
        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }
        Queue<ArrayList<String>> q = new LinkedList<>();
        ArrayList<String> initialSeq = new ArrayList<>();
        initialSeq.add(startWord);
        q.add(initialSeq);

        ArrayList<String> usedOnLevel = new ArrayList<>();
        usedOnLevel.add(startWord);
        int level = 0;
        ArrayList<ArrayList<String>> ans = new ArrayList<>();

        while (!q.isEmpty()) {
            ArrayList<String> currSeq = q.peek();
            q.remove();

            // erase all words that has been used in the previous levels to
            // transform
            if (currSeq.size() > level) {
                level++;
                for (String it : usedOnLevel) {
                    set.remove(it);
                }
            }
            String word = currSeq.get(currSeq.size() - 1);
            // termination condition
            if (word.equals(targetWord)) {
                // first sequence where we reach end
                if (ans.size() == 0) {
                    ans.add(currSeq);
                } else if (ans.get(0).size() == currSeq.size()) {
                    ans.add(currSeq);
                }
            }
            for (int i = 0; i < word.length(); i++) {
                for (char letter = 'a'; letter <= 'z'; letter++) {
                    char[] replacedCharArray = word.toCharArray();
                    replacedCharArray[i] = letter;
                    String replacedWord = new String(replacedCharArray);
                    if (set.contains(replacedWord)) {
                        currSeq.add(replacedWord);
                        q.add(new ArrayList<>(currSeq));
                        // mark as visited on the level
                        usedOnLevel.add(replacedWord);
                        currSeq.remove(currSeq.size() - 1);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String startWord = "hit";
        String targetWord = "cog";
        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};
        WordLadder2 obj = new WordLadder2();
        ArrayList<ArrayList<String>> sequences = obj.findSequences(startWord, targetWord, wordList);
        System.out.println("Minimum length of sequence: " + sequences);
    }
}
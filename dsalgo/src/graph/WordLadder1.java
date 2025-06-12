package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/word-ladder/description/
public class WordLadder1 {
    public int wordLadderLength(String startWord, String targetWord,
                                String[] wordList) {
        // BFS
        Queue<PairWordLadder> q = new LinkedList<>();
        q.add(new PairWordLadder(startWord, 1));
        // for constant lookup, SC:O(N)
        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }
        set.remove(startWord);

        while (!q.isEmpty()) {
            String currWord = q.peek().word;
            int curSeqLen = q.peek().seqLen;
            q.remove();
            // termination condition
            if (currWord.equals(targetWord)) return curSeqLen;
            // TC: O(currWord.length * 26) * N{number of words in wordList,
            // as word will get inserted in the queue only when it is present
            // in wordlist}
            // currWord = "hat"
            for (int i = 0; i < currWord.length(); i++) {
                for (char letter = 'a'; letter <= 'z'; letter++) {
                    char[] replacedCharArray = currWord.toCharArray();
                    replacedCharArray[i] = letter;
                    String replacedWord = new String(replacedCharArray);
                    if (set.contains(replacedWord)) {
                        set.remove(replacedWord);
                        q.add(new PairWordLadder(replacedWord, curSeqLen + 1));
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String startWord = "hit";
        String targetWord = "cog";
        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};
        WordLadder1 obj = new WordLadder1();
        int wordLadderLength = obj.wordLadderLength(startWord, targetWord, wordList);
        System.out.println("Minimum length of sequence: " + wordLadderLength);
    }
}

class PairWordLadder {
    String word;
    int seqLen;

    public PairWordLadder(String word, int seqLen) {
        this.word = word;
        this.seqLen = seqLen;
    }
}

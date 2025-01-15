package hashing;

import java.util.Arrays;

public class Basic_CharacterCounter {

    public static void main(String[] args) {
        String str = "abcdabefc";
        char ch = 'b';
        System.out.println("frequency of character " + ch + " in string: " + countCharacterInString('b', str));
        countCharacterInStringOptimized(ch, str);

    }

    private static int countCharacterInString(char ch, String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    private static void countCharacterInStringOptimized(char ch, String str) {
        // assuming that characters are always lowercase
        int[] charFreq = new int[26];
        Arrays.fill(charFreq, 0);
        for (int i = 0; i < str.length(); i++) {
            charFreq[str.charAt(i) - 'a']++;
        }

        // finding the frequency of ch
        System.out.println("frequency of character " + ch + " in string: " + charFreq[ch - 'a']);

        // if characters are not mentioned that they are lowercase or uppercase
        int[] hash = new int[256];
        Arrays.fill(hash, 0);
        for (int i = 0; i < str.length(); i++) {
            hash[str.charAt(i)]++;
        }
        System.out.println("frequency of character " + ch + " in string: " + hash[ch]);
    }

}

package com.hbo.interview.bowling;

/**
 * Created by innasokolov on 7/19/16.
 */
public class Palindrome {
    private String word;

    public Palindrome(String word) {
        this.word = word;
    }

    public boolean isPalindrome() {
        int length = (word == null)?0:word.length();
        System.out.println(length);
        if (length < 3 || length % 2 == 0) return false;
        int middle = word.length() / 2;
        for (int i = 0; i < middle; i++) {
            if (word.charAt(i) != word.charAt(length - 1 - i)) return false;
        }
        return true;
    }

    public static void main(String[] args) throws  Exception {
        String word = "d";
        Palindrome polyndrom = new Palindrome(word);
        System.out.println(polyndrom.isPalindrome());
    }
}

package com.hbo.interview.bowling;

/**
 * Created by innasokolov on 7/19/16.
 */
public class NumberPalindrome {
    private Integer integer;

    public NumberPalindrome(Integer integer) {
        this.integer = integer;
    }

    public boolean isPalindrome() {
        int initial = this.integer;
        int reversed = 0;
        int dig;
        while (initial > 0)
        {
            dig = initial % 10;
            reversed = reversed * 10 + dig;
            initial = initial / 10;
        }
        return this.integer == reversed;
    }

    public static void main(String[] args) throws  Exception {
        Integer word = 12471;
        NumberPalindrome polyndrom = new NumberPalindrome(word);
        System.out.println(polyndrom.isPalindrome());
    }
}


//0011 0000 1000 0101
//                 1100 1111 0111 1010
//1111111111111111 1100 1111 0111 1010
//1010000101000101 1010 0001 0100 0101
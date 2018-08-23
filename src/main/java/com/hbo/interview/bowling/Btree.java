package com.hbo.interview.bowling;

/**
 * Created by innasokolov on 7/19/16.
 */
public class Btree {
    private Btree left;
    private Btree right;
    private int value;

    public static boolean isSymmetrical(Btree left, Btree right) {
        if (left == null || right == null) return left == null && right == null;
        return left.value == right.value && isSymmetrical(left.left, right.right) && isSymmetrical(left.right, right.left);
    }

    public Btree(int value, Btree left, Btree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public static void main(String[] args) throws  Exception {
        Btree one, two, three, four;
        four = new Btree(4, null, null);
        three = new Btree(3, null, null);
        two = new Btree(1, three, four);
        one = new Btree(1, three, four);
        System.out.println(Btree.isSymmetrical(one, two));
    }
}

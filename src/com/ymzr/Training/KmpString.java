package com.ymzr.Training;

public class KmpString {


    public static int kmp(String str, String target) {
        if (str.length() < target.length()) {
            return -1;
        }
        int[] next = getNext(target);
        char[] strs = str.toCharArray();
        char[] targets = target.toCharArray();
        int i = 0;
        int j = 0;
        while (i < strs.length && j < targets.length) {
            if (strs[i] == targets[j]) {
                i++;
                j++;
            } else if (next[j] >= 0) {
                j = next[j];
            } else {
                i++;
                j = 0;
            }
        }
        return j == target.length() && i <= strs.length ? i - j : -1;
    }

    public static int[] getNext(String target) {
        if (target.length() <= 2) {
            return new int[]{-1,0};
        }
        char[] chars = target.toCharArray();
        int[] next = new int[chars.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < chars.length) {
            if (chars[i - 1] == chars[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        getNext("aaaaaaaaa");
    }
}

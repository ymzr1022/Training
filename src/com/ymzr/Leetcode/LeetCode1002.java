package com.ymzr.Leetcode;

import java.util.*;

/**
 * 给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（ 包括重复字符），并以数组形式返回。你可以按 任意顺序 返回答案。
 * 
 *
 * 示例 1：
 *
 * 输入：words = ["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 *
 * 输入：words = ["cool","lock","cook"]
 * 输出：["c","o"]
 *
 */
public class LeetCode1002 {
    public static List<String> commonChars(String[] words) {
        int[] hash = new int[26];
        List<String> res = new ArrayList<>();
        Arrays.fill(hash,Integer.MAX_VALUE);
        for (String word: words) {
            int[] counts = new int[26];
            for (int i = 0 ; i < word.length() ; i++) {
                int chr = word.charAt(i) - 'a';
                ++counts[chr];
            }

            for (int i = 0 ; i < 26 ; i++ ) {
                hash[i] = Math.min(hash[i], counts[i]);
            }
        }
        for (int i = 0 ; i < 26 ; i++ ) {
            while (hash[i]-- > 0) {
                char ch = (char) (i + 'a');
                res.add(String.valueOf(ch));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"cool","lock","cook"};
        System.out.println(commonChars(words).toString());
    }
}

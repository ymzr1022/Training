package com.ymzr.Leetcode.Trie;


import com.ymzr.Training.TrieTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个下标从 0 开始的正整数数组 nums 。请你找出并统计满足下述条件的三元组 (i, j, k) 的数目：
 *
 * 0 <= i < j < k < nums.length
 * nums[i]、nums[j] 和 nums[k] 两两不同 。
 * 换句话说：nums[i] != nums[j]、nums[i] != nums[k] 且 nums[j] != nums[k] 。
 * 返回满足上述条件三元组的数目。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：nums = [4,4,2,4,3]
 * 输出：3
 * 解释：下面列出的三元组均满足题目条件：
 * - (0, 2, 4) 因为 4 != 2 != 3
 * - (1, 2, 4) 因为 4 != 2 != 3
 * - (2, 3, 4) 因为 2 != 4 != 3
 * 共计 3 个三元组，返回 3 。
 * 注意 (2, 0, 4) 不是有效的三元组，因为 2 > 0 。
 * 示例 2：
 *
 * 输入：nums = [1,1,1,1,1]
 * 输出：0
 * 解释：不存在满足条件的三元组，所以返回 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-unequal-triplets-in-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode648 {

    public static class TrieNode {
        int pass;
        int end;
        TrieNode[] nexts;
        public TrieNode() {
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    public static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            if (word == null) {
                root.pass++;
                root.end++;
                return;
            }
            char[] chars = word.toCharArray();
            int index;
            TrieNode node = root;
            node.pass++;
            for (int i = 0 ; i < chars.length ; i++ ) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        public int search(String word) {
            if (word == null) {
                return root.end;
            }
            char[] chars = word.toCharArray();
            int index;
            TrieNode node = root;
            for (int i = 0 ; i < chars.length ; i++ ) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        public int prefix(String word) {
            if (word == null) {
                return root.pass;
            }
            char[] chars = word.toCharArray();
            int index;
            TrieNode node = root;
            for (int i = 0 ; i < chars.length ; i++ ) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }


        public void delete(String word) {
            if (search(word) > 0) {
                if (word == null) {
                    root.pass--;
                    root.end--;
                    return;
                }
                char[] chars = word.toCharArray();
                int index;
                TrieNode node = root;
                root.pass--;
                for (int i = 0 ; i < chars.length ; i++ ) {
                    index = chars[i] - 'a';
                    if (--node.nexts[index].pass == 0) {
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        public String hasPrefix(String word) {
            if (word == null) {
                return "";
            }
            char[] chars = word.toCharArray();
            int index;
            TrieNode node = root;
            StringBuffer sb = new StringBuffer();
            for (int i = 0 ; i < chars.length ; i++ ) {
                index = chars[i] - 'a';
                if (node.end != 0) {
                    return sb.toString();
                }
                if (node.nexts[index] == null) {
                    return "";
                }
                node = node.nexts[index];
                sb.append(chars[i]);
            }
            return sb.toString();
        }
    }

    public static String replaceWords(List<String> dictionary, String sentence) {
        String[] strings = sentence.split(" ");
        Trie trie = new Trie();
        StringBuffer sb = new StringBuffer();
        dictionary.forEach(e -> trie.insert(e));
        String s;
        for (String str: strings
             ) {
            s = trie.hasPrefix(str);
            sb.append("".equals(s) ? str : s).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Trie tire = new Trie();
        String w = "sss";
        tire.insert(w);
        tire.insert("abc");
        tire.insert("ab");
        tire.insert("ab");
        tire.insert("abc");
        System.out.println(tire.prefix("ab"));
        tire.delete("ab");
        System.out.println(tire.prefix("ab"));
        System.out.println(tire.search("ab"));
        tire.delete("abc");
        System.out.println(tire.prefix("ab"));
        System.out.println(tire.hasPrefix("abababab"));
        List<String> list = new ArrayList<>(Arrays.asList(new String[]{"catt", "cat","bat", "rat"}));
        String dd = "the cattle was rattled by the battery";
        System.out.println(replaceWords(list, dd));
    }
}

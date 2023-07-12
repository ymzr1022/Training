package com.ymzr.Leetcode.Trie;


import java.util.HashMap;

/**
 * 设计一个 map ，满足以下几点:
 *
 * 字符串表示键，整数表示值
 * 返回具有前缀等于给定字符串的键的值的总和
 * 实现一个 MapSum 类：
 *
 * MapSum() 初始化 MapSum 对象
 * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对key-value将被替代成新的键值对。
 * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 * 
 *
 * 示例 1：
 *
 * 输入：
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * 输出：
 * [null, null, 3, null, 5]
 *
 * 解释：
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);  
 * mapSum.sum("ap");           // 返回 3 (apple = 3)
 * mapSum.insert("app", 2);    
 * mapSum.sum("ap");           // 返回 5 (apple + app = 3 + 2 = 5)
 *
 */
public class LeetCode677 {

    public static class TrieNode {
        int pass;
        int end;
        HashMap<Character, TrieNode> nexts;
        public TrieNode() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class MapSum {
        TrieNode root;

        public MapSum() {
            root = new TrieNode();
        }

        public void insert(String key, int val) {
            val = val - check(key);
            if (key == null) {
                root.pass += val;
                root.end += val;
                return;
            }
            char[] chars = key.toCharArray();
            TrieNode node = root;
            node.pass += val;
            for (int i = 0; i < chars.length; i++) {
                if (node.nexts.get(chars[i]) == null) {
                    node.nexts.put(chars[i],new TrieNode());
                }
                node = node.nexts.get(chars[i]);
                node.pass += val;
            }
            node.end += val;
        }

        public int sum(String prefix) {
            if (prefix == null) {
                return root.pass;
            }
            char[] chars = prefix.toCharArray();
            TrieNode node = root;

            for (int i = 0; i < chars.length; i++) {
                if (node.nexts.get(chars[i]) == null) {
                    return 0;
                }
                node = node.nexts.get(chars[i]);
            }
            return node.pass;
        }

        public int check(String prefix) {
            if (prefix == null) {
                return root.pass;
            }
            char[] chars = prefix.toCharArray();
            TrieNode node = root;

            for (int i = 0; i < chars.length; i++) {
                if (node.nexts.get(chars[i]) == null) {
                    return 0;
                }
                node = node.nexts.get(chars[i]);
            }
            return node.end;
        }

    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("a"));
        mapSum.insert("apple", 2);
        System.out.println(mapSum.sum("ap"));
    }
}

package com.ymzr.Leetcode.Trie;


/**
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 *
 * 实现词典类 WordDictionary ：
 *
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与word 匹配，则返回 true ；否则，返回 false 。word 中可能包含一些 '.' ，每个. 都可以表示任何一个字母。
 * 
 *
 * 示例：
 *
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 *
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // 返回 False
 * wordDictionary.search("bad"); // 返回 True
 * wordDictionary.search(".ad"); // 返回 True
 * wordDictionary.search("b.."); // 返回 True
 *
 */
public class LeetCode211 {
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

    public static class WordDictionary {
        TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            if (word == null) {
                root.pass++;
                root.end++;
                return;
            }
            char[] chars = word.toCharArray();
            int index = 0;
            TrieNode node = root;
            node.pass++;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        public boolean search(String word) {
            return dfs(word, 0 ,root);
        }

        public boolean dfs(String words, int index, TrieNode node) {
            int n = words.length();
            if ( n == index) {
                return node.end > 0 ? true : false;
            }
            char c = words.charAt(index);
            if (c == '.') {
                for (int i = 0; i < 26; i++) {
                    if (node.nexts[i] != null && dfs(words,index + 1, node.nexts[i])) {
                        return true;
                    }
                }
                return false;
            } else {
                int x = c - 'a';
                if (node.nexts[x] == null) {
                    return false;
                }
                return dfs(words, index + 1, node.nexts[x]);
            }
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search("ba"));
        System.out.println(wordDictionary.search("b.."));
        System.out.println(wordDictionary.search(".a"));
    }
}

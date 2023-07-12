package com.ymzr.Training;

public class TrieTree {

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

    public static class Tire{
        TrieNode root;

        public Tire(){
            root = new TrieNode();
        }

        public void insert(String word) {
            if (word == null) {
                root.pass++;
                root.end++;
                return;
            }
            TrieNode node = root;
            char[] chars = word.toCharArray();
            int index;
            for (int i = 0 ; i < chars.length ; i++ ) {
                index = chars[i] - 'a';
                    node.pass++;
                if (node.nexts[index] == null) {
                    TrieNode node1 = new TrieNode();
                    node.nexts[index] = node1;
                }
                node = node.nexts[index];
            }
            node.pass++;
            node.end++;
        }

        public int search(String word) {
            if (word == null) {
                return root.end;
            }

            TrieNode node = root;
            char[] chars = word.toCharArray();
            int index;
            for (int i = 0 ; i < chars.length ; i++ ) {
                index = chars[i] - 'a';
                if (node.nexts[index] != null) {
                    node = node.nexts[index];
                } else {
                    return 0;
                }
            }
            return node.end;
        }

        public int prefix(String word) {
            if (word == null) {
                return root.pass;
            }
            TrieNode node = root;
            char[] chars = word.toCharArray();
            int index;
            for (int i = 0 ; i < chars.length ; i++ ) {
                index = chars[i] - 'a';
                if (node.nexts[index] != null) {
                    node = node.nexts[index];
                } else {
                    return 0;
                }
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
                TrieNode node = root;
                char[] chars = word.toCharArray();
                int index;
                for (int i = 0 ; i < chars.length ; i++ ) {
                    if (--node.pass == 0) {
                        node = null;
                    }
                    index = chars[i] - 'a';
                    node = node.nexts[index];
                }
                node.pass--;
                node.end--;
            }
        }
    }


    public static void main(String[] args) {
        Tire tire = new Tire();
        String w = "sss";
        tire.insert(w);
        tire.insert("abc");
        tire.insert("ab");
        tire.insert("ab");

        System.out.println(tire.prefix("ab"));
        tire.delete("ab");
        System.out.println(tire.prefix("ab"));
        System.out.println(tire.search("ab"));
        tire.delete("abc");
        System.out.println(tire.prefix("ab"));
    }
}

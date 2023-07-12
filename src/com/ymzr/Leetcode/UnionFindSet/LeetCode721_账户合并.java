package com.ymzr.Leetcode.UnionFindSet;

import java.util.*;

/**
 * 给定一个列表 accounts，每个元素 accounts[i]是一个字符串列表，其中第一个元素 accounts[i][0]是名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 *
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 *
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是 按字符 ASCII 顺序排列 的邮箱地址。账户本身可以以 任意顺序 返回。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * 输出：[["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 * 解释：
 * 第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。 
 * 第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
 * 可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 * ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。
 * 示例 2：
 *
 * 输入：accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 * 输出：[["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 * 
 *
 * 提示：
 *
 * 1 <= accounts.length <= 1000
 * 2 <= accounts[i].length <= 10
 * 1 <= accounts[i][j].length <= 30
 * accounts[i][0] 由英文字母组成
 * accounts[i][j] (for j > 0) 是有效的邮箱地址
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/accounts-merge
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode721_账户合并 {

    public  List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, Integer> mailIndex = new HashMap<>();
        HashMap<String, String> mailName = new HashMap<>();
        int count = 0;
        for (List<String> lists: accounts
             ) {
            String name = lists.get(0);
            int n = lists.size();
            for (int i = 1; i < n; i++) {
                String email = lists.get(i);
                mailIndex.put(email, count++);
                mailName.put(email, name);
            }
        }

        p = new int[count];
        for (int i = 0; i < count; i++) {
            p[i] = i;
        }
        for (List<String> lists: accounts
        ) {
            String firstEmail = lists.get(1);
            int index = mailIndex.get(firstEmail);
            int n = lists.size();
            for (int i = 2; i < n; i++) {
                String nextEmail = lists.get(i);
                int nextIndex = mailIndex.get(nextEmail);
                union(index, nextIndex);
            }
        }
        Map<Integer, List<String>> map = new HashMap<>();
        for (String email: mailIndex.keySet()
             ) {
            int index = find(mailIndex.get(email));
            List<String> list = map.getOrDefault(index, new ArrayList<>());
            list.add(email);
            map.put(index, list);
        }

        List<List<String>> res = new ArrayList<>();

        for (List<String> emails: map.values()) {
            Collections.sort(emails);

            String name = mailName.get(emails.get(0));

            List<String> list = new ArrayList<>();

            list.add(name);
            list.addAll(emails);
            res.add(list);
        }
        return res;

    }



    public  int[] p;

    public  int find(int x) {
        if(x != p[x]) p[x] = find(p[x]);
        return p[x];
    }

    public  void union(int a, int b) {
        p[find(a)] = p[find(b)];
    }

    public  boolean query(int a, int b) {
        return         p[find(a)] == p[find(b)];
    }

    public  void main(String[] args) {
        List<String> s1 = new ArrayList<>();
        s1.add("David");
        s1.add("David0@m.co");
        s1.add("David1@m.co");
        List<String> s2 = new ArrayList<>();
        s2.add("David");
        s2.add("David3@m.co");
        s2.add("David4@m.co");
        List<String> s3 = new ArrayList<>();
        s3.add("David");
        s3.add("David4@m.co");
        s3.add("David5@m.co");
        List<String> s4 = new ArrayList<>();
        s4.add("David");
        s4.add("David2@m.co");
        s4.add("David3@m.co");
        List<String> s5 = new ArrayList<>();
        s5.add("David");
        s5.add("David1@m.co");
        s5.add("David2@m.co");
        List<List<String>> ac = new ArrayList<>();
        ac.add(s1);
        ac.add(s2);
        ac.add(s3);
        ac.add(s4);
        ac.add(s5);
        accountsMerge(ac);
    }
    /**
     * [["Alex","Alex5@m.co","Alex4@m.co","Alex0@m.co"],["Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"],["Kevin","Kevin4@m.co","Kevin2@m.co","Kevin2@m.co"],["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"],["Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co"]]
     */
}

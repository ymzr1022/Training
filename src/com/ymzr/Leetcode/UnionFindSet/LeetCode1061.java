package com.ymzr.Leetcode.UnionFindSet;

/**
 * 给出长度相同的两个字符串s1 和s2，还有一个字符串baseStr。
 *
 * 其中 s1[i]和s2[i] 是一组等价字符。
 *
 * 举个例子，如果s1 = "abc" 且s2 = "cde"，那么就有'a' == 'c', 'b' == 'd', 'c' == 'e'。
 * 等价字符遵循任何等价关系的一般规则：
 *
 * 自反性：'a' == 'a'
 * 对称性：'a' == 'b' 则必定有 'b' == 'a'
 * 传递性 ：'a' == 'b' 且 'b' == 'c' 就表明 'a' == 'c'
 * 例如，s1 = "abc"和s2 = "cde"的等价信息和之前的例子一样，那么baseStr = "eed", "acd"或"aab"，这三个字符串都是等价的，而"aab"是baseStr的按字典序最小的等价字符串
 *
 * 利用s1和s2的等价信息，找出并返回baseStr的按字典序排列最小的等价字符串。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：s1 = "parker", s2 = "morris", baseStr = "parser"
 * 输出："makkek"
 * 解释：根据 A 和 B 中的等价信息，我们可以将这些字符分为 [m,p], [a,o], [k,r,s], [e,i] 共 4 组。每组中的字符都是等价的，并按字典序排列。所以答案是 "makkek"。
 * 示例 2：
 *
 * 输入：s1 = "hello", s2 = "world", baseStr = "hold"
 * 输出："hdld"
 * 解释：根据 A 和 B 中的等价信息，我们可以将这些字符分为 [h,w], [d,e,o], [l,r] 共 3 组。所以只有 S 中的第二个字符 'o' 变成 'd'，最后答案为 "hdld"。
 * 示例 3：
 *
 * 输入：s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
 * 输出："aauaaaaada"
 * 解释：我们可以把 A 和 B 中的等价字符分为 [a,o,e,r,s,c], [l,p], [g,t] 和 [d,m] 共 4 组，因此 S 中除了 'u' 和 'd' 之外的所有字母都转化成了 'a'，最后答案为 "aauaaaaada"。
 *
 *
 *
 * 提示：
 *
 * 1 <= s1.length, s2.length, baseStr <= 1000
 * s1.length == s2.length
 * 字符串s1,s2, andbaseStr仅由从'a' 到'z'的小写英文字母组成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/lexicographically-smallest-equivalent-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1061 {

    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        int n = s1.length();
        for (int i = 0; i < 26; i++) {
            p[i] = i;
        }

        for (int i = 0; i < n; i++) {
           union(s2.charAt(i) - 'a',s1.charAt(i) - 'a');
        }

        char[] chars = baseStr.toCharArray();
        char[] tmp = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            tmp[i] = (char) (find(index) + 'a');
        }
        return String.valueOf(tmp);
    }

    public static int[] p = new int[26];

    public static int find(int a) {
        if (a != p[a]) p[a] = find(p[a]);
        return p[a];
    }

    public static void union(int a, int b) {
        if (find(a) > find(b)) p[find(a)] = p[find(b)];
        else p[find(b)] = p[find(a)];
    }

    public static boolean query(int a, int b) {
        return p[find(a)] == p[find(b)];
    }

    public static void main(String[] args) {
        String  s1 = "dfeffdfafbbebbebacbbdfcfdbcacdcbeeffdfebbdebbdafff",
                s2 = "adcdfabadbeeafeabbadcefcaabdecabfecffbabbfcdfcaaae",
                baseStr = "myickvflcpfyqievitqtwvfpsrxigauvlqdtqhpfugguwfcpqv";

        System.out.println(smallestEquivalentString(s1,s2,baseStr));;
    }
}

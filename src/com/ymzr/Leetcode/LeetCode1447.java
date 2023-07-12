package com.ymzr.Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *     int gcd(int a, int b) { // 欧几里得算法
 *         return b == 0 ? a : gcd(b, a % b);
 *     }
 *
 示例 1：

 输入：n = 2
 输出：["1/2"]
 解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
 示例 2：

 输入：n = 3
 输出：["1/2","1/3","2/3"]
 示例 3：

 输入：n = 4
 输出：["1/2","1/3","1/4","2/3","3/4"]
 解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
 示例 4：

 输入：n = 1
 输出：[]
 */
public class LeetCode1447 {
    int gcd1(int a, int b) { // 欧几里得算法
        return b == 0 ? a : gcd1(b, a % b);
    }


    int gcd2(int a, int b) { // 更相减损法
        while (true) {
            if (a > b) a -= b;
            else if (a < b) b -= a;
            else return a;
        }
    }

    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (gcd1(i, j) == 1) ans.add(i + "/" + j);
            }
        }
        return ans;
    }

}

package com.ymzr.Leetcode;

/**
 * 你是一位施工队的工长，根据设计师的要求准备为一套设计风格独特的房子进行室内装修。
 *
 * 房子的客厅大小为nx m，为保持极简的风格，需要使用尽可能少的 正方形 瓷砖来铺盖地面。
 *
 * 假设正方形瓷砖的规格不限，边长都是整数。
 *
 * 请你帮设计师计算一下，最少需要用到多少块方形瓷砖？
 *
 * 
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 2, m = 3
 * 输出：3
 * 解释：3 块地砖就可以铺满卧室。
 *      2 块 1x1 地砖
 *      1 块 2x2 地砖
 * 示例 2：
 *
 *
 *
 * 输入：n = 5, m = 8
 * 输出：5
 * 示例 3：
 *
 *
 *
 * 输入：n = 11, m = 13
 * 输出：6
 * 
 *
 * 提示：
 *
 * 1 <= n <= 13
 * 1 <= m<=13
 *
 */
public class LeetCode1240 {
    public static int ans = 0;

    public static int tilingRectangle(int n, int m) {

        boolean[][] res = new boolean[n][m];


        return 0;
    }

    public static void cover(int m, int n, int k, boolean[][] res) {
        for (int i = 0 ; i < n; i ++) {
            for (int j = 0 ; j < n ; j++ ) {
                if (res[i][j]) {
                    return;
                }
            }
        }
    }

    public static void process(int x, int y, boolean[][] vis, int n, int m,  int s, int count) {

        if (s == n * m) {
            ans = count;
            return;
        }

        if (count > ans) {
            return;
        }

        for (int k = Math.min(n - x, m - y) ; k > 0 ; k--) {
            process(x + k, y + k, vis, n ,m, (k * k) + s, count++);
        }

    }
}

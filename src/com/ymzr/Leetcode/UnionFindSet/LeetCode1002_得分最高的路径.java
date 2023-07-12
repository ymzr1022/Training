package com.ymzr.Leetcode.UnionFindSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 给定一个 m x n 的整数矩阵grid，返回从 (0,0) 开始到 (m - 1, n - 1) 在四个基本方向上移动的路径的最大 分数 。
 *
 * 一条路径的 分数 是该路径上的最小值。
 *
 * 例如，路径 8 → 4 → 5 → 9 的得分为 4 。
 * 
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[5,4,5},{1,2,6},{7,4,6]]
 * 输出：4
 * 解释：得分最高的路径用黄色突出显示。 
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[2,2,1,2,2,2},{1,2,2,2,1,2]]
 * 输出：2
 * 示例 3：
 *
 *
 *
 * 输入：grid = [[3,4,6,3,4},{0,2,1,1,7},{8,8,3,2,7},{3,2,4,9,8},{4,1,2,0,0},{4,6,5,4,3]]
 * 输出：3
 * 
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * 0 <= grid[i][j] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/path-with-maximum-minimum-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1002_得分最高的路径 {

    public static int maximumMinimumPath(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        p = new int[row * col];
        for (int i = 0; i < row * col; i++) {
            p[i] = i;
        }
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int index1 = getIndex(i, j);
                int index2;
                if (j + 1 < col) {
                    index2 = getIndex(i, j + 1);
                    list.add(new int[]{index1, index2, Math.min(grid[i][j], grid[i][j + 1])});
                }

                if (i + 1 < row) {
                    index2 = getIndex(i + 1, j);
                    list.add(new int[]{index1, index2, Math.min(grid[i][j], grid[i + 1][j])});
                }
            }
        }

        Collections.sort(list, (o1, o2) -> o2[2] - o1[2]);

        for (int[] nums: list
             ) {
            int a = nums[0];
            int b = nums[1];
            int cost = nums[2];
            union(a, b);
            if (query(0, row * col - 1)) {
                return cost;
            }
        }
        return 0;
    }

    public static int[] p;
    public static int row;
    public static int col;

    public static int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }

    public static void union(int a, int b) {
        p[find(a)] = p[find(b)];
    }

    public static boolean query(int a, int b) {
        return p[find(a)] == p[find(b)];
    }

    public static int getIndex(int a, int b) {
        return a * col + b;
    }

    public static void main(String[] args) {
        int[][] s = {{3,4,6,3,4},{0,2,1,1,7},{8,8,3,2,7},{3,2,4,9,8},{4,1,2,0,0},{4,6,5,4,3}};
        maximumMinimumPath(s);
    }
}

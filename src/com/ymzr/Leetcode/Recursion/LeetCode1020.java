package com.ymzr.Leetcode.Recursion;

/**
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 *
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 *
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 *
 * 
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0]]
 * 输出：3
 * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 * 示例 2：
 *
 *
 * 输入：grid = [[0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0]]
 * 输出：0
 * 解释：所有 1 都在边界上或可以到达边界。
 * 
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid[i][j] 的值为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-enclaves
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1020 {

    public static int numEnclaves(int[][] grid) {
        if (grid == null || grid.length == 0 ) {
            return 0;
        }
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[0][j] == 1) {
                process(grid, 0, j);
            }
            if (grid[grid.length - 1][j] == 1) {
                process(grid, grid.length - 1, j);
            }
        }
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[i][0] == 1) {
                process(grid, i, 0);
            }
            if (grid[i][grid[0].length - 1] == 1) {
                process(grid, i, grid[0].length - 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void process(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 2;

        process(grid, i + 1, j);
        process(grid, i - 1, j);
        process(grid, i, j + 1);
        process(grid, i, j - 1);
    }

    public static void main(String[] args) {
        int[][] grid = {{0,0,0,1,1,1,0,1,0,0},
                        {1,1,0,0,0,1,0,1,1,1},
                        {0,0,0,1,1,1,0,1,0,0},
                        {0,1,1,0,0,0,1,0,1,0},
                        {0,1,1,1,1,1,0,0,1,0},
                        {0,0,1,0,1,1,1,1,0,1},
                        {0,1,1,0,0,0,1,1,1,1},
                        {0,0,1,0,0,1,0,1,0,1},
                        {1,0,1,0,1,1,0,0,0,0},
                        {0,0,0,0,1,1,0,0,0,1}};
        System.out.println(numEnclaves(grid));
    }
}

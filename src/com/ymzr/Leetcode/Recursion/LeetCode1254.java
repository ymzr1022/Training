package com.ymzr.Leetcode.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 二维矩阵 grid由 0（土地）和 1（水）组成。岛是由最大的4个方向连通的 0组成的群，封闭岛是一个完全 由1包围（左、上、右、下）的岛。
 *
 * 请返回 封闭岛屿 的数目。
 *
 * 
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * 输出：2
 * 解释：
 * 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * 输出：1
 * 示例 3：
 *
 * 输入：grid = [[1,1,1,1,1,1,1],
 *             [1,0,0,0,0,0,1],
 *             [1,0,1,1,1,0,1],
 *             [1,0,1,0,1,0,1],
 *             [1,0,1,1,1,0,1],
 *             [1,0,0,0,0,0,1],
 *             [1,1,1,1,1,1,1]]
 * 输出：2
 *
 */
public class LeetCode1254 {

    public static int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {

                   if (process(grid, i, j))
                   ans++;
                }
            }
        }
        return ans;
    }

    public static boolean process(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }
        if (grid[i][j] == 1 || grid[i][j] == 2) {
            return true;
        }
        grid[i][j] = 2;
        return process(grid, i + 1, j) &
        process(grid, i - 1, j) &
        process(grid, i, j + 1) &
        process(grid, i, j - 1);
    }

    public static void main(String[] args) {
        int[][] grid = {{0,0,1,1,0,1,0,0,1,0},
                        {1,1,0,1,1,0,1,1,1,0},
                        {1,0,1,1,1,0,0,1,1,0},
                        {0,1,1,0,0,0,0,1,0,1},
                        {0,0,0,0,0,0,1,1,1,0},
                        {0,1,0,1,0,1,0,1,1,1},
                        {1,0,1,0,1,1,0,0,0,1},
                        {1,1,1,1,1,1,0,0,0,0},
                        {1,1,1,0,0,1,0,1,0,1},
                        {1,1,1,0,1,1,0,1,1,0}};
        System.out.println(closedIsland(grid));
    }

}

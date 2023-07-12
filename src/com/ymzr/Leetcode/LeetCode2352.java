package com.ymzr.Leetcode;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.*;

/**
 * 给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
 *
 * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[3,2,1],[1,7,6],[2,7,7]]
 * 输出：1
 * 解释：存在一对相等行列对：
 * - (第 2 行，第 1 列)：[2,7,7]
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 * 输出：3
 * 解释：存在三对相等行列对：
 * - (第 0 行，第 0 列)：[3,1,2,2]
 * - (第 2 行, 第 2 列)：[2,4,2,2]
 * - (第 3 行, 第 2 列)：[2,4,2,2]
 *
 *
 */
public class LeetCode2352 {
    public static int equalPairs(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        HashMap<String,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++ ) {
            String gridStr = Arrays.toString(grid[i]);
            map.put(gridStr, map.getOrDefault(gridStr, 0) + 1);
        }
        int res = 0;
        int[][] rGrid = reverseGrid(grid);
        for (int i = 0 ; i < n ; i++) {
            String arr = Arrays.toString(rGrid[i]);
            if (map.containsKey(arr)) {
                res += map.get(arr);
            }
        }
        return res;
    }


    public static int[][] reverseGrid(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return null;
        }
        int n = grid.length;
        int[][] rGrid = new int[n][n];
        int row = 0;
        for (int i = n - 1; i >= 0; i-- ) {
            for (int j = 0; j < n; j++ ) {
                rGrid[row][j] = grid[j][i];
            }
            row++;
        }
        return rGrid;
    }
    public static void main(String[] args) {
        int[][] grid = new int[][]{{12,12,12}, {12,12,12}, {12,12,12}};
        System.out.println(equalPairs(grid));
    }
}

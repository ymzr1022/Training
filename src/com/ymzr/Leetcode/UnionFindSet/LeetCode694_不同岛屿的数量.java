package com.ymzr.Leetcode.UnionFindSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 给定一个非空 01 二维数组表示的网格，一个岛屿由四连通（上、下、左、右四个方向）的 1 组成，你可以认为网格的四周被海水包围。
 *
 * 请你计算这个网格中共有多少个形状不同的岛屿。两个岛屿被认为是相同的，当且仅当一个岛屿可以通过平移变换（不可以旋转、翻转）和另一个岛屿重合。
 *
 * 
 *
 * 示例 1：
 *
 *
 *
 * 输入: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
 * 输出：1
 * 示例 2：
 *
 * 输入: grid = [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
 * 输出: 3
 *
 *
 * 
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j]仅包含0或1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-distinct-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode694_不同岛屿的数量 {
    public static int col;
    public  static int numDistinctIslands(int[][] grid) {

        col = grid[0].length;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    process(grid, i, j,sb, "S");
                    set.add(sb.toString());
                }
            }
        }





        return set.size();
    }

    public static void process(int[][] grid, int i, int j, StringBuilder sb, String to) {
        if (i >= grid.length || j >= grid[0].length || i < 0 || j < 0 || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 2;
        sb.append(to).append(".");
        process(grid, i + 1, j, sb, "1");
        process(grid, i - 1, j, sb, "2");
        process(grid, i, j + 1, sb, "3");
        process(grid, i, j - 1, sb, "4");
        sb.append("-" + to).append(".");

    }

    public static   boolean processCheck(ArrayList<int[]> arrayList1, ArrayList<int[]> arrayList2) {
        int xi = arrayList1.get(0)[0] - arrayList2.get(0)[0];
        int xj = arrayList1.get(0)[1] - arrayList2.get(0)[1];
        for (int i = 1; i < arrayList1.size(); i++) {
            if (arrayList1.get(i)[0] - arrayList2.get(i)[0] != xi || arrayList1.get(i)[1] - arrayList2.get(i)[1] != xj) {
                return false;
            }
        }
        return true;
    }

    public static   void main(String[] args) {
        int[][] grid = {{0,1,1},{1,1,1},{0,0,0},{1,1,1},{1,1,0}};
        numDistinctIslands(grid);
    }
}

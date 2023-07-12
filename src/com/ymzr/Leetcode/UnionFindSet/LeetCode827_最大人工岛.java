package com.ymzr.Leetcode.UnionFindSet;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
 *
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 *
 * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: grid = [[1, 0], [0, 1}}
 * 输出: 3
 * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
 * 示例 2:
 *
 * 输入: grid = [[1, 1], [1, 0}}
 * 输出: 4
 * 解释: 将一格0变成1，岛屿的面积扩大为 4。
 * 示例 3:
 *
 * 输入: grid = [[1, 1], [1, 1}}
 * 输出: 4
 * 解释: 没有0可以让我们变成1，面积依然为 4。
 *  
 *
 * 提示：
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] 为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/making-a-large-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode827_最大人工岛 {

    public static int largestIsland(int[][] grid) {
        int ans = 0;
        int nums = 2;
        col = grid[0].length;
        p = new int[col * grid.length];
        HashMap<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                p[getIndex(i, j)] = getIndex(i, j);
            }
        }
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    ans = process(grid, i, j, nums, i, j);
                    max = Math.max(max, ans);
                    numMap.put(nums++, ans);
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    int num = 1;
                    HashSet<Integer> set = new HashSet<>();
                    if (i + 1 < grid.length && grid[i + 1][j] > 1 && !query(getIndex(i,j), getIndex(i + 1, j)) && !set.contains(find(getIndex(i + 1, j)))) {
                        num += numMap.get(grid[i + 1][j]);
                        int tmp = find(getIndex(i + 1, j));
                        set.add(tmp);
                    }
                    if (j + 1 < grid.length && grid[i][j + 1] > 1 && !query(getIndex(i,j), getIndex(i, j + 1)) && !set.contains(find(getIndex(i, j + 1)))) {
                        num += numMap.get(grid[i][j + 1]);
                        int tmp = find(getIndex(i, j + 1));
                        set.add(tmp);
                    }
                    if (i - 1 >= 0 && grid[i - 1][j] > 1 && !query(getIndex(i,j), getIndex(i - 1, j)) && !set.contains(find(getIndex(i - 1, j)))) {
                        num += numMap.get(grid[i - 1][j]);
                        int tmp = find(getIndex(i - 1, j));
                        set.add(tmp);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] > 1 && !query(getIndex(i,j), getIndex(i, j - 1)) && !set.contains(find(getIndex(i, j - 1)))) {
                        num += numMap.get(grid[i][j - 1]);
                        int tmp = find(getIndex(i, j - 1));
                        set.add(tmp);
                    }
                    max = Math.max(num, max);
                }
            }
        }
        return max;
    }

    public static int process(int[][] grid, int i, int j,int nums, int startI, int startJ) {
        if (i >= grid.length || j >= grid[0].length || j < 0 || i < 0 || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = nums;
        union(getIndex(i, j), getIndex(startI, startJ));
        int ans = 1;
        ans += process(grid, i + 1, j, nums, startI, startJ);
        ans += process(grid, i - 1, j, nums, startI, startJ);
        ans += process(grid, i, j + 1, nums, startI, startJ);
        ans += process(grid, i, j - 1, nums, startI, startJ);
        return ans;
    }



    public static int[] p;
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
        int[][] grid = {{0,0,0,0,0,0,0},{0,1,1,1,1,0,0},{0,1,0,0,1,0,0},{1,0,1,0,1,0,0},{0,1,0,0,1,0,0},{0,1,0,0,1,0,0},{0,1,1,1,1,0,0}};
        System.out.println(largestIsland(grid));
    }
}

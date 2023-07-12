package com.ymzr.Leetcode.UnionFindSet;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个大小为 m x n 的二进制网格 grid 。网格表示一个地图，其中，0 表示水，1 表示陆地。最初，grid 中的所有单元格都是水单元格（即，所有单元格都是 0）。
 *
 * 可以通过执行 addLand 操作，将某个位置的水转换成陆地。给你一个数组 positions ，其中 positions[i] = [ri, ci] 是要执行第 i 次操作的位置 (ri, ci) 。
 *
 * 返回一个整数数组 answer ，其中 answer[i] 是将单元格 (ri, ci) 转换为陆地后，地图中岛屿的数量。
 *
 * 岛屿 的定义是被「水」包围的「陆地」，通过水平方向或者垂直方向上相邻的陆地连接而成。你可以假设地图网格的四边均被无边无际的「水」所包围。
 *
 * 
 * 示例 1：
 *
 *
 * 输入：m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
 * 输出：[1,1,2,3]
 * 解释：
 * 起初，二维网格grid被全部注入「水」。（0 代表「水」，1 代表「陆地」）
 * - 操作#1：addLand(0, 0) 将grid[0][0] 的水变为陆地。此时存在 1 个岛屿。
 * - 操作#2：addLand(0, 1) 将grid[0][1] 的水变为陆地。此时存在 1 个岛屿。
 * - 操作#3：addLand(1, 2) 将grid[1][2] 的水变为陆地。此时存在 2 个岛屿。
 * - 操作#4：addLand(2, 1) 将grid[2][1] 的水变为陆地。此时存在 3 个岛屿。
 * 示例 2：
 *
 * 输入：m = 1, n = 1, positions = [[0,0]]
 * 输出：[1]
 * 
 *
 * 提示：
 *
 * 1 <= m, n, positions.length <= 104
 * 1 <= m * n <= 104
 * positions[i].length == 2
 * 0 <= ri < m
 * 0 <= ci < n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-islands-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode305_岛屿数量II {

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        col = n;
        row = m;
        p = new int[n * m];
        for (int i = 0; i < m * n; i++) {
            p[i] = i;
        }
        int[][] gird = new int[m][n];
        int counts = 0;
        for (int[] position : positions
             ) {
            int j = position[1];
            int i = position[0];
            if (gird[i][j] == 0) {
                gird[i][j] = 1;
                counts++;
                if (i + 1 < m && gird[i + 1][j] == 1) {
                    int a = getIndex(i, j);
                    int b = getIndex(i + 1, j);
                    if (!query(a, b)) {
                        union(a, b);
                        counts--;
                    }
                }
                if (i - 1 >= 0 && gird[i - 1][j] == 1) {
                    int a = getIndex(i, j);
                    int b = getIndex(i - 1, j);
                    if (!query(a, b)) {
                        union(a, b);
                        counts--;
                    }

                }
                if (j + 1 < n && gird[i][j + 1] == 1) {
                    int a = getIndex(i, j);
                    int b = getIndex(i, j + 1);
                    if (!query(a, b)) {
                        union(a, b);
                        counts--;
                    }
                }
                if (j - 1 >= 0 && gird[i][j - 1] == 1) {
                    int a = getIndex(i, j);
                    int b = getIndex(i, j - 1);
                    if (!query(a, b)) {
                        union(a, b);
                        counts--;
                    }
                }
            }
            res.add(counts);
        }
        return res;
    }

    public static int[] p;
    public static int row;
    public static int col;
    public static int find(int x) {
        if (x != p[x]) {
            p[x] = find(p[x]);
        }
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
        int[][] pp = {{0,0},{1,1},{0,1}};
        System.out.println(numIslands2(2,2,pp));
    }

}

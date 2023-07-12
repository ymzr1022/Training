package com.ymzr.Leetcode.Recursion;

/**
 * 给你一个 m x n 的网格 grid。网格里的每个单元都代表一条街道。grid[i][j] 的街道可以是：
 *
 * 1 表示连接左单元格和右单元格的街道。
 * 2 表示连接上单元格和下单元格的街道。
 * 3表示连接左单元格和下单元格的街道。
 * 4 表示连接右单元格和下单元格的街道。
 * 5 表示连接左单元格和上单元格的街道。
 * 6 表示连接右单元格和上单元格的街道。
 *
 *
 * 你最开始从左上角的单元格 (0,0) 开始出发，网格中的「有效路径」是指从左上方的单元格 (0,0) 开始、一直到右下方的 (m-1,n-1) 结束的路径。该路径必须只沿着街道走。
 *
 * 注意：你 不能 变更街道。
 *
 * 如果网格中存在有效的路径，则返回 true，否则返回 false 。
 *
 * 
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[2,4,3],[6,5,2]]
 * 输出：true
 * 解释：如图所示，你可以从 (0, 0) 开始，访问网格中的所有单元格并到达 (m - 1, n - 1) 。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[1,2,1],[1,2,1]]
 * 输出：false
 * 解释：如图所示，单元格 (0, 0) 上的街道没有与任何其他单元格上的街道相连，你只会停在 (0, 0) 处。
 * 示例 3：
 *
 * 输入：grid = [[1,1,2]]
 * 输出：false
 * 解释：你会停在 (0, 1)，而且无法到达 (0, 2) 。
 * 示例 4：
 *
 * 输入：grid = [[1,1,1,1,1,1,3]]
 * 输出：true
 * 示例 5：
 *
 * 输入：grid = [[2],[2],[2],[2],[2],[2],[6]]
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-if-there-is-a-valid-path-in-a-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1391 {
    public static boolean flg = false;
    public static boolean hasValidPath(int[][] grid) {
        int[][] visit = new int[grid.length][grid[0].length];
        process(grid,0,0,visit);
        return flg;
    }

    public static void process(int[][] grid, int i, int j,int[][] visit) {
        if ((i == grid.length - 1 && j == grid[0].length - 1)
                ) {
            flg = true;
            return;
        }
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visit[i][j] == 1) {
            return;
        }

        visit[i][j] = 1;
        if (grid[i][j] == 1) {
            if (check(grid, i ,j) || (j + 1 < visit[0].length && (grid[i][j + 1] == 3 || grid[i][j + 1] == 5 || grid[i][j + 1] == 1)))
            process(grid, i, j + 1,visit);
            if (check(grid, i ,j) || (j - 1 >= 0 && (grid[i][j - 1] == 2 || grid[i][j - 1] == 4 || grid[i][j - 1] == 1)))
            process(grid, i, j - 1,visit);
        }
        if (grid[i][j] == 2) {
            if (check(grid, i ,j) || (i + 1 < visit.length && (grid[i + 1][j] == 6 || grid[i + 1][j] == 5 || grid[i + 1][j] == 2)))
            process(grid, i + 1, j,visit);
            if (check(grid, i ,j) || (i - 1 >= 0 && (grid[i - 1][j] == 3 || grid[i - 1][j] == 4 || grid[i - 1][j] == 2)))
            process(grid, i - 1, j,visit);
        }
        if (grid[i][j] == 3) {
            if ((j - 1 >= 0 && (grid[i][j - 1] == 1 || grid[i][j - 1] == 4 || grid[i][j - 1] == 6)) || check(grid, i ,j) )
            process(grid, i, j - 1,visit);
            if ((i + 1 < visit.length && (grid[i + 1][j] == 6 || grid[i + 1][j] == 5 || grid[i + 1][j] == 2)) || check(grid, i ,j) )
            process(grid, i + 1, j,visit);
        }
        if (grid[i][j] == 4) {
            if (check(grid, i ,j) || (j + 1 < visit[0].length && (grid[i][j + 1] == 3 || grid[i][j + 1] == 5 || grid[i][j + 1] == 1)))
            process(grid, i, j + 1,visit);
            if (check(grid, i ,j) || (i + 1 < visit.length && (grid[i + 1][j] == 6 || grid[i + 1][j] == 5 || grid[i + 1][j] == 2)))
            process(grid, i + 1, j,visit);
        }
        if (grid[i][j] == 5) {
            if ((i - 1 >= 0 && (grid[i - 1][j] == 3 || grid[i - 1][j] == 4 || grid[i + 1][j] == 2)) || check(grid, i ,j) )
            process(grid, i - 1, j,visit);
            if ((j - 1 >= 0 && (grid[i][j - 1] == 1 || grid[i][j - 1] == 4 || grid[i][j - 1] == 6)) || check(grid, i ,j) )
            process(grid, i, j - 1,visit);
        }
        if (grid[i][j] == 6) {
            if (check(grid, i ,j) || (i - 1 >= 0 && (grid[i - 1][j] == 3 || grid[i - 1][j] == 4 || grid[i - 1][j] == 2)))
            process(grid, i - 1, j,visit);
            if (check(grid, i ,j) || (j + 1 < visit[0].length && (grid[i][j + 1] == 3 || grid[i][j + 1] == 5 || grid[i][j + 1] == 1)))
            process(grid, i, j + 1,visit);
        }
    }

    public static boolean check(int[][] grid,int i , int j) {
        if ((i == grid.length - 1 && j + 1 == grid[0].length) ||
                (i + 1 == grid.length  && j == grid[0].length - 1))
                return true;
        return false;
    }

    public static void main(String[] args) {
        int[][] grid = {{6,1,3},{4,1,5}};
        System.out.println(hasValidPath(grid));
    }
}

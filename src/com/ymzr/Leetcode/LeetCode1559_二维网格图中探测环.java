package com.ymzr.Leetcode;

/**给你一个二维字符网格数组 grid ，大小为 m x n ，你需要检查 grid 中是否存在 相同值 形成的环。

 一个环是一条开始和结束于同一个格子的长度 大于等于 4 的路径。对于一个给定的格子，你可以移动到它上、下、左、右四个方向相邻的格子之一，可以移动的前提是这两个格子有 相同的值 。

 同时，你也不能回到上一次移动时所在的格子。比方说，环  (1, 1) -> (1, 2) -> (1, 1) 是不合法的，因为从 (1, 2) 移动到 (1, 1) 回到了上一次移动时的格子。

 如果 grid 中有相同值形成的环，请你返回 true ，否则返回 false 。

  

 示例 1：



 输入：grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
 输出：true
 解释：如下图所示，有 2 个用不同颜色标出来的环：

 示例 2：



 输入：grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
 输出：true
 解释：如下图所示，只有高亮所示的一个合法环：

 示例 3：



 输入：grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
 输出：false
  

 提示：

 m == grid.length
 n == grid[i].length
 1 <= m <= 500
 1 <= n <= 500
 grid 只包含小写英文字母。

 来源：力扣（LeetCode）
 链接：https://leetcode.cn/problems/detect-cycles-in-2d-grid
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1559_二维网格图中探测环 {

    public static boolean[][] visit;

    public static boolean res;

    public static boolean containsCycle(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        visit = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visit[i][j]) {
                    process(grid, i, j, 'L', grid[i][j]);
                    if (res) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void process(char[][] grid, int i, int j, char from, char target) {

        if (i >= grid.length || j >= grid[0].length || i < 0 || j < 0 || grid[i][j] != target) {
            return;
        }
        if (visit[i][j]) {
            res = true;
            return;
        }

        visit[i][j] = true;
        if ('L' != from) process(grid, i , j - 1, 'R', target);
        if ('R' != from) process(grid, i , j + 1, 'L', target);
        if ('D' != from) process(grid, i + 1, j, 'U', target);
        if ('U' != from) process(grid, i - 1, j, 'D', target);

    }

    public static void main(String[] args) {
        char[][] grid = {{'c','a','d'},{'a','a','a'},{'a','a','d'},{'a','c','d'},{'a','b','c'}};
        System.out.println(containsCycle(grid));
    }
}

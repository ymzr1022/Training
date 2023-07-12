package com.ymzr.Leetcode.UnionFindSet;

import java.util.*;

/**
 * 你准备参加一场远足活动。给你一个二维rows x columns的地图heights，其中heights[row][col]表示格子(row, col)的高度。一开始你在最左上角的格子(0, 0)，且你希望去最右下角的格子(rows-1, columns-1)（注意下标从 0 开始编号）。你每次可以往 上，下，左，右四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 *
 * 一条路径耗费的 体力值是路径上相邻格子之间 高度差绝对值的 最大值决定的。
 *
 * 请你返回从左上角走到右下角的最小体力消耗值。
 *
 * 
 *
 * 示例 1：
 *
 *
 *
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 * 示例 2：
 *
 *
 *
 * 输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 * 输出：1
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
 * 示例 3：
 *
 *
 * 输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * 输出：0
 * 解释：上图所示路径不需要消耗任何体力。
 *
 * https://leetcode.cn/problems/path-with-minimum-effort/
 */
public class LeetCode1631 {
//    public static int minimumEffortPath(int[][] heights) {
//        if (heights == null || heights.length == 0 || heights[0].length == 0) {
//            return 0;
//        }
//
//        int[][] visit = new int[heights.length][heights[0].length];
//        return process(heights, 0, 0, 0, visit);
//    }
//
//    public static int process(int[][] heights, int i, int j, int cost, int[][] visit) {
//        if (i < 0 || j < 0 || i >= heights.length || j >= heights[0].length) {
//            return 0;
//        }
//        if ((i == heights.length - 1 && j == heights[0].length - 1)) {
//            return cost;
//        }
//        int right = 0;
//        int left = 0;
//        int down = 0;
//        int up = 0;
//        if (j != heights[0].length - 1 && visit[i][j + 1] == 0) {
//            visit[i][j + 1] = 1;
//            right = process(heights, i, j + 1, Math.max(Math.abs(heights[i][j + 1] - heights[i][j]), cost), visit);
//            visit[i][j + 1] = 0;
//        }
//        if (j != 0  && visit[i][j - 1] == 0) {
//            visit[i][j - 1] = 1;
//            left = process(heights, i, j - 1, Math.max(Math.abs(heights[i][j - 1] - heights[i][j]), cost), visit);
//            visit[i][j - 1] = 0;
//        }
//        if (i != heights.length - 1  && visit[i + 1][j] == 0) {
//            visit[i + 1][j] = 1;
//            down = process(heights, i + 1, j, Math.max(Math.abs(heights[i + 1][j] - heights[i][j]), cost), visit);
//            visit[i + 1][j] = 0;
//        }
//        if (i != 0  && visit[i - 1][j] == 0) {
//            visit[i - 1][j] = 1;
//            up = process(heights, i - 1, j, Math.max(Math.abs(heights[i - 1][j] - heights[i][j]), cost), visit);
//            visit[i - 1][j] = 0;
//        }
//        int ans = Math.min(Math.min(Math.min(right, left), down), up);
//        return ans;
//
//    }

    public static void main(String[] args) {
        int[][] heights =  {{4,3,4,10,5,5,9,2},
                            {10,8,2,10,9,7,5,6},
                            {5,8,10,10,10,7,4,2},
                            {5,1,3,1,1,3,1,9},
                            {6,4,10,6,10,9,4,6}};
        System.out.println(minimumEffortPath(heights));
    }

    public static int N = 10009;
    public static int[] p = new int[N];
    public static int row;
    public static int col;

    public static int find(int a) {
        if (p[a] != a) {
            p[a] = find(p[a]);
        }
        return p[a];
    }

    public static void union(int a, int b) {
        p[find(a)] = p[find(b)];
    }

    public static boolean query(int a, int b) {
        return p[find(a)] == p[find(b)];
    }

    public static int minimumEffortPath(int[][] heights) {
        row = heights.length;
        col = heights[0].length;

        for (int i = 0; i < col * row; i++) {
            p[i] = i;
        }

        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int idx = getIndex(i, j);
                if (i + 1 < row) {
                    int a = idx, b = getIndex(i + 1, j);
                    int w = Math.abs(heights[i][j] - heights[i + 1][j]);
                    edges.add(new int[]{a, b, w});
                }
                if (j + 1 < col) {
                    int a = idx, b = getIndex(i, j + 1);
                    int w = Math.abs(heights[i][j] - heights[i][j + 1]);
                    edges.add(new int[]{a, b, w});
                }
            }
        }

        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        int start = getIndex(0, 0);
        int end = getIndex(row - 1, col - 1);
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int w = edge[2];
            union(a, b);
            if (query(start, end)) {
                return w;
            }
        }

        return 0;

    }

    public static int getIndex(int a, int b) {
        return a * col + b;
    }
}

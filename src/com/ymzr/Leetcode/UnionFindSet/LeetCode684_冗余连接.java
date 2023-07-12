package com.ymzr.Leetcode.UnionFindSet;

/**
 * 树可以看成是一个连通且 无环的无向图。
 *
 * 给定往一棵n 个节点 (节点值1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组 edges，edges[i] = [ai, bi]表示图中在 ai 和 bi 之间存在一条边。
 *
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组edges中最后出现的那个。
 *
 * 
 *
 * 示例 1：
 *
 *
 *
 * 输入: edges = [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 示例 2：
 *
 *
 *
 * 输入: edges = [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 
 *
 * 提示:
 *
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai< bi<= edges.length
 * ai != bi
 * edges 中无重复元素
 * 给定的图是连通的
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/redundant-connection
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。树可以看成是一个连通且 无环的无向图。
 *
 * 给定往一棵n 个节点 (节点值1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组 edges，edges[i] = [ai, bi]表示图中在 ai 和 bi 之间存在一条边。
 *
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组edges中最后出现的那个。
 *
 * 
 *
 * 示例 1：
 *
 *
 *
 * 输入: edges = [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 示例 2：
 *
 *
 *
 * 输入: edges = [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 
 *
 * 提示:
 *
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai< bi<= edges.length
 * ai != bi
 * edges 中无重复元素
 * 给定的图是连通的
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/redundant-connection
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode684_冗余连接 {


    public int[] findRedundantConnection(int[][] edges) {
        int row = edges.length;
        p = new int[row + 1];
        for (int i = 1; i <= row; i++) {
            p[i] = i;
        }

        for (int i = 0; i < row; i++) {
            if (!query(edges[i][0], edges[i][1])) {
                union(edges[i][0], edges[i][1]);
            } else {
                return edges[i];
            }
        }
        return new int[2];
    }

    public int[] p;

    public int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }

    public void union(int a, int b) {
        p[find(a)] = p[find(b)];
    }

    public boolean query(int a, int b) {
        return p[find(a)] == p[find(b)];
    }
}

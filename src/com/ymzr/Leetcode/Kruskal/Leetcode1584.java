package com.ymzr.Leetcode.Kruskal;

import com.ymzr.Training.UnionFIndSets;

import java.util.*;

/**
 * 给你一个points数组，表示 2D 平面上的一些点，其中points[i] = [xi, yi]。
 *
 * 连接点[xi, yi] 和点[xj, yj]的费用为它们之间的 曼哈顿距离：|xi - xj| + |yi - yj|，其中|val|表示val的绝对值。
 *
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有一条简单路径时，才认为所有点都已连接。
 *
 * 
 *
 * 示例 1：
 *
 *
 *
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：
 *
 * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 * 示例 2：
 *
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 * 示例 3：
 *
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 * 示例 4：
 *
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 * 示例 5：
 *
 * 输入：points = [[0,0]]
 * 输出：0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/min-cost-to-connect-all-points
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode1584 {

        public static int row;
    public static int minCostConnectPoints(int[][] points) {
        row = points.length;
        List<int[]> list = Arrays.asList(points);

        UnionFindSet<int[]> set = new UnionFindSet<>(list);

        List<int[][]> tmp = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (i != j) {
                    int w = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                    tmp.add(new int[][]{points[i], points[j], {w,0}});
                }
            }
        }
        Collections.sort(tmp, Comparator.comparingInt(a -> a[2][0]));

        int ans = 0;
        for (int[][] nums: tmp
             ) {
            int[] a = nums[0];
            int[] b = nums[1];
            int w = nums[2][0];
            if (set.sizeMap.get(set.find(set.map.get(a))) == row) {
                return ans;
            }
            if (!set.isSameSet(a, b)) {
                set.union(a, b);
                ans += w;
            }
        }

        return ans;
    }

    public static int getIndex(int i, int j) {
        return i * row + j;
    }

    public static class Element<V> {
        V val;
        Element<V> element;

        public Element(V v) {
            val = v;
        }
    }

    public static class UnionFindSet<V> {

        public HashMap<V, Element<V>> map;
        public HashMap<Element<V>, Element<V>> fatherMap;
        public HashMap<Element<V>, Integer> sizeMap;

        public UnionFindSet(List<V> list) {
            map = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V v: list
            ) {
                Element<V> element = new Element<>(v);
                map.put(v, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        public Element<V> find(Element<V> element) {
            Stack<Element<V>> stack = new Stack<>();
            while (element != fatherMap.get(element)) {
                stack.push(element);
                element = fatherMap.get(element);
            }
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), element);
            }
            return element;
        }


        public void union(V a, V b) {
            if (map.containsKey(a) && map.containsKey(b)) {
                Element<V> aNode = find(map.get(a));
                Element<V> bNode = find(map.get(b));
                if (aNode != bNode) {
                    Element<V> big = sizeMap.get(aNode) > sizeMap.get(bNode) ? aNode : bNode;
                    Element<V> small = big == aNode ? bNode : aNode;
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(big) + sizeMap.get(small));
                    sizeMap.remove(small);
                }
            }
        }

        public boolean isSameSet(V a, V b) {
            if (map.containsKey(a) && map.containsKey(b)) {
                return find(map.get(a)) == find(map.get(b));
            }
            return false;
        }

    }

    public static void main(String[] args) {
        int[][] points = {{0,0},{1,1},{1,0},{-1,1}};
        System.out.println(minCostConnectPoints(points));
        System.out.println(minCostConnectPoints2(points));
    }

    public static int[] p = new int[1005];

    public static int find(int a) {
        if (a != p[a]) {
            a = find(p[a]);
        }
        return a;
    }

    public static void union(int a, int b) {
        p[find(a)] = p[find(b)];
    }

    public static boolean query(int a, int b) {
        return p[find(a)] == p[find(b)];
    }

    public static int minCostConnectPoints2(int[][] points) {
        row = points.length;

        for (int i = 0; i < row; i++) {
            p[i] = i;
        }
        List<int[]> edge = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = i + 1; j < row; j++) {
                int w = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);;
                edge.add(new int[]{i, j, w});
            }
        }

        Collections.sort(edge, Comparator.comparingInt(a -> a[2]));
        int ans = 0;
        int count = 0;
        for (int[] edg: edge
             ) {
            if (count == row - 1) {
                return ans;
            }
            int a = edg[0];
            int b = edg[1];
            int w = edg[2];
            if (!query(a, b)) {
                union(a, b);
                ans += w;
                count++;
            }
        }
        return ans;
    }

}

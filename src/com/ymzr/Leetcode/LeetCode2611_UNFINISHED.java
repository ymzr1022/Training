package com.ymzr.Leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 有两只老鼠和n块不同类型的奶酪，每块奶酪都只能被其中一只老鼠吃掉。
 *
 * 下标为 i处的奶酪被吃掉的得分为：
 *
 * 如果第一只老鼠吃掉，则得分为reward1[i]。
 * 如果第二只老鼠吃掉，则得分为reward2[i]。
 * 给你一个正整数数组reward1，一个正整数数组reward2，和一个非负整数k。
 *
 * 请你返回第一只老鼠恰好吃掉 k块奶酪的情况下，最大得分为多少。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
 * 输出：15
 * 解释：这个例子中，第一只老鼠吃掉第 2和 3 块奶酪（下标从 0 开始），第二只老鼠吃掉第 0 和 1 块奶酪。
 * 总得分为 4 + 4 + 3 + 4 = 15 。
 * 15 是最高得分。
 * 示例 2：
 *
 * 输入：reward1 = [1,1], reward2 = [1,1], k = 2
 * 输出：2
 * 解释：这个例子中，第一只老鼠吃掉第 0 和 1 块奶酪（下标从 0 开始），第二只老鼠不吃任何奶酪。
 * 总得分为 1 + 1 = 2 。
 * 2 是最高得分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/mice-and-cheese
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode2611_UNFINISHED {
    public static int miceAndCheese(int[] reward1, int[] reward2, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int res = 0;
        for (int i = 0; i < reward1.length; i++ ) {
            res += reward2[i];
            priorityQueue.add(reward1[i] - reward2[i]);
        }
        priorityQueue.remove(888);
        while (priorityQueue != null && priorityQueue.size() > 0 && k-- > 0) {
            res += priorityQueue.poll();
        }

        return res;

    }

    public static class Num {
        int num;
        int index;

        public Num() {}
        public Num(int nu, int in) {
            num = nu;
            index = in;
        }
    }

    public static class numComparator implements Comparator<Num> {

        @Override
        public int compare(Num o1, Num o2) {
            return o2.num - o1.num;
        }
    }

    public static void main(String[] args) {
        int[] reward1 = {1,4,4,6,4};
        int[] reward2 = {6,5,3,6,1};
        System.out.println(miceAndCheese(reward1,reward2,1));
    }
}

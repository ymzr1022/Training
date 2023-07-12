package com.ymzr.Leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给你一个数组nums，它包含若干正整数。
 *
 * 一开始分数score = 0，请你按照下面算法求出最后分数：
 *
 * 从数组中选择最小且没有被标记的整数。如果有相等元素，选择下标最小的一个。
 * 将选中的整数加到score中。
 * 标记 被选中元素，如果有相邻元素，则同时标记与它相邻的两个元素。
 * 重复此过程直到数组中所有元素都被标记。
 * 请你返回执行上述算法后最后的分数。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：nums = [2,1,3,4,5,2]
 * 输出：7
 * 解释：我们按照如下步骤标记元素：
 * - 1 是最小未标记元素，所以标记它和相邻两个元素：[2,1,3,4,5,2] 。
 * - 2 是最小未标记元素，所以标记它和左边相邻元素：[2,1,3,4,5,2] 。
 * - 4 是仅剩唯一未标记的元素，所以我们标记它：[2,1,3,4,5,2] 。
 * 总得分为 1 + 2 + 4 = 7 。
 * 示例 2：
 *
 * 输入：nums = [2,3,5,1,3,2]
 * 输出：5
 * 解释：我们按照如下步骤标记元素：
 * - 1 是最小未标记元素，所以标记它和相邻两个元素：[2,3,5,1,3,2] 。
 * - 2 是最小未标记元素，由于有两个 2 ，我们选择最左边的一个 2 ，也就是下标为 0 处的 2 ，以及它右边相邻的元素：[2,3,5,1,3,2] 。
 * - 2 是仅剩唯一未标记的元素，所以我们标记它：[2,3,5,1,3,2] 。
 * 总得分为 1 + 2 + 2 = 5 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-score-of-an-array-after-marking-all-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode2593 {

    public static long findScore(int[] nums) {
        long res = 0;
        int n = nums.length;
        Integer[] ids = new Integer[n];
        for (int i = 0 ; i < n ; i++ ) ids[i] = i;

        Arrays.sort(ids, (i , j) -> nums[i] - nums[j]);

        boolean[] vis = new boolean[n + 2];
        for (int i : ids) {
            if (!vis[i + 1]) {
                vis[i] = vis[i + 2] = true;
                res += nums[i];
            }
        }

        return res;
    }
    public static long findScores(int[] nums) {
        Queue<Param> queue = new PriorityQueue<>(new ParamComparator());
        for (int i = 0 ; i < nums.length ; i++) {
            Param param = new Param(nums[i],i);
            queue.add(param);
        }
        int res = 0;
        while (!queue.isEmpty()) {
            Param param = queue.poll();
            res += param.num;
            if (param.index > 0 && param.index < nums.length - 1) {
                queue.removeIf(e -> e.index == param.index - 1);
                queue.removeIf(e -> e.index == param.index + 1);
            } else if (param.index == 0){
                queue.removeIf(e -> e.index == param.index + 1);
            } else if (param.index == nums.length - 1) {
                queue.removeIf(e -> e.index == param.index - 1);
            }
        }
        return res;
    }

    public static class Param {
        int num;
        int index;

        public Param(int nu, int in) {
            num = nu;
            index = in;
        }
    }

    public static class ParamComparator implements Comparator<Param> {

        @Override
        public int compare(Param o1, Param o2) {
            return o1.num == o2.num ? o1.index - o2.index : o1.num - o2.num;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,1,3,4,5,2};
        System.out.println(findScores(nums));
        System.out.println(findScore(nums));
    }
}

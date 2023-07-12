package com.ymzr.Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个下标从 0 开始的正整数数组 nums 。请你找出并统计满足下述条件的三元组 (i, j, k) 的数目：
 *
 * 0 <= i < j < k < nums.length
 * nums[i]、nums[j] 和 nums[k] 两两不同 。
 * 换句话说：nums[i] != nums[j]、nums[i] != nums[k] 且 nums[j] != nums[k] 。
 * 返回满足上述条件三元组的数目。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：nums = [4,4,2,4,3]
 * 输出：3
 * 解释：下面列出的三元组均满足题目条件：
 * - (0, 2, 4) 因为 4 != 2 != 3
 * - (1, 2, 4) 因为 4 != 2 != 3
 * - (2, 3, 4) 因为 2 != 4 != 3
 * 共计 3 个三元组，返回 3 。
 * 注意 (2, 0, 4) 不是有效的三元组，因为 2 > 0 。
 * 示例 2：
 *
 * 输入：nums = [1,1,1,1,1]
 * 输出：0
 * 解释：不存在满足条件的三元组，所以返回 0 。
 *
 */
public class LeetCode2475 {

    public int unequalTriplets(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],map.getOrDefault(nums[i], 0 ) + 1);
        }
        int ans = 0, a = 0;
        int n = nums.length;
        for (int b : map.values()) {
            int c = n - a - b;
            ans += a * b * c;
            a += b;
        }
        return ans;

    }
}

/**
 * 然后遍历哈希表
 * cnt，枚举中间元素的个数
 * b，左侧元素个数记为
 * a，那么右侧元素个数有
 * c=n−a−b，此时符合条件的三元组数量为
 * a×b×c，累加到答案中。接着更新
 * a=a+b，继续枚举中间元素的个数
 */
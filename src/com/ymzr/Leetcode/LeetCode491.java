package com.ymzr.Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
 *
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：nums = [4,6,7,7]
 * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 * 示例 2：
 *
 * 输入：nums = [4,4,3,2,1]
 * 输出：[[4,4]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/non-decreasing-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode491 {
    public static List<List<Integer>> res = new ArrayList<>();
    public static List<Integer> path = new ArrayList<>();

    public static List<List<Integer>> findSubsequences(int[] nums) {
        process(0, Integer.MIN_VALUE, nums);
        return res;
    }

    public static void process(int index, int last, int[] nums) {
        if (index == nums.length) {
            if (path.size() > 1) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        if (nums[index] >= last) {
            path.add(nums[index]);
            process(index + 1, nums[index], nums);
            path.remove(path.size() - 1);
        }
        if (nums[index] != last) {
            process(index + 1, last, nums);
        }




    }

    public static void main(String[] args) {
        int[] nums = {4,6,7,7};
        System.out.println(findSubsequences(nums).toString());
    }
}

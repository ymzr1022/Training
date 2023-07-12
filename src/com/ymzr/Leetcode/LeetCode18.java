package com.ymzr.Leetcode;

import java.util.*;

/**
 * 给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]（若两个四元组元素一一对应，则认为两个四元组重复）：
 *
 * 0 <= a, b, c, d< n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 *
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *
 */
public class LeetCode18 {

    //递归 超时做法
    public static List<List<Integer>> res = new ArrayList<>();
    public static HashSet<List<Integer>> set = new HashSet<>();
    public static List<Integer> tmp = new ArrayList<>();
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        process(0, nums, 0 , target);
        return res;
    }

    public static void process(int sum, int[] nums, int index, int target) {
        if (sum == target && tmp.size() == 4 && !res.contains(new ArrayList<>(tmp))) {
                res.add(new ArrayList<>(tmp));
        }
        if (index >= nums.length) {
            return;
        }

        if (tmp.size() < 4) {
            tmp.add(nums[index]);
            process(sum + nums[index], nums, index + 1, target);
            tmp.remove(tmp.size() - 1);
            process(sum, nums, index + 1, target);
        }

    }



    //三指针法



    public static void main(String[] args) {
        int[] nums = {-5,5,4,-3,0,0,4,-2};
        System.out.println(fourSum(nums, 4).toString());
        System.out.println("s".equals(null));
    }
}

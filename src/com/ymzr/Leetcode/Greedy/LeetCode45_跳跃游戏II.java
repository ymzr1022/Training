package com.ymzr.Leetcode.Greedy;

/**
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 *
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 *
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 *
 * 
 *
 * 示例 1:
 *
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *     从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
 * 示例 2:
 *
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * 
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 * 题目保证可以到达nums[n-1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/jump-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode45_跳跃游戏II {

    public static int jump(int[] nums) {
        int i = 0;
        int count = 0;
        if (nums.length == 1) {
            return 0;
        }
        while (i < nums.length) {
            int max = 0;
            int index = i;
            if (i == nums.length - 1) {
                return count;
            }
            count++;
            if (i + nums[i] >= nums.length - 1) {
                return count;
            }
            for (int j = 1; j <= nums[i]; j++) {
                if (j + i > nums.length - 1) {
                    return count;
                }
                if (max <= nums[j + i] + j) {
                    max = nums[j + i] + j;
                    index = j + i;
                }
            }
            i = index;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump(nums));
    }


}

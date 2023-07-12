package com.ymzr.Leetcode.Greedy;

import java.util.*;

/**
 * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回 所需会议室的最小数量 。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：intervals = [[0,30],[5,10],[15,20]]
 * 输出：2
 * 示例 2：
 *
 * 输入：intervals = [[7,10],[2,4]]
 * 输出：1
 * 
 *
 * 提示：
 *
 * 1 <=intervals.length <= 104
 * 0 <= starti < endi <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/meeting-rooms-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_253_会议室II {
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 1) {
            return 1;
        }

        List<int[]> list = new ArrayList<>();
        for (int[] nums : intervals
             ) {
            list.add(new int[]{nums[0], 1});
            list.add(new int[]{nums[1], -1});
        }
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                return o1[0] - o2[0] == 0 ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        int cnt = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            cnt += list.get(i)[1];
            max = Math.max(max, cnt);
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] intervals = {{13,15},{1,13}};
        minMeetingRooms(intervals);
    }
}

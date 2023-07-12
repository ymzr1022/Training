package com.ymzr.Training;

public class ShimaProblem {
    public static int landProblem(int[][] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                if (nums[i][j] == 1) {
                    ans++;
                    process(nums, i, j);
                }
            }
        }
        return ans;
    }

    public static void process(int[][] nums,int i, int j) {
        if (i < 0 || i >= nums.length || j < 0 || j >= nums[0].length || nums[i][j] == 2 || nums[i][j] == 0) {
            return;
        }

        nums[i][j] = 2;
        process(nums, i + 1, j);
        process(nums, i - 1, j);
        process(nums, i, j + 1);
        process(nums, i, j - 1);
    }

    public static void main(String[] args) {
        int[][] nums = {{0,1,0,0,0,0},
                        {0,1,0,0,0,0},
                        {1,1,0,1,0,0},
                        {0,1,0,0,1,1},
                        {0,1,0,0,0,0}};
        System.out.println(landProblem(nums));
    }
}

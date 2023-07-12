package com.ymzr.Leetcode;

import javax.sound.midi.Soundbank;
import java.util.Map;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围[−231, 231− 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * 
 *
 * 示例 1：
 *
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 *
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 *
 * 输入：x = 0
 * 输出：0
 *
 */
public class LeetCode7 {

    public static int reverse(int x) {
        String str = String.valueOf(x);
        String tmp = str;
        int m = 1;
        if (str.startsWith("-")) {
            tmp = str.substring(1);
            m = -1;
        }
        char[] chars = tmp.toCharArray();
        int ans = 0;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - '0';
            if (Math.pow(10, i) * index > Integer.MAX_VALUE) {
                return 0;
            }
            ans += (int)Math.pow(10, i) * index;
            if (ans < 0) {
                return 0;
            }
        }
        return ans * m;
    }

    public static int reverse1(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }


    public static void main(String[] args) {
        System.out.println(String.valueOf((int)-1000));
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE - 1);
        System.out.println(Integer.MIN_VALUE);
//        System.out.println(reverse(-19990));
        System.out.println(reverse(1563847412));
        System.out.println(reverse1(1563847412));
    }
}

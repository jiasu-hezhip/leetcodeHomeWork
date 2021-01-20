package Day11三个数的最大乘积;

import java.util.Arrays;

/**
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: 24
 *
 * 有可能输出负数，而因为是求最大值所以只能是绝对值最大的两个负数相乘得到最大的正数
 * 否则就应该是最大的正数相乘
 * 结果只可能出现在排序后的0，1，length和最后三个之中
 *
 * 同样的也可以直接扫描出最大的3个正数和最小的两个负数
 */

public class Solution {
    public static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        if (nums[0]<0 && nums[1]<0){
            return nums[0]*nums[1]*nums[nums.length-1]>nums[nums.length-1]*nums[nums.length-2]*nums[nums.length-3]?nums[0]*nums[1]*nums[nums.length-1]:nums[nums.length-1]*nums[nums.length-2]*nums[nums.length-3];
        }
        return nums[nums.length-1]*nums[nums.length-2]*nums[nums.length-3];
    }
    //扫描方案
//    public int maximumProduct(int[] nums) {
//        // 最小的和第二小的
//        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
//        // 最大的、第二大的和第三大的
//        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
//
//        for (int x : nums) {
//            if (x < min1) {
//                min2 = min1;
//                min1 = x;
//            } else if (x < min2) {
//                min2 = x;
//            }
//
//            if (x > max1) {
//                max3 = max2;
//                max2 = max1;
//                max1 = x;
//            } else if (x > max2) {
//                max3 = max2;
//                max2 = x;
//            } else if (x > max3) {
//                max3 = x;
//            }
//        }
//
//        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
//    }


    public static void main(String[] args) {
        int[] n = {-9,2,4,7,-10,1,2,9};
        System.out.println(maximumProduct(n));
    }
}

package Day35跳跃游戏;

public class Solution {

    /**
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 判断你是否能够到达最后一个下标。
     *
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     *
     * 输入：nums = [3,2,1,0,4]
     * 输出：false
     * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
     *
     *
     * 第一个解法，暴力穷举，递归查找
     *      对于每个数组值，挨个查找他值内部的所有值，直到数组下标到达最后一位
     *
     * 优化一下就是贪心解法，注意要维护的不是数组值+数组下标值和的最大值，而是维护能到达的最大值
     * 第二 贪心解法 一般情况下只要出现 数组每个元素是多少 能否到达 之类的词要想到贪心
     *      具体流程：挨个遍历值，维护最大值
     *      最大值是当前下标+当前值或者上一个最大值其中的一个
     *      如果最大值大于数组长度返回true
     *
     *
     *
     *
     * @param args
     */

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(canJump(nums));
    }

    public static boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) { // 判断如果下标位置小于等于维护的最大值才做判断
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

}

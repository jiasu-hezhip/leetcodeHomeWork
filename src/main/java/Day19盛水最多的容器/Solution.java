package Day19盛水最多的容器;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    /**
     * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
     *
     * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
     *
     * 返回容器可以储存的最大水量。
     *
     * 说明：你不能倾斜容器。
     *
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49 
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
     *
     *
     * 其实暴力for循环可以直接解出来，但是时间复杂度很高
     *
     *
     * 最优解法是双指针，即给出两个指针，从数组的两端开始寻找体积 体积的计算公式是 min(x,y)∗长度
     * 每次都把min(x,y)的小的一个向后移一位或者向前一位，直到最后相遇
     * 每次把得到的最大值做个对比，最后输出最大值
     *
     */

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        ArrayList<String> arrayList = new ArrayList<>();
//        while (in.hasNextLine()){
//            arrayList.add(in.nextLine());
//        }

        int[] a = {1,9,2,4,1,2,7};
        System.out.println(maxArea(a));

    }

    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }

}

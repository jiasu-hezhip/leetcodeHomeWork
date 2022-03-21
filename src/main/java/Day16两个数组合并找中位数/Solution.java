package Day16两个数组合并找中位数;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        ArrayList<String> arrayList = new ArrayList<>();
//        while (in.hasNextLine()) {
//            arrayList.add(in.nextLine());
//        }
//        String[] s = arrayList.get(0).split(" ");
//        String[] s2 = arrayList.get(0).split(" ");

        /**
         * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
         *
         * 算法的时间复杂度应该为
         * O(log (m+n)) 。
         * 出现要求算法时间复杂度的，而且出现了log的查找，要联想到二分查找
         */

        int[] a = {1, 2};
        int[] b = {3, 4};

        System.out.println(findMedianSortedArrays(a, b));

    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /**
         * 这道题目可以说非常复杂
         * 时间复杂度要求不可以直接用归并排序合并两个数组
         * 看到log要直接想到二分查找
         * 需要寻找两个数组合并后的中位数
         * 要特别注意边界考量的问题
         *
         *
         * 官方解法是一种非常复杂的切分问题，需要考量大量的边界判断
         * 有一种更加通用的在两个数组中寻找第？小元素的方法
         * 即每次都在两个数组中找？/2的元素，如果a[？/2] < b[?/2] 则所有a中被对比的数都不可能是第？小的数，反之则排除所有b中的数
         * 如果某个数组的长度小于？/2 则只需要判断数组的最后一个
         * 如果有一个数组为空则退化为在单一的数组中寻找中位数
         *
         */


        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;

    }

    public static double getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1]; // 这种就是退化的情况

        if (k == 1) return Math.min(nums1[start1], nums2[start2]); // k==1时就找到了

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
}
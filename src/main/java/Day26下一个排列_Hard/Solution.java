package Day26下一个排列_Hard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    /**
     * 整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。
     *
     * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
     * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
     *
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     * 给你一个整数数组 nums ，找出 nums 的下一个排列。
     *
     * 必须 原地 修改，只允许使用额外常数空间。
     *
     * 完全没头绪的一题
     * 这道题要首先分析排列和尽可能大的排列是什么意思
     * 通俗的来说，就是按照自然数排序来比大小，但是这道题要求不能使用其他额外的空间
     * 所以对于一个序列 123456 来说，第一个是123465 第二个是123546而不是123645 第三个是123564
     *
     * 在想明白来了这个原理后 就要从后向前进行扫描
     * 只要出现一个升序的元素对就记录下来 以124653为例 第一个升序对是4 6
     * 在 6 5 3中寻找一个最小的比4大的元素 5
     * 交换4 5 125643
     * 排序 6 4 3
     * 125346
     *
     * 这种问题一般想不到解决办法，除非在限制时间内能提炼出规划的方法，只需要记住就行
     *
     * @param args
     */
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("/Users/togi/Downloads/day01-SpringCloud01/代码/leetcodeHomeWork/src/main/java/Day26下一个排列_Hard/test.txt"));
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (in.hasNextInt()){
            arrayList.add(in.nextInt());
        }
        int[] array = arrayList.stream().mapToInt(Integer::intValue).toArray();
        nextPermutation(array);
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            if (i >= array.length -1) {
                System.out.print(array[i]);
            }else {
                System.out.print(array[i] +",");
            }
        }
        System.out.print("]");
    }

    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2; // 找最大值的时候使用，因为length是最大值，本来就需要 length-1 来表示最后一位，所以为了寻找升序对，直接-2
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }// 找到第一个需要交换的位置
        if (i >= 0) {
            int j = nums.length - 1; // 依旧是从最后一个开始寻找
            while (j >= 0 && nums[i] >= nums[j]) { //只要找到一个比i位置上小的就可以
                j--;
            }// 找到第二个需要交换的位置
            swap(nums, i, j); // 交换 i j
        }
        // 上两步都是在寻找合适的 i j 如果没找到是不会执行交换的，也就是已经最大了
        reverse(nums, i + 1); //将 i 之后的所有元素排序成自然数队列
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

}

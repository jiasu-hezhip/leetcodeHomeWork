package Day29排序数组中找目标值位置;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 如果数组中不存在目标值 target，返回[-1, -1]。
     *
     * 时间复杂度为O(log n)
     *
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     *
     *
     * 但凡是寻找算法又指定了时间复杂度是 logn 的，几乎都是二分查找
     * 这题也是
     *  我的思路是改进的二分查找，具体思路是：
     *  首先取开始和结束的数组下标，计算中间值
     *  如果中间正好等于目标值，分开出双指针向前向后寻找边界，极端情况下会退化成o（n）
     *  之后就是正常的找目标值，直到开始和结束的下标相等
     *
     *  官方解法
     *  两次二分查找，但是边界确定不同（太魔鬼了！！！！）
     *  主体思想不变
     *  但是在二分查找时判断了一下mid 与 自己相等时，添加一个标记位，如果标记位为true 则表示相等时右边界要移动到mid-1
     *  否则左边界移动到mid+1
     *  这样就可以根据targer找到target的第一个值和target向后一位的第一个值
     *  返回这两个数组组成的值就行
     *
     * @param args
     */

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("/Users/togi/Downloads/day01-SpringCloud01/代码/leetcodeHomeWork/src/main/java/Day29排序数组中找目标值位置/test.txt"));
        int target = in.nextInt();
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (in.hasNextInt()) {
            arrayList.add(in.nextInt());
        }
        int[] array = arrayList.stream().mapToInt(Integer::intValue).toArray();
        int[] searchRange = searchRange(array, target);
        System.out.print("[");
        for (int i = 0; i < searchRange.length; i++) {
            if (i >= searchRange.length - 1) {
                System.out.print(searchRange[i]);
            }else {
                System.out.print(searchRange[i] + ",");
            }
        }
        System.out.print("]");
    }

    public static int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        } // 细节点，只有左小于右 并且 右小于长度 并且 数组的左节点 = 数组的右节点 = 目标值才返回数据
        return new int[]{-1, -1}; //否则返回找不到
    }

    public static int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2; // 以上都没什么好说的
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                // 当出现了中间和目标值相等的情况时 根据传入的标记位，确定是向前推进还是向后推进
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}

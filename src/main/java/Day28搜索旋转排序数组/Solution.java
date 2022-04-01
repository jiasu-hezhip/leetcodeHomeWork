package Day28搜索旋转排序数组;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    /**
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     *
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
     *
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
     *
     * ！！！请给出时间复杂度为 O(log n) 的解决方案
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     *
     * 输入：nums = [1], target = 0
     * 输出：-1
     *
     * 看到 logn 第一想法是二分查找，但是二分查找需要有序
     * 虽然数组不是有序的，但是部分有序，所以不太影响二分查找的思路
     * 首先取中间部分，观察左右部分是否有序，因为是旋转过的数组，所以一定有一边是有序的
     * 目标在有序数组中直接使用二分查找就可以
     * 如果目标不在有序数组中则把边界移到另一边去
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("/Users/togi/Downloads/day01-SpringCloud01/代码/leetcodeHomeWork/src/main/java/Day28搜索旋转排序数组/test.txt"));

        int target = in.nextInt();
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (in.hasNextInt()){
            arrayList.add(in.nextInt());
        }
        int[] nums = arrayList.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(search(nums,0));
    }

    public static int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) { // 健壮性判断，如果数组长度为0返回-1
            return -1;
        }
        if (n == 1) { // 健壮性判断，如果长度为1最后找的不为目标值返回-1否则返回0
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1; // 获取开启和结束下标值
        while (l <= r) {
            int mid = (l + r) / 2; //计算中间值
            if (nums[mid] == target) { //结束条件 即左指针和右指针相等时，应该就是找到了目标值
                return mid;
            }
            if (nums[0] <= nums[mid]) { // 如果第一位小于中间的，则第一位到中间值必定为递增
                if (nums[0] <= target && target < nums[mid]) { // 如果目标值大于开始值小于中间值
                    r = mid - 1; //排除掉mid向右的所有值，右边界移动到中间向左一个
                } else { // 否则目标值存在于旋转区间，左边界移动到中间向右一个
                    l = mid + 1;
                }
            } else { // 否则第一位到中间的这段必定出现了旋转
                if (nums[mid] < target && target <= nums[n - 1]) { //和上面的逻辑一样，如果出现在了顺序区间
                    l = mid + 1; // 左边界移动到mid的右边一个
                } else { //否则右区间移动到中间的左边一个
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

}

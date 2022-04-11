package Day37合并区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {

    /**
     * 以数组 intervals 表示若干个区间的集合，
     * 其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     *
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     *
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     *
     * 构建一个栈，遍历一遍
     * 如果栈里有元素，把要遍历的元素挨个和栈里比较，如果范围在栈里，更新栈，否则加入栈
     * 最后返回栈
     *
     * 官方多了一个排序，因为拍完序就不需要每次都做遍历，直接挨个添加就行
     * 数组排序要能想到Array.sort（array，comparator）
     * 对于加入数组的第一个方法   要能想到在数组加入时添加一个被添加数组长度为0的 或（｜｜） 条件
     *
     *
     * @param args
     */

    public static void main(String[] args) {
        int[][] nums = {{1,3},{2,6},{5,10},{11,20}};
        int[][] merge = merge(nums);
        System.out.print("[");
        for (int i = 0; i < merge.length; i++) {
            if (i +1 > merge.length -1) {
                System.out.print("[");
                for (int j = 0; j < merge[i].length; j++) {
                    if (j+1 > merge[i].length -1){
                        System.out.print(merge[i][j]);
                    }else {
                        System.out.print(merge[i][j] + ",");
                    }
                }
                System.out.print("]");
            }else {
                System.out.print("[");
                for (int j = 0; j < merge[i].length; j++) {
                    if (j+1 > merge[i].length -1){
                        System.out.print(merge[i][j]);
                    }else {
                        System.out.print(merge[i][j] + ",");
                    }
                }
                System.out.print("]");
                System.out.print(",");
            }
        }
        System.out.print("]");
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}

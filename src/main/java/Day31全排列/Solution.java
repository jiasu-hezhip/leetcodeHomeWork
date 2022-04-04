package Day31全排列;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

    /**
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     *
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * 深度优先搜索，没啥好说的，最呆的解法是用用掉一个数就在列表里把这个数去掉
     * 优化一下就可以去掉标记数组，在原本的数组里不停做交换
     *
     * @param args
     */

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("/Users/togi/Downloads/day01-SpringCloud01/代码/leetcodeHomeWork/src/main/java/Day31全排列/test.txt"));
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (in.hasNextInt()){
            arrayList.add(in.nextInt());
        }
        List<List<Integer>> permute = permute(arrayList.stream().mapToInt(Integer::intValue).toArray());
        System.out.print("[");
        for (List<Integer> list:permute
             ) {
            System.out.print("[");
            for (int i = 0; i < list.size(); i++) {
                if (i >= list.size() -1 ) {
                    System.out.print(list.get(i));
                }else {
                    System.out.print(list.get(i) + ",");
                }
            }
            System.out.print("]");
        }
        System.out.print("]");
    }


    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }
        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public static void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 长度，输出，结果集，开始递归位数
        // 主要思想是循环遍历交换开始位和结束位，从0开始
        // 和自己换 换完之后开始位+1 直到开始位等于结束位 添加到结果集
        // 把结果再换回来保证每次的输入都是一样的
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }// 如果已经走到了最后一位，添加到结果集
        for (int i = first; i < n; i++) {
            Collections.swap(output, first, i);// 交换
            backtrack(n, output, res, first + 1);// 继续递归填下一个数
            Collections.swap(output, first, i);// 撤销操作
        }
    }
}

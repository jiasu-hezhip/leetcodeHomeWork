package Day24括号生成;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     * 输入：n = 1
     * 输出：["()"]
     *
     * 这道题可以使用深度优先搜索来完成，因为括号匹配需要先左再右，那么每次添加一个左括号后才能继续添加其他的括号
     * 否则如果先添加右括号则该排序一定是错误的
     *
     * 不要把生成的情况想像成一个序列，而是根据步骤想像成一个二叉树，这样就能理解深度优先搜索了
     *
     * 但是如果想不到也可以使用暴力遍历来完成
     *
     * @param args
     * @throws FileNotFoundException
     */

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("/Users/togi/Downloads/day01-SpringCloud01/代码/leetcodeHomeWork/src/main/java/Day24括号生成/test.txt"));
        int a = in.nextInt();
        ArrayList<String> res = generateParenthesis(a);
        System.out.print("[");
        for (int i = 0; i < res.size(); i++) {
            System.out.print('"' + res.get(i) + '"');
            if (i < res.size() -1) {
                System.out.print(',');
            }
        }
        System.out.print("]");
    }

    public static ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }
        // 执行深度优先遍历，搜索可能的结果
        dfs("", n, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号还有几个可以使用
     * @param right  右括号还有几个可以使用
     * @param res    结果集
     */
    public static void dfs(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可，注意与「力扣」第 46 题、第 39 题区分
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝（左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝,因为一定要优先使用左括号）
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }

        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }

}

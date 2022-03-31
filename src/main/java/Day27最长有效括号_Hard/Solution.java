package Day27最长有效括号_Hard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {

    /**
     * ！！！！！！！！！！！看题目！！！！！！！！！！！！！！
     * ！！！！！！！！！！！题目要的是长度！！！！！！！！！！
     *
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     *
     * 输入：s = "(()"
     * 输出：2
     * 解释：最长有效括号子串是 "()"
     *
     * 输入：s = ")()())"
     * 输出：4
     * 解释：最长有效括号子串是 "()()"
     *
     * 第一想法是构建一个栈，一遍循环依次入栈
     *      入栈规则：
     *      如果为空，只有左括号可以入栈
     *      如果不为空，左右括号都可以入栈，如果入栈的是右括号，同时弹出上一个左括号
     *      如果栈里面已经有元素 弹出的括号要继续包在栈中元素里
     *
     *      老老实实用栈叭！！！！！！！
     *      左括号入栈
     *      右括号出栈，算出最大长度
     *      循环比较一次就能出来
     *
     *
     * 第二个想法 动态规划
     *      1。为字符串再单独构建一个数组
     *      2。从头开始寻找，如果出现有匹配的字符串则给对应数组标上子串长度
     *      3。向前寻找时，如果遇到右括号就反向找到最近的一个左括号，如果反向找到的第一个元素还是右括号，则需要在之后再多匹配一个左括号
     *      (此处官方给出了状态转移的方法，即使用长度数组来确定)
     *      4。匹配完成后找到标记数组最大值，返回最大值的下标（i）和下标-最大值（i-max）为下标的原数组
     *      （看题目的话只要返回最大值就行）
     *1
     *
     * @param args
     */

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("/Users/togi/Downloads/day01-SpringCloud01/代码/leetcodeHomeWork/src/main/java/Day27最长有效括号_Hard/test.txt"));
        String s = in.nextLine();
        System.out.println(longestValidParentheses(s));
//        System.out.println(longestValidParentheses2(s));
    }

    public static int longestValidParentheses(String s) {
        int maxans = 0; // 标记出最大值
        int[] dp = new int[s.length()]; //构建一个新的数组
        for (int i = 1; i < s.length(); i++) { //从1开始防止下标越界
            if (s.charAt(i) == ')') { // 如果当前字符是右括号
                if (s.charAt(i - 1) == '(') { // 看看它的左边是不是左括号
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2; // 处理边界问题，并给dp[i]赋值，是在前两个的基础上+2
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') { // 虽然写的很复杂，但是基本逻辑和上一个不变，只是在[i-1]中添加了上一个的标记量
                    // 同时做出了健壮性检测，即当前位置必须要大于上一个的子串长度
                    // 这是为了防止匹配到前一个右括号的左括号的情况
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    //栈 栈最简单了
    public static int longestValidParentheses2(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

}

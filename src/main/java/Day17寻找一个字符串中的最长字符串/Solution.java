package Day17寻找一个字符串中的最长字符串;

public class Solution {

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     */

    /**
     *
     * 我的解决方法
     * 1。转为数组，循环每个字符
     *  1。1 如果循环到的数离数组最后的长度还没有之前已经记录的最大长度长则直接退出
     * 2。对于每个字符，新增一个判断函数，该函数输入一个字符串判断是否为回文数
     *  2。1 每次输入的字符应当是从后向前寻找，这样找到第一个回文数就直接退出
     * 3。如果是回文数，记录该字串和对应的长度
     * 4。找到最长数的数组下标返回对应字串
     */

    /**
     *
     * 官方的第一种方案和我的差不多，动态规划
     *
     * public String longestPalindrome(String s) {
     *         int len = s.length();
     *         if (len < 2) {
     *             return s;
     *         }
     *
     *         int maxLen = 1;
     *         int begin = 0;
     *         // dp[i][j] 表示 s[i..j] 是否是回文串
     *         boolean[][] dp = new boolean[len][len];
     *         // 初始化：所有长度为 1 的子串都是回文串
     *         for (int i = 0; i < len; i++) {
     *             dp[i][i] = true;
     *         }
     *
     *         char[] charArray = s.toCharArray();
     *         // 递推开始
     *         // 先枚举子串长度
     *         for (int L = 2; L <= len; L++) {
     *             // 枚举左边界，左边界的上限设置可以宽松一些
     *             for (int i = 0; i < len; i++) {
     *                 // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
     *                 int j = L + i - 1;
     *                 // 如果右边界越界，就可以退出当前循环
     *                 if (j >= len) {
     *                     break;
     *                 }
     *
     *                 if (charArray[i] != charArray[j]) {
     *                     dp[i][j] = false;
     *                 } else {
     *                     if (j - i < 3) {
     *                         dp[i][j] = true;
     *                     } else {
     *                         dp[i][j] = dp[i + 1][j - 1];
     *                     }
     *                 }
     *
     *                 // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
     *                 if (dp[i][j] && j - i + 1 > maxLen) {
     *                     maxLen = j - i + 1;
     *                     begin = i;
     *                 }
     *             }
     *         }
     *         return s.substring(begin, begin + maxLen);
     *     }
     *
     *
     */


    // 第二种方法是根据回文数的特性 以每个字符为中心向外扩展以求得最大回文长度
    // 虽然时间复杂度相同，但是非常好理解
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); // 定义只有aba的情况
            int len2 = expandAroundCenter(s, i, i + 1); // 定义abba的情况
            int len = Math.max(len1, len2); // 一遍循环找出最大的长度
            if (len > end - start) { //如果长度大于上一个，则更新最大长度的开始值和结束值
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }
}

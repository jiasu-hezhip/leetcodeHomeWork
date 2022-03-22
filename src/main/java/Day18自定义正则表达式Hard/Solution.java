package Day18自定义正则表达式Hard;

public class Solution {

    /**
     * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
     *
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖  ！！！ 整个 ！！！  字符串s的，而不是部分字符串。
     *
     * 根据给定的事例 "aa" 和 "a*" 其实在两个数组中，需要按照下标去匹配
     *
     *
     * 我的想法是：既然没有提出时间复杂度要求，就对需要匹配的字符串做一次循环
     * 非常复杂，代码量巨大
     * --- 非空判断
     * 1。首先找所有的"." 和 "*" 如果有，按照他们的数组下标向前搜索
     *  1。1 如果是"." 的话只能匹配单一字符串
     *  1。1。1 找到"."下标的前一位是啥
     *  1。1。2 依次向前判断原字符串对应位置元素和现有元素
     *          如果是"*" 直接返回true
     *  --- 如果能对应则判断成功，向后搜索，不能就直接返回false，停止循环
     *  1。2 如果是"*" 则直接向后搜索
     *  1。2。1 找到"*"下标的前一位是啥
     *  1。2。2 依次向前判断原字符串对应位置元素和现有元素
     *          如果是"."直接返回true
     *  --- 如果能对应则判断成功，向后搜索，不能就直接返回false，停止循环
     * 2。如果没有特殊字符则直接开始匹配
     * --- 判断成功返回true
     * ---否则返回false
     *
     * 官方使用了动态规划，但是它优化了动态规划方程式
     * 直接说不好理解，在代码中解释
     *
     *
     */
    
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        ArrayList<String> arrayList = new ArrayList();
//        while (in.hasNextLine()){
//            arrayList.add(in.nextLine());
//        }
        String s = "aaaa";
        String p = "a*";
        System.out.println(isMatch(s,p));

    }


    public static boolean isMatch(String s, String p) {

        /**
         * 所谓动态规划就是指每次都能根据一定的方式简化到最后，并且不需要像递归那样一直重复计算的方式
         *
         * 在本题中，无论怎么计算都需要使用到双重for循环来解决问题
         *
         * 针对s中每个需要对比的字符，p中都需要取出全部的字符来对比，如果对比成功则可以转移它的状态到下一位，直到最后匹配完成
         *
         */

        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];

        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

}

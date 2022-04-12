package Day38不同路径;

public class Solution {

    /**
     * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     *
     * 问总共有多少条不同的路径？
     *
     * 输入：m = 3, n = 7
     * 输出：28
     *
     * 动态规划
     *      每个格子的值 = 自己的左边 + 自己的上面
     *      for 循环挨个遍历就行
     *
     * @param args
     */

    public static void main(String[] args) {
        int m = 3, n= 7;
        final int i = uniquePaths(m, n);
    }

    public static int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }


}

package Day39最小路径;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    /**
     * 给定一个包含非负整数的 mxn网格grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     *
     * 说明：每次只能向下或者向右移动一步。
     *
     * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
     * 输出：7
     * 解释：因为路径 1→3→1→1→1 的总和最小。
     *
     * 昨天刚做完动态规划 在m*n内找出所有不同路径
     * 实际上这条题也是在不同的路线中找到最小的那个
     * 方格上的值只可能等于 自己左上角 + 自己上方 ｜｜ 自己左方
     * 所以在构建出基础结构后，for循环依次判断上方和左方最小的一个就行
     *
     * @param args
     */

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("/Users/togi/Downloads/day01-SpringCloud01/代码/leetcodeHomeWork/src/main/java/Day39最小路径/test.txt"));
        ArrayList<String> arrayList = new ArrayList<>();
        while (in.hasNextLine()){
            arrayList.add(in.nextLine());
        }
        int m = arrayList.size();
        int n = arrayList.get(0).split(" ").length;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            String[] split = arrayList.get(i).split(" ");
            int[] temp = new int[n];
            for (int j = 0; j < split.length; j++) {
                temp[j] = Integer.parseInt(split[j]);
            }
            result[i] = temp;
        }
        System.out.println(minPathSum(result));
    }
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        } // 正确性判断
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns]; // 初始化一个新的存放结果二维数组
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        // 初始化边，注意累加各条边而不是和上一题一样将各边初始化为1
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]; // 每一步求出自己和左边上边最小值的和
            }
        }
        return dp[rows - 1][columns - 1];
    }

}

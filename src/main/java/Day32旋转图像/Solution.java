package Day32旋转图像;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    /**
     * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     *
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     *
     * 图在同目录下
     *
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[7,4,1],[8,5,2],[9,6,3]]
     *
     * 题目中明确说明了是n*n的结构
     *
     * @param args
     */

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("/Users/togi/Downloads/day01-SpringCloud01/代码/leetcodeHomeWork/src/main/java/Day32旋转图像/test.txt"));
        ArrayList<String> arrayList = new ArrayList<>();
        while (in.hasNextLine()){
            arrayList.add(in.nextLine());
        }
        int n = arrayList.size();
        int[][] ints = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] split = arrayList.get(i).split(" ");
            int[] temp = new int[n];
            for (int j = 0; j < n; j++) {
                temp[j] = Integer.parseInt(split[j]);
            }
            ints[i] = temp;
        }

        rotate(ints);

        System.out.print("[");
        for (int i = 0; i < n; i++) {
            if (i >= n-1) {
                System.out.print("[");
                for (int j = 0; j < n; j++) {
                    if (j >= n-1){
                        System.out.print(ints[i][j]);
                    }else {
                        System.out.print(ints[i][j]);
                        System.out.print(",");
                    }
                }
                System.out.print("]");
            }else {
                System.out.print("[");
                for (int j = 0; j < n; j++) {
                    if (j >= n-1){
                        System.out.print(ints[i][j]);
                    }else {
                        System.out.print(ints[i][j]);
                        System.out.print(",");
                    }
                }
                System.out.print("]");
                System.out.print(",");
            }
        }
        System.out.print("]");
    }

    // 主要思想是交换位置，因为规定了必须原地交换
    // 观察交换的位置(拿纸画一下)，[i][j] = [j][n-i-1]
    // 但是直接交换不行，即便使用了暂存变量也不能很好解决一次交换的问题
    // 观察这个n*n的矩阵，每次旋转都一定对应了四个位置的交换，而他们只需要共用一个暂存变量
    // 最后，因为一次交换就交换了4个位置，所以对于偶数矩阵只需要交换左上角的1/4就行
    // 对于奇数个的矩阵，交换[0][0] -> [1/2*n][1/2*n + 1] 空间
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

}

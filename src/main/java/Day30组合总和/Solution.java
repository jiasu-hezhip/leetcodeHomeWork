package Day30组合总和;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    /**
     * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     *
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     *
     * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
     *
     *
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     * 解释：
     * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
     * 7 也是一个候选， 7 = 7 。
     * 仅有这两种组合。
     *
     * 这种类型的搜索就是递归去找，而且也只能递归去找，for循环之类的都不现实
     * 深度优先算法中可以通过剪枝来优化，但是题目没有提及优化就可以不优化
     *
     * ！！！注意看题
     * 1 <= candidates.length <= 30
     * ！！！   1 <= candidates[i] <= 200 每个数都比1大
     * candidate 中的每个元素都 互不相同
     * 1 <= target <= 500
     *
     *
     * @param args
     */
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("/Users/togi/Downloads/day01-SpringCloud01/代码/leetcodeHomeWork/src/main/java/Day30组合总和/test.txt"));
        int target = in.nextInt();
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (in.hasNextInt()){
            arrayList.add(in.nextInt());
        }
        int[] nums = arrayList.stream().mapToInt(Integer::intValue).toArray();
        List<List<Integer>> lists = combinationSum(nums, target);
        System.out.print("[");
        for (List<Integer> list:lists
             ) {
            System.out.print("[");
            for (int i = 0; i < list.size(); i++) {
                if (i >= list.size() - 1){
                    System.out.print(i);
                }else {
                    System.out.print(i + ",");
                }
            }
            System.out.print("]");
        }
        System.out.print("]");
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        // 需要传入待搜索数组  目标值  最终结果 单个循环结果 当前寻找下标
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public static void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        } // 判空 最初的肯定都是一些判断问题
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 求和为0 直接跳过
        dfs(candidates, target, ans, combine, idx + 1); // 正常递归
        // 选择当前数
        if (target - candidates[idx] >= 0) { // 开始尝试用目标值去减当前值
            combine.add(candidates[idx]); // 如果成功了，收集当前值
            dfs(candidates, target - candidates[idx], ans, combine, idx); // 继续寻找自己，但是目标值修改为已减少的值
            combine.remove(combine.size() - 1); // 如果最后没有找到一个可以让 目标值 - 当前idx的值 大于等于0的值，把当前值移除掉
            // 因为上一个idx+1已经保证了可以把除了当前值以外的第二个值添加到寻找，所以可以直接remove掉，当前函数执行完，不添加到ans中
        }
    }
    
}

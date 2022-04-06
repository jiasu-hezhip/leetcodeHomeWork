package Day33字母异位词排列;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {

    /**
     *
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     *
     * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
     *
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     *
     * 说人话就是把给定单词中，所有组成字符相同的分到一个组
     *
     *
     * 暴力解法：
     * 对于每个单词，其组成字母是相同的，所以维护一个map
     * 如果字母的组成相同则放到一个map的键中（for循环计算每一个单词的组成）
     * 最后返回该map组成的list
     *
     * 官方也是这个思路，但是官方是对所有字符做了一个排序！很优秀的想法
     * 这样就能直接解决键和字符查询的问题
     *
     * @param args
     * @throws FileNotFoundException
     */


    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("/Users/togi/Downloads/day01-SpringCloud01/代码/leetcodeHomeWork/src/main/java/Day33字母异味词排列/test.txt"));
        String[] split = in.nextLine().split(" ");
        List<List<String>> lists = groupAnagrams(split);
        System.out.print("[");
        for (int i = 0; i < lists.size(); i++) {
            if (i >= lists.size() -1 ){
                soutList(lists.get(i));
            }else {
                soutList(lists.get(i));
                System.out.print(",");
            }
        }
        System.out.print("]");
    }

    public static void soutList(List<String> list){
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            if (i>= list.size()-1){
                System.out.print(list.get(i));
            }else {
                System.out.print(list.get(i) + ",");
            }
        }
        System.out.print("]");
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray(); // 先将字符串转换为字符数组
            Arrays.sort(array); // 排序该字符数组
            //！！！ 字符串也是可以直接排序的
            String key = new String(array); // 使用排序过后的字符串作为key
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            // 使用map自带的getOrDefault方法获取当前key对应的列表，省掉了一步如果为空则新创建的步骤
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values()); // 直接使用map来构建list
    }

}

package Day20电话号码的字母组合;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {

        /**
         * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
         *
         * 2 abc
         * 3 def
         * 4 ghi
         * 5 jkl
         * 6 mno
         * 7 pqrs
         * 8 tuv
         * 9 wxyz
         *
         * 输入：digits = "23"
         * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
         *
         * 直接暴力递归穷举？
         * 可能这个类型需要转换成树来操作，先把树构造出来，然后走一次树的深度优先搜索答案就出来了
         * 但是这样也会出现时间复杂度问题
         *
         * 好吧，官方用的回溯也是暴力穷举
         *
         * 回溯的结束条件是一定会输出一个长度和给定字符串长度相等的字符串
         * 每次根据index拿到对应map中的数据，for循环添加到一个维护的字符串中
         * 找到最后一个后删除最后一位添加最新的一位
         *
         * ！！递归一定是会一直走到重点的，所以只要给定判断条件，递归会把所有的可能性都走一遍
         * ！！唯一困难的是如何去构造递归公式
         *
         * 本地的递归公式是（ 最后放答案的list ， 暂存数据的map ， 实际传入字符串 ， 当前下标 ， 维护的字符串（！每次都把自己的状态传入到下一次的递归中） ）
         *
         */

        String a = "232";
        System.out.println(letterCombinations(a).toString());
    }

    public static List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits.length() == 0 || digits.equals("")) {
            return list;
        }

        Map<Character,String> phone = new HashMap<Character,String>();
        phone.put('2',"abc");
        phone.put('3',"def");
        phone.put('4',"ghi");
        phone.put('5',"jkl");
        phone.put('6',"mno");
        phone.put('7',"pqrs");
        phone.put('8',"tuv");
        phone.put('9',"wxyz");

        backtrack(list,phone,digits,0,new StringBuffer());

        return list;
    }

    public static void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) { // 直到长度到达制定长度
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit); // 从第一位开始做递归
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) { // for循环添加所有对应的字段
                combination.append(letters.charAt(i)); // 先添加第一个字符
                backtrack(combinations, phoneMap, digits, index + 1, combination); //循环添加自己的下一个字符
                combination.deleteCharAt(index); //删除掉最后一个，用来添加新的字符
            }
        }
    }

}

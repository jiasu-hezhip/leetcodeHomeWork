package Day22括号匹配;

import java.util.ArrayList;

public class Solution {

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *
     * @param args
     */

    public static void main(String[] args) {
        String s = "({})[]{}";
//        String s = "(]{}";

        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        if (s.length() == 0 || s.equals("") || s == null || s.length() == 1) {
            return false;
        }
        char[] chars = s.toCharArray();
        if (chars.length == 1) {
            return false;
        }
        ArrayList<Character> arrayList = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            arrayList.add(chars[i]);
            if (arrayList.size() > 1){
                if (isMatch(chars[i],arrayList.get(arrayList.size()-2))) {
                    arrayList.remove(arrayList.size() - 1);
                    arrayList.remove(arrayList.size() - 1);
                }
            }
        }
        return arrayList.size() == 0 ? true : false;
    }

    public static boolean isMatch(char a,char b) {
        if (a == ')' && b == '(') {
            return true;
        }
        if (a == '}' && b == '{') {
            return true;
        }
        if (a == ']' && b == '[') {
            return true;
        }
        return false;
    }

}

package Day16无重复字符的最长字串;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        String s = "asa";
        System.out.println(lengthOfLongestSubstring(s));
    }

    /**
     * 官方解法
     * 其实思路差不多，都是沿着字符串路径做的搜索，只不过官方用了set，而我用了list
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }


    public static int lengthOfLongestSubstring(String s) { //沿着字符串直接搜索
        char chars[] = s.toCharArray();
        if (chars.length == 0) {
            return 0;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length - 1) {
                arrayList.add(1);
                break;
            }
            int j = i;
            ArrayList<Character> arrayList2 = new ArrayList<>();
            arrayList2.add(chars[i]);
            while (!exist(arrayList2,chars[j+1])) {
                if (j+1 > chars.length) {
                    break;
                }else {
                    arrayList2.add(chars[j+1]);
                    j++;
                    if (j>=chars.length-1){
                        break;
                    }
                }

            }
            arrayList.add(arrayList2.size());
        }
        arrayList.sort(Comparator.reverseOrder()); // 自带的升序降序
        return arrayList.get(0);
    }

    public static boolean exist(ArrayList<Character> arrayList, char c) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (c == arrayList.get(i)) {
                return true;
            }
        }
        return false;
    }

}
